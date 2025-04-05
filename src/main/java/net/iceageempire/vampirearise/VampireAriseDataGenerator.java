package net.iceageempire.vampirearise;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.iceageempire.vampirearise.item.ModItems;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static net.iceageempire.vampirearise.block.custom.WatcherBlock.CLICKED;
import static net.minecraft.client.data.BlockStateModelGenerator.createBooleanModelMap;
import static net.minecraft.client.data.BlockStateModelGenerator.createWeightedVariant;

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
			addDrop(ModBlocks.WACTHER_BLOCK);
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

			WeightedVariant weightedVariant = createWeightedVariant(TexturedModel.CUBE_ALL.upload(ModBlocks.WACTHER_BLOCK, blockStateModelGenerator.modelCollector));
			WeightedVariant weightedVariant2 = createWeightedVariant(blockStateModelGenerator.createSubModel(ModBlocks.WACTHER_BLOCK, "_on", Models.CUBE_ALL, TextureMap::all));

			blockStateModelGenerator.blockStateCollector
					.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.WACTHER_BLOCK).with(createBooleanModelMap(CLICKED, weightedVariant2, weightedVariant)));

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
