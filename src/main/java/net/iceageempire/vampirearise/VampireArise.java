package net.iceageempire.vampirearise;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.iceageempire.vampirearise.component.ModDataComponentTypes;
import net.iceageempire.vampirearise.item.ModItemGroups;
import net.iceageempire.vampirearise.item.ModItems;
import net.iceageempire.vampirearise.loot_table.ModLootTables;
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
		ModLootTables.modifyLootTables();
		ModDataComponentTypes.registerDataComponentTypes();

		FuelRegistryEvents.BUILD.register((builder, context) ->
				builder.add(ModItems.DECAY_WAND, 200));
	}
}