package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.tototuto.legendchasersremake.init.LCRBiomes;

public class LCRBiomeBuilder {

    // Méthode bootstrap indispensable pour le DataGen
    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        // Enregistre le biome dans le registre
        context.register(LCRBiomes.PRISSAZ_PLAIN, createPrissazPlain(features, carvers));
    }

    private static Biome createPrissazPlain(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0x8FA29C)       // Couleur du brouillard
                .skyColor(0x18A4D9)       // Couleur du ciel
                .waterColor(-14329397)     // Couleur de l'eau
                .waterFogColor(-14329397)  // Brouillard sous l'eau
                .grassColorOverride(-2875) // Herbe
                .foliageColorOverride(-13261698)
                .build();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(features, carvers);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7f)
                .downfall(1.0f)
                .specialEffects(effects)
                .generationSettings(generationSettings.build())
                .mobSpawnSettings(spawnSettings.build())
                .build();
    }
}