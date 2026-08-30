package net.tototuto.legendchasersremake.datagen;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.tototuto.legendchasersremake.init.LCRBiomes;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_GRAPHITE_ORE = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_graphite_ore"));

    public static final ResourceKey<BiomeModifier> ADD_PRISS_GRASS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_priss_grass"));
    public static final ResourceKey<BiomeModifier> ADD_BLUE_PRISS_GRASS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_blue_priss_grass"));
    public static final ResourceKey<BiomeModifier> ADD_PURPLE_PRISS_GRASS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_purple_priss_grass"));
    public static final ResourceKey<BiomeModifier> ADD_PINK_PRISS_GRASS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_pink_priss_grass"));
    public static final ResourceKey<BiomeModifier> ADD_GREEN_PRISS_GRASS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "add_green_priss_grass"));

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        // 1. Ajout du minerai de graphite
        context.register(ADD_GRAPHITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GRAPHITE_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        // 2. Ajout de l'herbe au biome spécifique
        context.register(ADD_PRISS_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), // Il faut HolderSet.direct() !
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PRISS_GRASS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_BLUE_PRISS_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), // Il faut HolderSet.direct() !
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLUE_PRISS_GRASS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_PURPLE_PRISS_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), // Il faut HolderSet.direct() !
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PURPLE_PRISS_GRASS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_PINK_PRISS_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), // Il faut HolderSet.direct() !
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINK_PRISS_GRASS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_GREEN_PRISS_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), // Il faut HolderSet.direct() !
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GREEN_PRISS_GRASS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}