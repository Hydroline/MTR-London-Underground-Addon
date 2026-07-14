#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
SERVER_DIR="${SERVER_DIR:-$ROOT_DIR/server-smoke}"
TIMEOUT_SECONDS="${TIMEOUT_SECONDS:-240}"
READY_TIMEOUT_SECONDS="${READY_TIMEOUT_SECONDS:-90}"
JAVA_HOME="${JAVA21_HOME:-$HOME/.gradle/jdks/eclipse_adoptium-21-amd64-linux.2}"
JAVA_BIN="$JAVA_HOME/bin/java"
SERVER_JAR_SOURCE="${SERVER_JAR_SOURCE:-/home/aurlemon/code/Joban-Client-Mod/youer-1.21.1-cb6ddeab-server.jar}"
SERVER_JAR_NAME="server.jar"
LOG_FILE="${LOG_FILE:-$SERVER_DIR/logs/latest.log}"
FIFO_PATH="$SERVER_DIR/.server-stdin"
MOD_JAR="${MOD_JAR:-$(find "$ROOT_DIR/neoforge/build/libs" -maxdepth 1 -type f -name 'neoforge-*.jar' ! -name '*-sources.jar' ! -name '*-javadoc.jar' | head -n 1)}"
MTR_JAR_SOURCE="${MTR_JAR_SOURCE:-$HOME/.gradle/caches/modules-2/files-2.1/maven.modrinth/minecraft-transit-railway/NEOFORGE-4.1.0-beta.2+1.21.1/3c25af1f04b5a9178bafa505ffc2fd265525e60d/minecraft-transit-railway-NEOFORGE-4.1.0-beta.2+1.21.1.jar}"
UNIVERSE_DIR="${UNIVERSE_DIR:-$SERVER_DIR/worlds/run-$$}"
LEVEL_NAME="${LEVEL_NAME:-run-$$}"
DEFAULT_SERVER_PORT="$((25587 + (BASHPID % 1000)))"
SERVER_PORT="${SERVER_PORT:-}"

cleanup() {
  if [[ -n "${SERVER_PID:-}" ]] && kill -0 "$SERVER_PID" >/dev/null 2>&1; then
    printf 'stop\n' >&${FIFO_FD} || true
    wait "$SERVER_PID" || true
  fi
  if [[ -n "${FIFO_FD:-}" ]]; then
    exec {FIFO_FD}>&- || true
  fi
  rm -f "$FIFO_PATH"
}

trap cleanup EXIT INT TERM

require_file() {
  local path="$1"
  if [[ ! -f "$path" ]]; then
    echo "Missing required file: $path" >&2
    exit 1
  fi
}

stop_stale_server() {
  local jar_path="$SERVER_DIR/$SERVER_JAR_NAME"
  pkill -f "$jar_path" >/dev/null 2>&1 || true
}

find_open_port() {
  local start_port="$1"
  local end_port=$((start_port + 200))
  local candidate
  for ((candidate = start_port; candidate <= end_port; candidate++)); do
    if ! ss -ltnH "( sport = :$candidate )" | grep -q .; then
      echo "$candidate"
      return 0
    fi
  done
  echo "Failed to find an open port in range ${start_port}-${end_port}" >&2
  exit 1
}

wait_for_log() {
  local pattern="$1"
  local deadline=$((SECONDS + READY_TIMEOUT_SECONDS))
  while (( SECONDS < deadline )); do
    if [[ -f "$LOG_FILE" ]] && rg -q "$pattern" "$LOG_FILE"; then
      return 0
    fi
    sleep 1
  done
  echo "Timed out waiting for log pattern: $pattern" >&2
  if [[ -f "$LOG_FILE" ]]; then
    tail -n 80 "$LOG_FILE" >&2 || true
  fi
  return 1
}

wait_for_server_ready() {
  local done_deadline=$((SECONDS + READY_TIMEOUT_SECONDS))
  while (( SECONDS < done_deadline )); do
    if [[ -f "$LOG_FILE" ]] && rg -q 'Done \([0-9.]+s\)! For help, type "help"' "$LOG_FILE"; then
      break
    fi
    sleep 1
  done

  if ! [[ -f "$LOG_FILE" ]] || ! rg -q 'Done \([0-9.]+s\)! For help, type "help"' "$LOG_FILE"; then
    echo "Timed out waiting for server startup completion" >&2
    tail -n 120 "$LOG_FILE" >&2 || true
    exit 1
  fi

  local ready_deadline=$((SECONDS + 15))
  while (( SECONDS < ready_deadline )); do
    if rg -q "Starting Minecraft server on \\*:${SERVER_PORT}" "$LOG_FILE" && ! rg -q 'FAILED TO BIND TO PORT' "$LOG_FILE"; then
      return 0
    fi
    sleep 1
  done

  echo "Server did not bind cleanly to port ${SERVER_PORT}" >&2
  tail -n 120 "$LOG_FILE" >&2 || true
  exit 1
}

