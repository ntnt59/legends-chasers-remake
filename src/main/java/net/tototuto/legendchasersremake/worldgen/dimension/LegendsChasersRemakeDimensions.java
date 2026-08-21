package net.tototuto.legendchasersremake.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.List;
import java.util.OptionalLong;

public class LegendsChasersRemakeDimensions {
    public static final ResourceKey<LevelStem> PRISSAZ_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz"));
    public static final ResourceKey<Level> PRISSAZ_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz"));
    public static final ResourceKey<DimensionType> PRISSAZ_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz_type"));

    public static void bootstapType(BootstapContext<DimensionType> context) {
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

    public static void bootstapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.PLAINS)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.PLAINS))
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED)
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(LegendsChasersRemakeDimensions.PRISSAZ_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(PRISSAZ_KEY, stem);
    }
}