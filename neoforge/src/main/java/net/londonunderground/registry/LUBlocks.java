package net.londonunderground.registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.londonunderground.block.MordenSignBlock;
import net.londonunderground.block.MordenSignDlrBlock;
import net.londonunderground.block.MordenSignOvergroundBlock;
import net.londonunderground.block.NorthernPIDSBlock;
import net.londonunderground.block.NameProjectorBlock;
import net.londonunderground.block.ElizabethSignBlock;
import net.londonunderground.block.MetropolitanSignBlock;
import net.londonunderground.block.SignDlrBlock;
import net.londonunderground.block.SignLizzyBlock;
import net.londonunderground.block.SignMetroBlock;
import net.londonunderground.block.SignOvergroundBlock;
import net.londonunderground.block.SignPoppyBlock;
import net.londonunderground.block.SignPrideBlock;
import net.londonunderground.block.SignRiverBlock;
import net.londonunderground.block.SignTramsBlock;
import net.londonunderground.block.SignUndergroundBlock;
import net.londonunderground.block.StationRoundelBlock;
import net.londonunderground.block.TunnelA2SignalBlock;
import net.londonunderground.block.TunnelSignalBlock;
import net.londonunderground.block.WallRoundelBlock;
import net.londonunderground.init.MainNeoForge;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mtr.block.BlockPIDSPole;

public final class LUBlocks {

	private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MainNeoForge.MOD_ID);
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MainNeoForge.MOD_ID);
	private static final Map<String, DeferredItem<BlockItem>> BLOCK_ITEMS = new LinkedHashMap<>();

	public static final DeferredBlock<Block> PIDS_NORTHERN =
			register("pids_northern", () -> new NorthernPIDSBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.PIDS_NORTHERN.get()));
	public static final DeferredBlock<Block> PIDS_POLE =
			register("pids_pole", () -> new BlockPIDSPole(baseProperties().noOcclusion()));
	public static final DeferredBlock<Block> TUNNEL_A2_SIGNAL =
			register("tunnel_a2_signal", () -> new TunnelA2SignalBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.TUNNEL_A2_SIGNAL.get()));
	public static final DeferredBlock<Block> TUNNEL_BLOCK_2_SIGNAL =
			register("tunnel_block_2_signal", () -> new TunnelSignalBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.TUNNEL_BLOCK_2_SIGNAL.get()));
	public static final DeferredBlock<Block> NAME_PROJECTOR =
			register("name_projector", () -> new NameProjectorBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.NAME_PROJECTOR.get()));
	public static final DeferredBlock<Block> SIGN_UNDERGROUND =
			register("sign_underground", () -> new SignUndergroundBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_UNDERGROUND.get()));
	public static final DeferredBlock<Block> SIGN_OVERGROUND =
			register("sign_overground", () -> new SignOvergroundBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_OVERGROUND.get()));
	public static final DeferredBlock<Block> SIGN_DLR =
			register("sign_dlr", () -> new SignDlrBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_DLR.get()));
	public static final DeferredBlock<Block> SIGN_TRAMS =
			register("sign_trams", () -> new SignTramsBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_TRAMS.get()));
	public static final DeferredBlock<Block> SIGN_POPPY =
			register("sign_poppy", () -> new SignPoppyBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_POPPY.get()));
	public static final DeferredBlock<Block> SIGN_METRO =
			register("sign_metro", () -> new SignMetroBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_METRO.get()));
	public static final DeferredBlock<Block> SIGN_LIZZY =
			register("sign_lizzy", () -> new SignLizzyBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_LIZZY.get()));
	public static final DeferredBlock<Block> SIGN_PRIDE =
			register("sign_pride", () -> new SignPrideBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_PRIDE.get()));
	public static final DeferredBlock<Block> SIGN_RIVER =
			register("sign_river", () -> new SignRiverBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.SIGN_RIVER.get()));
	public static final DeferredBlock<Block> MORDEN_SIGN =
			register("morden_sign", () -> new MordenSignBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.MORDEN_SIGN.get()));
	public static final DeferredBlock<Block> MORDEN_SIGN_DLR =
			register("morden_sign_dlr", () -> new MordenSignDlrBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.MORDEN_SIGN_DLR.get()));
	public static final DeferredBlock<Block> MORDEN_SIGN_OVERGROUND =
			register("morden_sign_overground", () -> new MordenSignOvergroundBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.MORDEN_SIGN_OVERGROUND.get()));
	public static final DeferredBlock<Block> METROPOLITAN_SIGN =
			register("metropolitan_sign", () -> new MetropolitanSignBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.METROPOLITAN_SIGN.get()));
	public static final DeferredBlock<Block> ELIZABETH_SIGN =
			register("elizabeth_sign", () -> new ElizabethSignBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.ELIZABETH_SIGN.get()));
	public static final DeferredBlock<Block> BRITISH_RAIL_UNDERGROUND =
			register("british_rail_underground", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BRITISH_RAIL_UNDERGROUND.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_1 =
			register("block_roundel_1", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_1.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_NLE =
			register("block_roundel_nle", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_NLE.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_1_BIG =
			register("block_roundel_1_big", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_1_BIG.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_1_BIG_EVEN =
			register("block_roundel_1_big_even", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_1_BIG_EVEN.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_2 =
			register("block_roundel_2", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_2.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_2_BIG =
			register("block_roundel_2_big", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_2_BIG.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_2_BIG_EVEN =
			register("block_roundel_2_big_even", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_2_BIG_EVEN.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_3 =
			register("block_roundel_3", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_3.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_3_BIG =
			register("block_roundel_3_big", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_3_BIG.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_3_BIG_EVEN =
			register("block_roundel_3_big_even", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_3_BIG_EVEN.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_4 =
			register("block_roundel_4", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_4.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_4_BIG =
			register("block_roundel_4_big", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_4_BIG.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_4_BIG_EVEN =
			register("block_roundel_4_big_even", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_4_BIG_EVEN.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_5 =
			register("block_roundel_5", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_5.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_5_BIG =
			register("block_roundel_5_big", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_5_BIG.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_5_BIG_EVEN =
			register("block_roundel_5_big_even", () -> new WallRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_5_BIG_EVEN.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_STATION =
			register("block_roundel_station", () -> new StationRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_STATION.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_STATION_TYPE_B =
			register("block_roundel_station_type_b", () -> new StationRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_STATION_TYPE_B.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_STATION_TYPE_C =
			register("block_roundel_station_type_c", () -> new StationRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_STATION_TYPE_C.get()));
	public static final DeferredBlock<Block> BLOCK_ROUNDEL_STATION_TOP =
			register("block_roundel_station_top", () -> new StationRoundelBlock(baseProperties().noOcclusion(), () -> LUBlockEntities.BLOCK_ROUNDEL_STATION_TOP.get()));

	private LUBlocks() {
	}

	public static void register(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
	}

	public static void addCreativeTabItems(Consumer<Item> consumer) {
		BLOCK_ITEMS.values().forEach(item -> consumer.accept(item.get()));
	}

	private static DeferredBlock<Block> register(String name, Supplier<? extends Block> supplier) {
		final DeferredBlock<Block> block = BLOCKS.register(name, supplier);
		BLOCK_ITEMS.put(name, ITEMS.registerSimpleBlockItem(name, block));
		return block;
	}

	private static BlockBehaviour.Properties baseProperties() {
		return BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.METAL);
	}
}