send_command() {
  printf '%s\n' "$1" >&${FIFO_FD}
}

start_server() {
  rm -f "$LOG_FILE" "$FIFO_PATH"
  mkfifo "$FIFO_PATH"
  exec {FIFO_FD}<>"$FIFO_PATH"

  cd "$SERVER_DIR"
  env JAVA_HOME="$JAVA_HOME" PATH="$JAVA_HOME/bin:$PATH" \
    timeout --signal=TERM --kill-after=20s "${TIMEOUT_SECONDS}s" \
    "$JAVA_BIN" -jar "$SERVER_JAR_NAME" nogui --universe "$UNIVERSE_DIR" <"$FIFO_PATH" >/dev/null 2>&1 &
  SERVER_PID=$!

  wait_for_server_ready
}

stop_server() {
  send_command 'stop'
  wait "$SERVER_PID"
  SERVER_PID=""
  exec {FIFO_FD}>&- || true
  unset FIFO_FD
  rm -f "$FIFO_PATH"
}

require_file "$JAVA_BIN"
require_file "$SERVER_JAR_SOURCE"
require_file "$MTR_JAR_SOURCE"
require_file "$MOD_JAR"

stop_stale_server
SERVER_PORT="${SERVER_PORT:-$(find_open_port "$DEFAULT_SERVER_PORT")}"

mkdir -p "$SERVER_DIR/mods"
mkdir -p "$UNIVERSE_DIR"
cp "$SERVER_JAR_SOURCE" "$SERVER_DIR/$SERVER_JAR_NAME"
cp "$MTR_JAR_SOURCE" "$SERVER_DIR/mods/$(basename "$MTR_JAR_SOURCE")"
cp "$MOD_JAR" "$SERVER_DIR/mods/$(basename "$MOD_JAR")"
printf 'eula=true\n' >"$SERVER_DIR/eula.txt"
cat >"$SERVER_DIR/server.properties" <<EOF
server-port=${SERVER_PORT}
online-mode=false
enable-rcon=false
enable-query=false
motd=London Underground Smoke
level-name=${LEVEL_NAME}
EOF
rm -f "$LOG_FILE" "$FIFO_PATH"
start_server

send_command 'forceload add 0 0 48 16'
wait_for_log 'Marked [0-9]+ chunk[s]? in Overworld from|No chunks were marked for force loading'
sleep 2

send_command 'fill 0 63 0 40 64 0 air'
wait_for_log 'Successfully filled [0-9]+ block\(s\)|No blocks were filled'

send_command 'fill 0 63 0 40 63 0 stone'
wait_for_log 'Successfully filled 41 block\(s\)'

send_command 'setblock 0 64 0 londonunderground:pids_northern'
wait_for_log 'Changed the block at 0, 64, 0'

send_command 'setblock 1 64 0 londonunderground:sign_underground'
wait_for_log 'Changed the block at 1, 64, 0'

send_command 'setblock 2 64 0 londonunderground:pids_pole'
wait_for_log 'Changed the block at 2, 64, 0'

send_command 'setblock 3 64 0 londonunderground:sign_overground'
wait_for_log 'Changed the block at 3, 64, 0'

send_command 'setblock 4 64 0 londonunderground:sign_dlr'
wait_for_log 'Changed the block at 4, 64, 0'

send_command 'setblock 5 64 0 londonunderground:sign_trams'
wait_for_log 'Changed the block at 5, 64, 0'

send_command 'setblock 6 64 0 londonunderground:sign_poppy'
wait_for_log 'Changed the block at 6, 64, 0'

send_command 'setblock 7 64 0 londonunderground:sign_metro'
wait_for_log 'Changed the block at 7, 64, 0'

send_command 'setblock 8 64 0 londonunderground:sign_lizzy'
wait_for_log 'Changed the block at 8, 64, 0'

send_command 'setblock 9 64 0 londonunderground:sign_pride'
wait_for_log 'Changed the block at 9, 64, 0'

send_command 'setblock 10 64 0 londonunderground:sign_river'
wait_for_log 'Changed the block at 10, 64, 0'

send_command 'setblock 11 64 0 londonunderground:morden_sign'
wait_for_log 'Changed the block at 11, 64, 0'

