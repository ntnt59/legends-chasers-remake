package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> GRAPHITE_ORE_PLACED = registerKey("graphite_ore");
    public static final ResourceKey<PlacedFeature> PRISS_GRASS_PLACED_KEY = registerKey("priss_grass_placed");
    public static final ResourceKey<PlacedFeature> BLUE_PRISS_GRASS_PLACED_KEY = registerKey("blue_priss_grass_placed");
    public static final ResourceKey<PlacedFeature> GREEN_PRISS_GRASS_PLACED_KEY = registerKey("green_priss_grass_placed");
    public static final ResourceKey<PlacedFeature> PURPLE_PRISS_GRASS_PLACED_KEY = registerKey("purple_priss_grass_placed");
    public static final ResourceKey<PlacedFeature> PINK_PRISS_GRASS_PLACED_KEY = registerKey("pink_priss_grass_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // 1. Minerai de Graphite
        context.register(GRAPHITE_ORE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GRAPHITE_ORE_CONFIGURED),
                List.of(
                        CountPlacement.of(15),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100)),
                        BiomeFilter.biome()
                )
        ));

        // 2. Herbe (Priss Grass)
        context.register(PRISS_GRASS_PLACED_KEY, new PlacedFeature(
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PRISS_GRASS_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        CountPlacement.of(UniformInt.of(5, 8)),
                        BiomeFilter.biome()
                )
        ));
        context.register(BLUE_PRISS_GRASS_PLACED_KEY, new PlacedFeature(
                        configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_PRISS_GRASS_KEY),
                        List.of(
                                RarityFilter.onAverageOnceEvery(1),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                                CountPlacement.of(UniformInt.of(2, 4)),
                                BiomeFilter.biome()
                        )
                ));
        context.register(PURPLE_PRISS_GRASS_PLACED_KEY, new PlacedFeature(
                        configuredFeatures.getOrThrow(ModConfiguredFeatures.PURPLE_PRISS_GRASS_KEY),
                        List.of(
                                RarityFilter.onAverageOnceEvery(1),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                                CountPlacement.of(UniformInt.of(1, 3)),
                                BiomeFilter.biome()
                        )
                ));
        context.register(PINK_PRISS_GRASS_PLACED_KEY, new PlacedFeature(
                        configuredFeatures.getOrThrow(ModConfiguredFeatures.PINK_PRISS_GRASS_KEY),
                        List.of(
                                RarityFilter.onAverageOnceEvery(1),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                                CountPlacement.of(UniformInt.of(1, 2)),
                                BiomeFilter.biome()
                        )
                ));
        context.register(GREEN_PRISS_GRASS_PLACED_KEY, new PlacedFeature(
                        configuredFeatures.getOrThrow(ModConfiguredFeatures.GREEN_PRISS_GRASS_KEY),
                        List.of(
                                RarityFilter.onAverageOnceEvery(1),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                                CountPlacement.of(UniformInt.of(2,4)),
                                BiomeFilter.biome()
                        )
                ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}