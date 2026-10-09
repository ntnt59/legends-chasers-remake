package net.tototuto.legendchasersremake.datagen;

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
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LCRBiomes;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_GRAPHITE_ORE = registerKey("add_graphite_ore");
    public static final ResourceKey<BiomeModifier> ADD_PRISS_GRASS_PATCH = registerKey("add_priss_grass_patch");
    public static final ResourceKey<BiomeModifier> ADD_MIXED_TALL_PRISS_GRASS_PATCH = registerKey("add_mixed_tall_priss_grass_patch");
    public static final ResourceKey<BiomeModifier> ADD_PARAPHYTE = registerKey("add_paraphyte");
    public static final ResourceKey<BiomeModifier> ADD_FLOROPHYTE = registerKey("add_florophyte");
    public static final ResourceKey<BiomeModifier> ADD_BUBBLEPHYTE = registerKey("add_bubblephyte");
    public static final ResourceKey<BiomeModifier> ADD_PROTIUM_SPRUCE = registerKey("add_protium_spruce");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        // Minerai
        context.register(ADD_GRAPHITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GRAPHITE_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        // Tapis d'herbes basses
        context.register(ADD_PRISS_GRASS_PATCH, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PRISS_GRASS_PATCH_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // Injection UNIQUE des patchs de hautes herbes mélangées
        context.register(ADD_MIXED_TALL_PRISS_GRASS_PATCH, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MIXED_TALL_PRISS_GRASS_PATCH_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        // Plantes rares & Arbres
        context.register(ADD_PARAPHYTE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PARAPHYTE_KEY)), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_FLOROPHYTE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FLOROPHYTE_KEY)), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_BUBBLEPHYTE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BUBBLEPHYTE_KEY)), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_PROTIUM_SPRUCE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(biomes.getOrThrow(LCRBiomes.PRISSAZ_PLAIN)), HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PROTIUM_SPRUCE_PLACED)), GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}