package net.londonunderground.registry;

import java.util.function.Supplier;
import net.londonunderground.block.AbstractRoundelSignBlock;
import net.londonunderground.block.AbstractMordenSignBlock;
import net.londonunderground.block.TunnelA2SignalBlock;
import net.londonunderground.block.TunnelSignalBlock;
import net.londonunderground.block.NameProjectorBlock;
import net.londonunderground.block.NorthernPIDSBlock;
import net.londonunderground.block.StationRoundelBlock;
import net.londonunderground.block.WallRoundelBlock;
import net.londonunderground.init.MainNeoForge;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class LUBlockEntities {

	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
			DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MainNeoForge.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NorthernPIDSBlock.NorthernPIDSBlockEntity>> PIDS_NORTHERN =
			register("pids_northern", () -> BlockEntityType.Builder.of(
					(pos, state) -> new NorthernPIDSBlock.NorthernPIDSBlockEntity(getPidsNorthernType(), pos, state),
					LUBlocks.PIDS_NORTHERN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TunnelSignalBlock.TunnelSignalBlockEntity>> TUNNEL_BLOCK_2_SIGNAL =
			register("tunnel_block_2_signal", () -> BlockEntityType.Builder.of(
					(pos, state) -> new TunnelSignalBlock.TunnelSignalBlockEntity(getTunnelBlock2SignalType(), pos, state),
					LUBlocks.TUNNEL_BLOCK_2_SIGNAL.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TunnelA2SignalBlock.TunnelA2SignalBlockEntity>> TUNNEL_A2_SIGNAL =
			register("tunnel_a2_signal", () -> BlockEntityType.Builder.of(
					(pos, state) -> new TunnelA2SignalBlock.TunnelA2SignalBlockEntity(getTunnelA2SignalType(), pos, state),
					LUBlocks.TUNNEL_A2_SIGNAL.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NameProjectorBlock.NameProjectorBlockEntity>> NAME_PROJECTOR =
			register("name_projector", () -> BlockEntityType.Builder.of(
					(pos, state) -> new NameProjectorBlock.NameProjectorBlockEntity(getNameProjectorType(), pos, state),
					LUBlocks.NAME_PROJECTOR.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_UNDERGROUND =
			register("sign_underground", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignUndergroundType(), pos, state),
					LUBlocks.SIGN_UNDERGROUND.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_OVERGROUND =
			register("sign_overground", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignOvergroundType(), pos, state),
					LUBlocks.SIGN_OVERGROUND.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_DLR =
			register("sign_dlr", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignDlrType(), pos, state),
					LUBlocks.SIGN_DLR.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_TRAMS =
			register("sign_trams", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignTramsType(), pos, state),
					LUBlocks.SIGN_TRAMS.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_POPPY =
			register("sign_poppy", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignPoppyType(), pos, state),
					LUBlocks.SIGN_POPPY.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_METRO =
			register("sign_metro", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignMetroType(), pos, state),
					LUBlocks.SIGN_METRO.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_LIZZY =
			register("sign_lizzy", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignLizzyType(), pos, state),
					LUBlocks.SIGN_LIZZY.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_PRIDE =
			register("sign_pride", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignPrideType(), pos, state),
					LUBlocks.SIGN_PRIDE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> SIGN_RIVER =
			register("sign_river", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getSignRiverType(), pos, state),
					LUBlocks.SIGN_RIVER.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity>> MORDEN_SIGN =
			register("morden_sign", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractMordenSignBlock.MordenSignBlockEntity(getMordenSignType(), pos, state),
					LUBlocks.MORDEN_SIGN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity>> MORDEN_SIGN_DLR =
			register("morden_sign_dlr", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractMordenSignBlock.MordenSignBlockEntity(getMordenSignDlrType(), pos, state),
					LUBlocks.MORDEN_SIGN_DLR.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity>> MORDEN_SIGN_OVERGROUND =
			register("morden_sign_overground", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractMordenSignBlock.MordenSignBlockEntity(getMordenSignOvergroundType(), pos, state),
					LUBlocks.MORDEN_SIGN_OVERGROUND.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> METROPOLITAN_SIGN =
			register("metropolitan_sign", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getMetropolitanSignType(), pos, state),
					LUBlocks.METROPOLITAN_SIGN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity>> ELIZABETH_SIGN =
			register("elizabeth_sign", () -> BlockEntityType.Builder.of(
					(pos, state) -> new AbstractRoundelSignBlock.RoundelSignBlockEntity(getElizabethSignType(), pos, state),
					LUBlocks.ELIZABETH_SIGN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BRITISH_RAIL_UNDERGROUND =
			register("british_rail_underground", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBritishRailUndergroundType(), pos, state),
					LUBlocks.BRITISH_RAIL_UNDERGROUND.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_1 =
			register("block_roundel_1", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel1Type(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_1.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_NLE =
			register("block_roundel_nle", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundelNleType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_NLE.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_1_BIG =
			register("block_roundel_1_big", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel1BigType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_1_BIG.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_1_BIG_EVEN =
			register("block_roundel_1_big_even", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel1BigEvenType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_1_BIG_EVEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_2 =
			register("block_roundel_2", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel2Type(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_2.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_2_BIG =
			register("block_roundel_2_big", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel2BigType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_2_BIG.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_2_BIG_EVEN =
			register("block_roundel_2_big_even", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel2BigEvenType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_2_BIG_EVEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_3 =
			register("block_roundel_3", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel3Type(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_3.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_3_BIG =
			register("block_roundel_3_big", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel3BigType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_3_BIG.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_3_BIG_EVEN =
			register("block_roundel_3_big_even", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel3BigEvenType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_3_BIG_EVEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_4 =
			register("block_roundel_4", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel4Type(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_4.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_4_BIG =
			register("block_roundel_4_big", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel4BigType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_4_BIG.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_4_BIG_EVEN =
			register("block_roundel_4_big_even", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel4BigEvenType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_4_BIG_EVEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_5 =
			register("block_roundel_5", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel5Type(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_5.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_5_BIG =
			register("block_roundel_5_big", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel5BigType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_5_BIG.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity>> BLOCK_ROUNDEL_5_BIG_EVEN =
			register("block_roundel_5_big_even", () -> BlockEntityType.Builder.of(
					(pos, state) -> new WallRoundelBlock.WallRoundelBlockEntity(getBlockRoundel5BigEvenType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_5_BIG_EVEN.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity>> BLOCK_ROUNDEL_STATION =
			register("block_roundel_station", () -> BlockEntityType.Builder.of(
					(pos, state) -> new StationRoundelBlock.StationRoundelBlockEntity(getBlockRoundelStationType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_STATION.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity>> BLOCK_ROUNDEL_STATION_TYPE_B =
			register("block_roundel_station_type_b", () -> BlockEntityType.Builder.of(
					(pos, state) -> new StationRoundelBlock.StationRoundelBlockEntity(getBlockRoundelStationTypeBType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_STATION_TYPE_B.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity>> BLOCK_ROUNDEL_STATION_TYPE_C =
			register("block_roundel_station_type_c", () -> BlockEntityType.Builder.of(
					(pos, state) -> new StationRoundelBlock.StationRoundelBlockEntity(getBlockRoundelStationTypeCType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_STATION_TYPE_C.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity>> BLOCK_ROUNDEL_STATION_TOP =
			register("block_roundel_station_top", () -> BlockEntityType.Builder.of(
					(pos, state) -> new StationRoundelBlock.StationRoundelBlockEntity(getBlockRoundelStationTopType(), pos, state),
					LUBlocks.BLOCK_ROUNDEL_STATION_TOP.get()).build(null));

	private LUBlockEntities() {
	}

	public static void register(IEventBus modEventBus) {
		BLOCK_ENTITY_TYPES.register(modEventBus);
	}

	private static BlockEntityType<NorthernPIDSBlock.NorthernPIDSBlockEntity> getPidsNorthernType() {
		return PIDS_NORTHERN.get();
	}

	private static BlockEntityType<TunnelSignalBlock.TunnelSignalBlockEntity> getTunnelBlock2SignalType() {
		return TUNNEL_BLOCK_2_SIGNAL.get();
	}

	private static BlockEntityType<TunnelA2SignalBlock.TunnelA2SignalBlockEntity> getTunnelA2SignalType() {
		return TUNNEL_A2_SIGNAL.get();
	}

	private static BlockEntityType<NameProjectorBlock.NameProjectorBlockEntity> getNameProjectorType() {
		return NAME_PROJECTOR.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignUndergroundType() {
		return SIGN_UNDERGROUND.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignOvergroundType() {
		return SIGN_OVERGROUND.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignDlrType() {
		return SIGN_DLR.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignTramsType() {
		return SIGN_TRAMS.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignPoppyType() {
		return SIGN_POPPY.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignMetroType() {
		return SIGN_METRO.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignLizzyType() {
		return SIGN_LIZZY.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignPrideType() {
		return SIGN_PRIDE.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getSignRiverType() {
		return SIGN_RIVER.get();
	}

	private static BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity> getMordenSignType() {
		return MORDEN_SIGN.get();
	}

	private static BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity> getMordenSignDlrType() {
		return MORDEN_SIGN_DLR.get();
	}

	private static BlockEntityType<AbstractMordenSignBlock.MordenSignBlockEntity> getMordenSignOvergroundType() {
		return MORDEN_SIGN_OVERGROUND.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getMetropolitanSignType() {
		return METROPOLITAN_SIGN.get();
	}

	private static BlockEntityType<AbstractRoundelSignBlock.RoundelSignBlockEntity> getElizabethSignType() {
		return ELIZABETH_SIGN.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBritishRailUndergroundType() {
		return BRITISH_RAIL_UNDERGROUND.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel1Type() {
		return BLOCK_ROUNDEL_1.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundelNleType() {
		return BLOCK_ROUNDEL_NLE.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel1BigType() {
		return BLOCK_ROUNDEL_1_BIG.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel1BigEvenType() {
		return BLOCK_ROUNDEL_1_BIG_EVEN.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel2Type() {
		return BLOCK_ROUNDEL_2.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel2BigType() {
		return BLOCK_ROUNDEL_2_BIG.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel2BigEvenType() {
		return BLOCK_ROUNDEL_2_BIG_EVEN.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel3Type() {
		return BLOCK_ROUNDEL_3.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel3BigType() {
		return BLOCK_ROUNDEL_3_BIG.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel3BigEvenType() {
		return BLOCK_ROUNDEL_3_BIG_EVEN.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel4Type() {
		return BLOCK_ROUNDEL_4.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel4BigType() {
		return BLOCK_ROUNDEL_4_BIG.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel4BigEvenType() {
		return BLOCK_ROUNDEL_4_BIG_EVEN.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel5Type() {
		return BLOCK_ROUNDEL_5.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel5BigType() {
		return BLOCK_ROUNDEL_5_BIG.get();
	}

	private static BlockEntityType<WallRoundelBlock.WallRoundelBlockEntity> getBlockRoundel5BigEvenType() {
		return BLOCK_ROUNDEL_5_BIG_EVEN.get();
	}

	private static BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity> getBlockRoundelStationType() {
		return BLOCK_ROUNDEL_STATION.get();
	}

	private static BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity> getBlockRoundelStationTypeBType() {
		return BLOCK_ROUNDEL_STATION_TYPE_B.get();
	}

	private static BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity> getBlockRoundelStationTypeCType() {
		return BLOCK_ROUNDEL_STATION_TYPE_C.get();
	}

	private static BlockEntityType<StationRoundelBlock.StationRoundelBlockEntity> getBlockRoundelStationTopType() {
		return BLOCK_ROUNDEL_STATION_TOP.get();
	}

	@SuppressWarnings("unchecked")
	private static <T extends net.minecraft.world.level.block.entity.BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
			String name, Supplier<BlockEntityType<T>> supplier) {
		return (DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>) (DeferredHolder<?, ?>) BLOCK_ENTITY_TYPES.register(name, supplier);
	}
}