send_command 'setblock 12 64 0 londonunderground:morden_sign_dlr'
wait_for_log 'Changed the block at 12, 64, 0'

send_command 'setblock 13 64 0 londonunderground:morden_sign_overground'
wait_for_log 'Changed the block at 13, 64, 0'

send_command 'setblock 14 64 0 londonunderground:metropolitan_sign'
wait_for_log 'Changed the block at 14, 64, 0'

send_command 'setblock 15 64 0 londonunderground:elizabeth_sign'
wait_for_log 'Changed the block at 15, 64, 0'

send_command 'setblock 16 64 0 londonunderground:block_roundel_1[facing=north,color=0]'
wait_for_log 'Changed the block at 16, 64, 0'

send_command 'setblock 17 64 0 londonunderground:block_roundel_nle[facing=north,color=0]'
wait_for_log 'Changed the block at 17, 64, 0'

send_command 'setblock 18 64 0 londonunderground:block_roundel_2[facing=north,color=0]'
wait_for_log 'Changed the block at 18, 64, 0'

send_command 'setblock 19 64 0 londonunderground:block_roundel_2_big[facing=north,color=0]'
wait_for_log 'Changed the block at 19, 64, 0'

send_command 'setblock 20 64 0 londonunderground:block_roundel_2_big_even[facing=north,color=0]'
wait_for_log 'Changed the block at 20, 64, 0'

send_command 'setblock 21 64 0 londonunderground:block_roundel_3[facing=north,color=0]'
wait_for_log 'Changed the block at 21, 64, 0'

send_command 'setblock 22 64 0 londonunderground:block_roundel_3_big[facing=north,color=0]'
wait_for_log 'Changed the block at 22, 64, 0'

send_command 'setblock 23 64 0 londonunderground:block_roundel_3_big_even[facing=north,color=0]'
wait_for_log 'Changed the block at 23, 64, 0'

send_command 'setblock 24 64 0 londonunderground:block_roundel_4[facing=north,color=0]'
wait_for_log 'Changed the block at 24, 64, 0'

send_command 'setblock 25 64 0 londonunderground:block_roundel_4_big[facing=north,color=0]'
wait_for_log 'Changed the block at 25, 64, 0'

send_command 'setblock 26 64 0 londonunderground:block_roundel_4_big_even[facing=north,color=0]'
wait_for_log 'Changed the block at 26, 64, 0'

send_command 'setblock 27 64 0 londonunderground:block_roundel_5[facing=north,color=0]'
wait_for_log 'Changed the block at 27, 64, 0'

send_command 'setblock 28 64 0 londonunderground:block_roundel_5_big[facing=north,color=0]'
wait_for_log 'Changed the block at 28, 64, 0'

send_command 'setblock 29 64 0 londonunderground:block_roundel_5_big_even[facing=north,color=0]'
wait_for_log 'Changed the block at 29, 64, 0'

send_command 'setblock 30 64 0 londonunderground:block_roundel_1_big[facing=north,color=0]'
wait_for_log 'Changed the block at 30, 64, 0'

send_command 'setblock 31 64 0 londonunderground:block_roundel_1_big_even[facing=north,color=0]'
wait_for_log 'Changed the block at 31, 64, 0'

send_command 'setblock 32 64 0 londonunderground:british_rail_underground[facing=north,color=0]'
wait_for_log 'Changed the block at 32, 64, 0'

send_command 'setblock 33 64 0 londonunderground:block_roundel_station[facing=north,color=0]'
wait_for_log 'Changed the block at 33, 64, 0'

send_command 'setblock 34 64 0 londonunderground:block_roundel_station_type_b[facing=north,color=0]'
wait_for_log 'Changed the block at 34, 64, 0'

send_command 'setblock 35 64 0 londonunderground:block_roundel_station_type_c[facing=north,color=0]'
wait_for_log 'Changed the block at 35, 64, 0'

send_command 'setblock 36 64 0 londonunderground:block_roundel_station_top[facing=north,color=0]'
wait_for_log 'Changed the block at 36, 64, 0'

send_command 'setblock 37 64 0 londonunderground:name_projector[facing=north,color=0]'
wait_for_log 'Changed the block at 37, 64, 0'

send_command 'setblock 38 64 0 londonunderground:tunnel_a2_signal[facing=north,is_45=false,is_22_5=false,power=0]'
wait_for_log 'Changed the block at 38, 64, 0'

send_command 'setblock 39 64 0 londonunderground:tunnel_block_2_signal[facing=north,is_45=false,is_22_5=false,power=0]'
wait_for_log 'Changed the block at 39, 64, 0'

