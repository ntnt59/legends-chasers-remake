package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPHITE_ORE_CONFIGURED = registerKey("graphite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRISS_GRASS_KEY = registerKey("priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_PRISS_GRASS_KEY = registerKey("blue_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_PRISS_GRASS_KEY = registerKey("green_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_PRISS_GRASS_KEY = registerKey("purple_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_PRISS_GRASS_KEY = registerKey("pink_priss_grass");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        // 1. Minerai de Graphite
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> graphiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, LegendChasersRemakeModBlocks.GRAPHITE_ORE.get().defaultBlockState())
        );
        context.register(GRAPHITE_ORE_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(graphiteOres, 9)));

        // 2. Herbe (Priss Grass)
        context.register(PRISS_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        32, // Nombre d'essais (tries)
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PRISS_GRASS.get()))
                        )
                )
        ));

        context.register(BLUE_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(
                                64, // Nombre d'essais (tries)
                                PlacementUtils.onlyWhenEmpty(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.get()))
                                )
                        )
                ));
        context.register(GREEN_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(
                                64, // Nombre d'essais (tries)
                                PlacementUtils.onlyWhenEmpty(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.get()))
                                )
                        )
                ));
        context.register(PURPLE_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(
                                64, // Nombre d'essais (tries)
                                PlacementUtils.onlyWhenEmpty(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.get()))
                                )
                        )
                ));
        context.register(PINK_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(
                                64, // Nombre d'essais (tries)
                                PlacementUtils.onlyWhenEmpty(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.get()))
                                )
                        )
                ));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}