package net.tototuto.legendchasersremake.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
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
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LCRBiomes;
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
    public static final ResourceKey<NoiseGeneratorSettings> PRISSAZ_NOISE_SETTINGS = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, "prissaz_noise_settings")
    );

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(PRISSAZ_DIM_TYPE, new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                (double) 2 / 3,
                true,
                false,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    public static void bootstrapNoiseSettings(BootstapContext<NoiseGeneratorSettings> context) {
        NoiseGeneratorSettings overworldSettings = NoiseGeneratorSettings.overworld(context, false, false);
        NoiseSettings customNoiseSettings = NoiseSettings.create(-64, 384, 1, 2);

        // Détecteurs de liquide et de surface
        SurfaceRules.ConditionSource isAboveWater = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.ConditionSource isWaterDepth2 = SurfaceRules.waterStartCheck(-2, 0);
        SurfaceRules.ConditionSource isTrueSurface = SurfaceRules.abovePreliminarySurface();

        // Traitement des fonds marins (Sable et Gravier)
        SurfaceRules.RuleSource underwaterFloor = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.not(isWaterDepth2),
                        SurfaceRules.state(Blocks.GRAVEL.defaultBlockState())
                ),
                SurfaceRules.state(Blocks.SAND.defaultBlockState())
        );

        // Règles de surface
        SurfaceRules.RuleSource prissazSurfaceRule = SurfaceRules.sequence(
                // 1. Bedrock tout en bas (Y=-64 à -59)
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                        SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
                ),

                // 2. Biome PRISSAZ_PLAIN
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(LCRBiomes.PRISSAZ_PLAIN),
                        SurfaceRules.sequence(
                                // SURFACE DIRECTE (ON_FLOOR)
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                // SI Vraie Surface EXTERIEURE
                                                SurfaceRules.ifTrue(
                                                        isTrueSurface,
                                                        SurfaceRules.sequence(
                                                                // Si à l'air libre -> Nylium
                                                                SurfaceRules.ifTrue(
                                                                        isAboveWater,
                                                                        SurfaceRules.state(LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get().defaultBlockState())
                                                                ),
                                                                // Sinon (sous l'eau en surface) -> Sable/Gravier
                                                                underwaterFloor
                                                        )
                                                ),
                                                // SI GROTTE (Pas en vraie surface extérieure) -> Pierre Custom
                                                SurfaceRules.state(LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState())
                                        )
                                ),
                                // SOUS LE SOL (UNDER_FLOOR)
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        isTrueSurface,
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.not(isAboveWater),
                                                                SurfaceRules.state(Blocks.SANDSTONE.defaultBlockState())
                                                        )
                                                ),
                                                SurfaceRules.state(LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState())
                                        )
                                )
                        )
                ),

                // 3. Secours pierre sous terre
                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.state(LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState())
                )
        );

        context.register(PRISSAZ_NOISE_SETTINGS, new NoiseGeneratorSettings(
                customNoiseSettings,
                LegendChasersRemakeModBlocks.PRISS_STONE.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                overworldSettings.noiseRouter(),
                prissazSurfaceRule,
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

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(LCRBiomes.PRISSAZ_PLAIN))
                        ))
                ),
                noiseGenSettings.getOrThrow(PRISSAZ_NOISE_SETTINGS)
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(PRISSAZ_DIM_TYPE), noiseBasedChunkGenerator);
        context.register(PRISSAZ_KEY, stem);
    }
}