send_command 'data get block 0 64 0'
wait_for_log '0, 64, 0 has the following block data:'
wait_for_log 'platform_ids'
wait_for_log 'display_page'
wait_for_log 'custom_color'
wait_for_log 'message0'
wait_for_log 'hide_arrival0'

send_command 'data merge block 0 64 0 {platform_ids:[L;11L,22L],display_page:2,custom_color:123456,message0:"alpha",message1:"beta",message2:"gamma",hide_arrival0:1b,hide_arrival1:0b,hide_arrival2:1b}'
wait_for_log 'Modified block (entity )?data of (block at )?0, 64, 0'
send_command 'data get block 0 64 0'
wait_for_log '0, 64, 0 has the following block data:'
wait_for_log '11'
wait_for_log '22'
wait_for_log '123456'
wait_for_log 'alpha'
wait_for_log 'beta'
wait_for_log 'gamma'

send_command 'execute if block 0 64 0 londonunderground:pids_northern run say london-pids-northern-ok'
wait_for_log 'london-pids-northern-ok'
send_command 'say london-pids-northern-config-ok'
wait_for_log 'london-pids-northern-config-ok'

send_command 'execute if block 1 64 0 londonunderground:sign_underground[color=0] run say london-sign-ok'
wait_for_log 'london-sign-ok'

send_command 'execute if block 2 64 0 londonunderground:pids_pole run say london-pole-ok'
wait_for_log 'london-pole-ok'

send_command 'execute if block 3 64 0 londonunderground:sign_overground[color=0] run say london-overground-ok'
wait_for_log 'london-overground-ok'

send_command 'execute if block 4 64 0 londonunderground:sign_dlr[color=0] run say london-dlr-ok'
wait_for_log 'london-dlr-ok'

send_command 'execute if block 5 64 0 londonunderground:sign_trams[color=0] run say london-trams-ok'
wait_for_log 'london-trams-ok'

send_command 'execute if block 6 64 0 londonunderground:sign_poppy[color=0] run say london-poppy-ok'
wait_for_log 'london-poppy-ok'

send_command 'execute if block 7 64 0 londonunderground:sign_metro[color=0] run say london-metro-ok'
wait_for_log 'london-metro-ok'

send_command 'execute if block 8 64 0 londonunderground:sign_lizzy[color=0] run say london-lizzy-ok'
wait_for_log 'london-lizzy-ok'

send_command 'execute if block 9 64 0 londonunderground:sign_pride[color=0] run say london-pride-ok'
wait_for_log 'london-pride-ok'

send_command 'execute if block 10 64 0 londonunderground:sign_river[color=0] run say london-river-ok'
wait_for_log 'london-river-ok'

send_command 'execute if block 11 64 0 londonunderground:morden_sign run say london-morden-ok'
wait_for_log 'london-morden-ok'

send_command 'execute if block 12 64 0 londonunderground:morden_sign_dlr run say london-morden-dlr-ok'
wait_for_log 'london-morden-dlr-ok'

send_command 'execute if block 13 64 0 londonunderground:morden_sign_overground run say london-morden-overground-ok'
wait_for_log 'london-morden-overground-ok'

send_command 'execute if block 14 64 0 londonunderground:metropolitan_sign[color=0] run say london-metropolitan-ok'
wait_for_log 'london-metropolitan-ok'

send_command 'execute if block 15 64 0 londonunderground:elizabeth_sign[color=0] run say london-elizabeth-ok'
wait_for_log 'london-elizabeth-ok'

send_command 'execute if block 16 64 0 londonunderground:block_roundel_1[facing=north,color=0] run say london-roundel-1-ok'
wait_for_log 'london-roundel-1-ok'

send_command 'execute if block 17 64 0 londonunderground:block_roundel_nle[facing=north,color=0] run say london-roundel-nle-ok'
wait_for_log 'london-roundel-nle-ok'

send_command 'execute if block 18 64 0 londonunderground:block_roundel_2[facing=north,color=0] run say london-roundel-2-ok'
wait_for_log 'london-roundel-2-ok'

send_command 'execute if block 19 64 0 londonunderground:block_roundel_2_big[facing=north,color=0] run say london-roundel-2-big-ok'
wait_for_log 'london-roundel-2-big-ok'

send_command 'execute if block 20 64 0 londonunderground:block_roundel_2_big_even[facing=north,color=0] run say london-roundel-2-big-even-ok'
wait_for_log 'london-roundel-2-big-even-ok'

