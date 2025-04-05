package net.iceageempire.vampirearise;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.iceageempire.vampirearise.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class VampireAriseDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModTagGenerator::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		//pack.addProvider(ModChestLootTableProvider::new);
	}

	private static class ModTagGenerator extends FabricTagProvider.ItemTagProvider {
		public ModTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}

		private static final TagKey<Item> MAGIC_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("vampirearise:magic_items"));

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(MAGIC_ITEMS)
					.add(ModItems.RUBY_POTATO)
					.add(ModItems.DECAY_WAND);
					//.add(Items.ROTTEN_FLESH)
					//.addOptionalTag(ItemTags.DIRT);
		}
	}

	public static class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
		protected ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, registryLookup);
		}

		@Override
		public void generate() {
			addDrop(ModBlocks.RUBY_BLOCK);
			addDrop(ModBlocks.RAW_RUBY_BLOCK);
			addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE,ModItems.RAW_RUBY));
			addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE,ModItems.RAW_RUBY));
		}
	}

	public static class ModModelProvider extends FabricModelProvider{

		public ModModelProvider(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);
			blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_RUBY_BLOCK);
			blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
			blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
			blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TURRET_BLOCK);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
			itemModelGenerator.register(ModItems.RAW_RUBY, Models.GENERATED);
			itemModelGenerator.register(ModItems.RUBY_POTATO, Models.GENERATED);
			itemModelGenerator.register(ModItems.RUBY_SWORD, Models.HANDHELD);
			itemModelGenerator.register(ModItems.DECAY_WAND, Models.HANDHELD);
		}
	}

	/*
	public static class ModChestLootTableProvider extends SimpleFabricLootTableProvider {
		public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(output, registryLookup, LootContextTypes.CHEST);
		}

		public static final RegistryKey<LootTable> TEST_CHEST = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "desert_pyramid"));

		@Override
		public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
			lootTableBiConsumer.accept(TEST_CHEST, LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
							.with(ItemEntry.builder(ModItems.RUBY)
									.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
							.with(ItemEntry.builder(ModItems.RUBY)))
			);
		}
	}
	 */
}
