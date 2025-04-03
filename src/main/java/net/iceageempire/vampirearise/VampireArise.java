package net.iceageempire.vampirearise;

import net.fabricmc.api.ModInitializer;

import net.iceageempire.vampirearise.block.ModBlocks;
import net.iceageempire.vampirearise.item.ModItemGroups;
import net.iceageempire.vampirearise.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VampireArise implements ModInitializer {
	public static final String MOD_ID = "vampirearise";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}