send_command 'execute if block 21 64 0 londonunderground:block_roundel_3[facing=north,color=0] run say london-roundel-3-ok'
wait_for_log 'london-roundel-3-ok'

send_command 'execute if block 22 64 0 londonunderground:block_roundel_3_big[facing=north,color=0] run say london-roundel-3-big-ok'
wait_for_log 'london-roundel-3-big-ok'

send_command 'execute if block 23 64 0 londonunderground:block_roundel_3_big_even[facing=north,color=0] run say london-roundel-3-big-even-ok'
wait_for_log 'london-roundel-3-big-even-ok'

send_command 'execute if block 24 64 0 londonunderground:block_roundel_4[facing=north,color=0] run say london-roundel-4-ok'
wait_for_log 'london-roundel-4-ok'

send_command 'execute if block 25 64 0 londonunderground:block_roundel_4_big[facing=north,color=0] run say london-roundel-4-big-ok'
wait_for_log 'london-roundel-4-big-ok'

send_command 'execute if block 26 64 0 londonunderground:block_roundel_4_big_even[facing=north,color=0] run say london-roundel-4-big-even-ok'
wait_for_log 'london-roundel-4-big-even-ok'

send_command 'execute if block 27 64 0 londonunderground:block_roundel_5[facing=north,color=0] run say london-roundel-5-ok'
wait_for_log 'london-roundel-5-ok'

send_command 'execute if block 28 64 0 londonunderground:block_roundel_5_big[facing=north,color=0] run say london-roundel-5-big-ok'
wait_for_log 'london-roundel-5-big-ok'

send_command 'execute if block 29 64 0 londonunderground:block_roundel_5_big_even[facing=north,color=0] run say london-roundel-5-big-even-ok'
wait_for_log 'london-roundel-5-big-even-ok'

send_command 'execute if block 30 64 0 londonunderground:block_roundel_1_big[facing=north,color=0] run say london-roundel-1-big-ok'
wait_for_log 'london-roundel-1-big-ok'

send_command 'execute if block 31 64 0 londonunderground:block_roundel_1_big_even[facing=north,color=0] run say london-roundel-1-big-even-ok'
wait_for_log 'london-roundel-1-big-even-ok'

send_command 'execute if block 32 64 0 londonunderground:british_rail_underground[facing=north,color=0] run say london-british-rail-ok'
wait_for_log 'london-british-rail-ok'

send_command 'execute if block 33 64 0 londonunderground:block_roundel_station[facing=north,color=0] run say london-roundel-station-ok'
wait_for_log 'london-roundel-station-ok'

send_command 'execute if block 34 64 0 londonunderground:block_roundel_station_type_b[facing=north,color=0] run say london-roundel-station-b-ok'
wait_for_log 'london-roundel-station-b-ok'

send_command 'execute if block 35 64 0 londonunderground:block_roundel_station_type_c[facing=north,color=0] run say london-roundel-station-c-ok'
wait_for_log 'london-roundel-station-c-ok'

send_command 'execute if block 36 64 0 londonunderground:block_roundel_station_top[facing=north,color=0] run say london-roundel-station-top-ok'
wait_for_log 'london-roundel-station-top-ok'

send_command 'execute if block 37 64 0 londonunderground:name_projector[facing=north,color=0] run say london-name-projector-ok'
wait_for_log 'london-name-projector-ok'

send_command 'execute if block 38 64 0 londonunderground:tunnel_a2_signal[facing=north,is_45=false,is_22_5=false,power=0] run say london-tunnel-a2-signal-ok'
wait_for_log 'london-tunnel-a2-signal-ok'

send_command 'execute if block 39 64 0 londonunderground:tunnel_block_2_signal[facing=north,is_45=false,is_22_5=false,power=0] run say london-tunnel-block-2-signal-ok'
wait_for_log 'london-tunnel-block-2-signal-ok'

send_command 'forceload remove 0 0 48 16'
wait_for_log 'Unmarked [0-9]+ chunk[s]? in Overworld from|No chunks were marked for force loading'

stop_server

start_server
sleep 2
send_command 'data get block 0 64 0'
wait_for_log '0, 64, 0 has the following block data:'
wait_for_log '11'
wait_for_log '22'
wait_for_log '123456'
wait_for_log 'alpha'
wait_for_log 'beta'
wait_for_log 'gamma'
wait_for_log 'hide_arrival0'
wait_for_log 'hide_arrival2'
send_command 'say london-pids-northern-persisted-ok'
wait_for_log 'london-pids-northern-persisted-ok'

stop_server

echo "London Underground server smoke passed. Log: $LOG_FILE"
