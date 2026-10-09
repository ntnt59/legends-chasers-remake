package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LCRFeatures;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPHITE_ORE_CONFIGURED = registerKey("graphite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRISS_GRASS_PATCH_KEY = registerKey("priss_grass_patch");

    // Patch unique de HAUTES HERBES MÉLANGÉES
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIXED_TALL_PRISS_GRASS_PATCH_KEY = registerKey("mixed_tall_priss_grass_patch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PARAPHYTE_KEY = registerKey("paraphyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOROPHYTE_KEY = registerKey("florophyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUBBLEPHYTE_KEY = registerKey("bubblephyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PROTIUM_SPRUCE_KEY = registerKey("protium_spruce");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> graphiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, LegendChasersRemakeModBlocks.GRAPHITE_ORE.get().defaultBlockState())
        );
        context.register(GRAPHITE_ORE_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(graphiteOres, 9)));

        // 1. Patchs d'herbes courtes (Fond de prairie)
        context.register(PRISS_GRASS_PATCH_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new WeightedStateProvider(
                                        SimpleWeightedRandomList.<BlockState>builder()
                                                .add(LegendChasersRemakeModBlocks.PRISS_GRASS.get().defaultBlockState(), 25)
                                                .add(LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.get().defaultBlockState(), 5)
                                                .add(LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.get().defaultBlockState(), 5)
                                                .add(LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.get().defaultBlockState(), 4)
                                                .add(LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.get().defaultBlockState(), 4)
                                )
                        ),
                        List.of(),
                        96
                )
        ));

        // 2. PATCH STRICT & MÉLANGÉ DE HAUTES HERBES (Suivant la critique d'Antoine)
        // Note: Utilise LCRFeatures.DOUBLE_PLANT.get() si ce sont des blocs 2-cases, ou Feature.SIMPLE_BLOCK si 1-case.
        context.register(MIXED_TALL_PRISS_GRASS_PATCH_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK, // ou ta feature de pose
                        new SimpleBlockConfiguration(
                                new WeightedStateProvider(
                                        SimpleWeightedRandomList.<BlockState>builder()
                                                .add(LegendChasersRemakeModBlocks.TALL_PRISS_GRASS.get().defaultBlockState(), 80)
                                                .add(LegendChasersRemakeModBlocks.TALL_RAINBOW_PRISS_GRASS.get().defaultBlockState(), 20)
                                )
                        ),
                        List.of(),
                        24
                )
        ));

        // Plantes rares (-phytes)
        context.register(PARAPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(16, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PARAPHYTE.get()))))
        ));

        context.register(FLOROPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(16, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.FLOROPHYTE.get()))))
        ));

        context.register(BUBBLEPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(16, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.BUBBLEPHYTE.get()))))
        ));

        // Arbre
        context.register(PROTIUM_SPRUCE_KEY, new ConfiguredFeature<>(
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(LegendChasersRemakeModBlocks.PROTIUM_LOG.get()),
                        new StraightTrunkPlacer(5, 2, 0),
                        BlockStateProvider.simple(LegendChasersRemakeModBlocks.PROTIUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                        new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                        new TwoLayersFeatureSize(2, 0, 2)
                ).ignoreVines().build()
        ));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}