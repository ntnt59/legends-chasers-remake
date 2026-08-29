package net.tototuto.legendchasersremake.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

import java.util.List;
import java.util.OptionalLong;

public class LegendsChasersRemakeDimensions {
    public static final ResourceKey<LevelStem> PRISSAZ_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz"));
    public static final ResourceKey<Level> PRISSAZ_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz"));
    public static final ResourceKey<DimensionType> PRISSAZ_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz_type"));

    // 1. Définition de la clé pour vos paramètres de bruit personnalisés
    public static final ResourceKey<NoiseGeneratorSettings> PRISSAZ_NOISE_SETTINGS = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz_noise_settings")
    );

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(PRISSAZ_DIM_TYPE, new DimensionType(
                OptionalLong.empty(),
                false,
                false,
                false,
                true,
                (double) 2 / 3,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    // 2. Méthode pour enregistrer la configuration avec VOTRE BLOC
    public static void bootstrapNoiseSettings(BootstapContext<NoiseGeneratorSettings> context) {
        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);

        // On utilise la méthode publique d'usine de NoiseGeneratorSettings
        NoiseGeneratorSettings overworldSettings = NoiseGeneratorSettings.overworld(context, false, false);

        context.register(PRISSAZ_NOISE_SETTINGS, new NoiseGeneratorSettings(
                overworldSettings.noiseSettings(),
                LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState(), // Ton bloc custom
                Blocks.WATER.defaultBlockState(),
                overworldSettings.noiseRouter(),
                overworldSettings.surfaceRule(),
                overworldSettings.spawnTarget(),
                overworldSettings.seaLevel(),
                overworldSettings.disableMobGeneration(),
                overworldSettings.aquifersEnabled(),
                overworldSettings.oreVeinsEnabled(),
                overworldSettings.useLegacyRandomSource()
        ));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        // 3. On utilise 'PRISSAZ_NOISE_SETTINGS' au lieu de 'NoiseGeneratorSettings.AMPLIFIED'
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.PLAINS))
                        ))
                ),
                noiseGenSettings.getOrThrow(PRISSAZ_NOISE_SETTINGS) // <--- Utilise la clé personnalisée
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(PRISSAZ_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(PRISSAZ_KEY, stem);
    }
}