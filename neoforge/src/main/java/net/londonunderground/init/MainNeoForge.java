package net.londonunderground.init;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.londonunderground.registry.LUBlockEntities;
import net.londonunderground.registry.LUBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MainNeoForge.MOD_ID)
public class MainNeoForge {

	public static final String MOD_ID = "londonunderground";
	private static final Logger LOGGER = LoggerFactory.getLogger("MTR London Underground Addon");

	public MainNeoForge(IEventBus modEventBus) {
		LUBlocks.register(modEventBus);
		LUBlockEntities.register(modEventBus);
		LOGGER.info("MTR London Underground Addon NeoForge blocks registered");
	}
}
