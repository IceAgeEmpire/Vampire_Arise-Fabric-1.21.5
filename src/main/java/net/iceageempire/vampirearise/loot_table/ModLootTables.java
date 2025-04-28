package net.iceageempire.vampirearise.loot_table;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.iceageempire.vampirearise.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.loot.condition.RandomChanceLootCondition;


public class ModLootTables {
    private static final Identifier GRASS_BLOCK_ID
            = Identifier.of("minecraft", "blocks/short_grass");
    private static final Identifier WITCH_ID
            = Identifier.of("minecraft", "entities/witch");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
//            if (GRASS_BLOCK_ID.equals(key.getValue())) {
//                LootPool.Builder poolBuilder = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .conditionally(RandomChanceLootCondition.builder(0.25f)) // Drops 25% of the time
//                        .with(ItemEntry.builder(ModItems.RUBY))
//                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
//
//                tableBuilder.pool(poolBuilder.build());
//            }

            if (LootTables.VILLAGE_PLAINS_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.PINEAPPLE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.PINEAPPLE_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(3))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.OMINOUS_TRIAL_CHAMBER_KEY_SPAWNER.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 3f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.BURIED_TREASURE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(3))
                        .conditionally(RandomChanceLootCondition.builder(0.8f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.NETHER_BRIDGE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(.8f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(3))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (WITCH_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.RUBY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
