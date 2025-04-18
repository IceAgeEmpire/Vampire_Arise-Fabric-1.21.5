package net.iceageempire.vampirearise.world;

import net.iceageempire.vampirearise.VampireArise;
import net.iceageempire.vampirearise.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_SMALL = registerKey("ruby_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_LARGE = registerKey("ruby_ore_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_RUBY_ORE_LARGE = registerKey("nether_ruby_ore_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_RAW_RUBY_BLOCK = registerKey("nether_raw_ruby_block");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        List<OreFeatureConfig.Target> overworldRubyOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RUBY_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> netherRubyOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.NETHER_RUBY_ORE.getDefaultState()));
        
        register(context, RUBY_ORE_SMALL, Feature.ORE, new OreFeatureConfig(overworldRubyOres,5, 0.5F));
        register(context, RUBY_ORE_LARGE, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 8, 0.25F));
        register(context, NETHER_RUBY_ORE_LARGE, Feature.ORE, new OreFeatureConfig(netherRubyOres, 8, 0.5F));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(VampireArise.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
