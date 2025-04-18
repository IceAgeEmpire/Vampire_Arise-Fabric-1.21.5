package net.iceageempire.vampirearise.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.iceageempire.vampirearise.world.ModPlacedFeatures;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.RUBY_ORE_PLACED_SMALL_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.RUBY_ORE_PLACED_LARGE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.RUBY_ORE_NETHER_KEY);

        // Example for individual Biomes
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PALE_GARDEN), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.RUBY_ORE_PLACED_SMALL_KEY);
    }
}
