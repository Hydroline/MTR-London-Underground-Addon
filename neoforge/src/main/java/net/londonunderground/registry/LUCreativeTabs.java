package net.londonunderground.registry;

import net.londonunderground.init.MainNeoForge;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class LUCreativeTabs {

	private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainNeoForge.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TFL_BLOCKS =
			CREATIVE_MODE_TABS.register("tfl_blocks", () -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.londonunderground.tfl_blocks"))
					.icon(() -> new ItemStack(LUBlocks.PIDS_NORTHERN.asItem()))
					.displayItems((parameters, output) -> LUBlocks.addBlocksTabItems(output::accept))
					.build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TFL_STATION =
			CREATIVE_MODE_TABS.register("tfl_station", () -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.londonunderground.tfl_station"))
					.icon(() -> new ItemStack(LUBlocks.TUNNEL_A2_SIGNAL.asItem()))
					.displayItems((parameters, output) -> LUBlocks.addStationTabItems(output::accept))
					.build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TFL_SIGNS =
			CREATIVE_MODE_TABS.register("tfl_signs", () -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.londonunderground.tfl_signs"))
					.icon(() -> new ItemStack(LUBlocks.BLOCK_ROUNDEL_1.asItem()))
					.displayItems((parameters, output) -> LUBlocks.addSignsTabItems(output::accept))
					.build());

	private LUCreativeTabs() {
	}

	public static void register(IEventBus modEventBus) {
		CREATIVE_MODE_TABS.register(modEventBus);
	}
}
