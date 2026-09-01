package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_PRISS_GRASS_KEY = registerKey("blue_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_PRISS_GRASS_KEY = registerKey("green_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_PRISS_GRASS_KEY = registerKey("purple_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_PRISS_GRASS_KEY = registerKey("pink_priss_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARAPHYTE_KEY = registerKey("paraphyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOROPHYTE_KEY = registerKey("florophyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUBBLEPHYTE_KEY = registerKey("bubblephyte");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_CANOPY_TREE_KEY = registerKey("giant_canopy_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRISS_GRASS_PATCH_KEY = registerKey("priss_grass_patch_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        // 1. Minerai de Graphite
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> graphiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, LegendChasersRemakeModBlocks.GRAPHITE_ORE.get().defaultBlockState())
        );
        context.register(GRAPHITE_ORE_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(graphiteOres, 9)));

        // 2. Herbes (Priss Grass)
        context.register(PRISS_GRASS_PATCH_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new WeightedStateProvider(
                                        SimpleWeightedRandomList.<BlockState>builder()
                                                .add(LegendChasersRemakeModBlocks.PRISS_GRASS.get().defaultBlockState(), 16)
                                                .add(LegendChasersRemakeModBlocks.TALL_PRISS_GRASS.get().defaultBlockState(), 1)
                                                .add(LegendChasersRemakeModBlocks.TALL_RAINBOW_PRISS_GRASS.get().defaultBlockState(), 1)
                                )
                        ),
                        List.of(), // Utilise la survie naturelle (mayPlaceOn / wouldSurvive)
                        64         // 64 essais par patch
                )
        ));

        context.register(BLUE_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        64,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.get()))
                        )
                )
        ));

        context.register(GREEN_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        64,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.get()))
                        )
                )
        ));

        context.register(PURPLE_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        64,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.get()))
                        )
                )
        ));

        context.register(PINK_PRISS_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        64,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.get()))
                        )
                )
        ));
        context.register(PARAPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        32,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.PARAPHYTE.get())
                                )
                        )
                )
        ));
        context.register(FLOROPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        32,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.FLOROPHYTE.get())
                                )
                        )
                )
        ));
        context.register(BUBBLEPHYTE_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        32,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(LegendChasersRemakeModBlocks.BUBBLEPHYTE.get())
                                )
                        )
                )
        ));

        // 3. Arbre Géant (Giant Canopy Tree)
        context.register(GIANT_CANOPY_TREE_KEY, new ConfiguredFeature<>(
                LCRFeatures.GIANT_CANOPY_TREE.get(),
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(1, 0, 0),
                        BlockStateProvider.simple(
                                LegendChasersRemakeModBlocks.PROTIUM_LEAVES.get()
                                        .defaultBlockState()
                                        .setValue(LeavesBlock.PERSISTENT, true)
                        ),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        ));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}