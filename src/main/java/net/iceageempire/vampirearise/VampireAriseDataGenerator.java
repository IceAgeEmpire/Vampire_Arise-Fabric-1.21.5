package net.iceageempire.vampirearise;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.iceageempire.vampirearise.item.ModItems;
import net.iceageempire.vampirearise.util.ModTags;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;
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
		pack.addProvider(ModRecipeProvider::new);
		//pack.addProvider(ModChestLootTableProvider::new);
	}

	private static class ModTagGenerator extends FabricTagProvider.ItemTagProvider {
		public ModTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}

		private static final TagKey<Item> MAGIC_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("vampirearise:magic_items"));
		private static final TagKey<Item> TRANSFORMABLE_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of("vampirearise:transformable_items"));

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(MAGIC_ITEMS)
					.add(ModItems.RUBY_POTATO)
					.add(ModItems.DECAY_WAND);

			getOrCreateTagBuilder(TRANSFORMABLE_ITEMS)
					.add(ModItems.RUBY_POTATO)
					.add(ModItems.DECAY_WAND);

			getOrCreateTagBuilder(ModTags.Items.RUBY_REPAIR).add(ModItems.RUBY);
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
			itemModelGenerator.register(ModItems.RUBY_AXE, Models.HANDHELD);
			itemModelGenerator.register(ModItems.RUBY_HOE, Models.HANDHELD);
			itemModelGenerator.register(ModItems.RUBY_PICKAXE, Models.HANDHELD);
			itemModelGenerator.register(ModItems.RUBY_SHOVEL, Models.HANDHELD);
			itemModelGenerator.register(ModItems.RUBY_HAMMER, Models.HANDHELD);
		}
	}
	public static class ModRecipeProvider extends FabricRecipeProvider {
		public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
			return new RecipeGenerator(wrapperLookup, recipeExporter) {
				@Override
				public void generate() {
					List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY, ModBlocks.RUBY_ORE,
							ModBlocks.DEEPSLATE_RUBY_ORE);

					offerSmelting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.25f, 200, "ruby");
					offerBlasting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY, 0.25f, 100, "ruby");

					offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.RAW_RUBY, RecipeCategory.MISC, ModBlocks.RAW_RUBY_BLOCK);
					offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_PICKAXE)
							.pattern("OOO")
							.pattern(" # ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_AXE)
							.pattern(" OO")
							.pattern(" #O")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_AXE)
							.pattern("OO ")
							.pattern("O# ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter, VampireArise.MOD_ID + "/mirrored/ruby_axe_mirrored");


					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_SHOVEL)
							.pattern(" O ")
							.pattern(" # ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_HOE)
							.pattern(" OO")
							.pattern(" # ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_HOE)
							.pattern("OO ")
							.pattern(" # ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter, VampireArise.MOD_ID + "/mirrored/ruby_hoe_mirrored");

					createShaped(RecipeCategory.COMBAT, ModItems.RUBY_SWORD)
							.pattern(" O ")
							.pattern(" O ")
							.pattern(" # ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.DECAY_WAND)
							.pattern("  O")
							.pattern(" # ")
							.pattern("#  ")
							.input('O', ModItems.RUBY)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);


					createShaped(RecipeCategory.FOOD, ModItems.RUBY_POTATO)
							.pattern("###")
							.pattern("#O#")
							.pattern("###")
							.input('O', Items.POTATO)
							.input('#', ModItems.RUBY)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.COMBAT, ModBlocks.WACTHER_BLOCK)
							.pattern("###")
							.pattern("#O#")
							.pattern("###")
							.input('O', ModItems.RUBY)
							.input('#', Items.POLISHED_DEEPSLATE)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

					createShaped(RecipeCategory.TOOLS, ModItems.RUBY_HAMMER)
							.pattern("OOO")
							.pattern(" # ")
							.pattern(" # ")
							.input('O', ModBlocks.RUBY_BLOCK)
							.input('#', Items.STICK)
							.criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
							.offerTo(exporter);

//					createShapeless(RecipeCategory.MISC, ModItems.RAW_RUBY, 32)
//							.input(ModBlocks.MAGIC_BLOCK)
//							.criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
//							.offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(TutorialMod.MOD_ID, "raw_ruby_from_magic_block")));

//					offerSmithingTrimRecipe(ModItems.KAUPEN_SMITHING_TEMPLATE, RegistryKey.of(RegistryKeys.RECIPE,
//							Identifier.ofVanilla(getItemPath(ModItems.KAUPEN_SMITHING_TEMPLATE) + "_smithing_trim")));
				}
			};
		}
		@Override
		public String getName() {
			return "TutorialMod Recipes";
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
