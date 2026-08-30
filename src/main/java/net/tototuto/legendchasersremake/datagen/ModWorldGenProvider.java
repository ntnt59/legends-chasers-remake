package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.worldgen.dimension.LegendsChasersRemakeDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // 1. Biomes
            .add(Registries.BIOME, LCRBiomeBuilder::bootstrap)
            // 2. Features & Modifiers
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            // 3. Dimensions & Noise
            .add(Registries.NOISE_SETTINGS, LegendsChasersRemakeDimensions::bootstrapNoiseSettings)
            .add(Registries.DIMENSION_TYPE, LegendsChasersRemakeDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, LegendsChasersRemakeDimensions::bootstrapStem);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(LegendChasersRemakeMod.MODID));
    }
}