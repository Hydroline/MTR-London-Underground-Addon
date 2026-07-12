package net.londonunderground.init;

import net.londonunderground.render.RenderNorthernLinePIDS;
import net.londonunderground.render.RenderNameProjector;
import net.londonunderground.render.RenderRoundel;
import net.londonunderground.render.RenderTunnelSignalLight;
import net.londonunderground.registry.LUBlockEntities;
import net.londonunderground.registry.LUBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.mtr.neoforge.ModEventBusClient;
import org.mtr.render.RenderPIDS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(value = MainNeoForge.MOD_ID, dist = Dist.CLIENT)
public class MainNeoForgeClient {

	private static final Logger LOGGER = LoggerFactory.getLogger("MTR London Underground Addon");

	public MainNeoForgeClient(IEventBus modEventBus) {
		modEventBus.addListener((net.neoforged.fml.event.lifecycle.FMLClientSetupEvent event) -> event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.TUNNEL_BLOCK_2_SIGNAL.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.TUNNEL_A2_SIGNAL.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BRITISH_RAIL_UNDERGROUND.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_1.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_NLE.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_1_BIG.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_1_BIG_EVEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_2.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_2_BIG.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_2_BIG_EVEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_3.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_3_BIG.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_3_BIG_EVEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_4.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_4_BIG.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_4_BIG_EVEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_5.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_5_BIG.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_5_BIG_EVEN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.PIDS_NORTHERN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.PIDS_POLE.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_UNDERGROUND.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_OVERGROUND.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_DLR.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_TRAMS.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_POPPY.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_METRO.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_LIZZY.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_PRIDE.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.SIGN_RIVER.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.MORDEN_SIGN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.MORDEN_SIGN_DLR.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.MORDEN_SIGN_OVERGROUND.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.METROPOLITAN_SIGN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.ELIZABETH_SIGN.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_STATION.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_STATION_TYPE_B.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_STATION_TYPE_C.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.BLOCK_ROUNDEL_STATION_TOP.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(LUBlocks.NAME_PROJECTOR.get(), RenderType.cutout());
		}));

		ModEventBusClient.BLOCK_ENTITY_RENDERERS.add(event -> {
			event.registerBlockEntityRenderer(LUBlockEntities.PIDS_NORTHERN.get(), context -> new RenderNorthernLinePIDS());
			event.registerBlockEntityRenderer(LUBlockEntities.TUNNEL_BLOCK_2_SIGNAL.get(), context -> new RenderTunnelSignalLight<>(false, 0xFF00FF00));
			event.registerBlockEntityRenderer(LUBlockEntities.TUNNEL_A2_SIGNAL.get(), context -> new RenderTunnelSignalLight<>(false, 0xFF00FF00));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_1.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_NLE.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.495F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BRITISH_RAIL_UNDERGROUND.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0.09F, -0.480F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_1_BIG.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, 0, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_1_BIG_EVEN.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, -0.5F, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_2.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_2_BIG.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, 0, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_2_BIG_EVEN.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, -0.5F, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_3.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_3_BIG.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, 0, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_3_BIG_EVEN.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, -0.5F, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_4.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_4_BIG.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, 0, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_4_BIG_EVEN.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, -0.5F, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_5.get(), context -> new RenderRoundel<>(14 / 16F, 0.2F / 16, 0, 0, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_5_BIG.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, 0, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_5_BIG_EVEN.get(), context -> new RenderRoundel<>(28 / 16F, 0.4F / 16, -0.5F, 0.5F, -0.5F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_STATION.get(), context -> new RenderRoundel<>(22 / 13F, 0.22F / 13, 0, 0.21F, 1.046F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_STATION_TOP.get(), context -> new RenderRoundel<>(22 / 13F, 0.22F / 13, 0.40F, 0.250F, 1.577F, 30, 0xFF000D3D, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_STATION_TYPE_B.get(), context -> new RenderRoundel<>(22 / 13F, 0.22F / 13, 0.01F, -0.039F, 1.296F, 0, 0xFFB3B3B3, false, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.BLOCK_ROUNDEL_STATION_TYPE_C.get(), context -> new RenderRoundel<>(32 / 5F, 1.32F / 13, 0.01F, 1.739F, 0.596F, 30, 0xFF000000, false, "beeching"));
			event.registerBlockEntityRenderer(LUBlockEntities.NAME_PROJECTOR.get(), context -> new RenderNameProjector());
			event.registerBlockEntityRenderer(LUBlockEntities.MORDEN_SIGN.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10 / 16F, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.MORDEN_SIGN_DLR.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10 / 16F, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.MORDEN_SIGN_OVERGROUND.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10 / 16F, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.METROPOLITAN_SIGN.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10 / 16F, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.ELIZABETH_SIGN.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10 / 16F, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_RIVER.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_OVERGROUND.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_DLR.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_TRAMS.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_POPPY.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_METRO.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_LIZZY.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_UNDERGROUND.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
			event.registerBlockEntityRenderer(LUBlockEntities.SIGN_PRIDE.get(), context -> new RenderRoundel<>(15 / 16F, 0.2F / 16, 0, 10.2F / 16, 0.425F / 16, 0, 0xFFB3B3B3, true, "johnston"));
		});

		LOGGER.info("MTR London Underground Addon NeoForge client initialized");
	}
}
