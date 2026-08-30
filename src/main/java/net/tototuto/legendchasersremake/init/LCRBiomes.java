package net.tototuto.legendchasersremake.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;

public class LCRBiomes {
    public static final ResourceKey<Biome> PRISSAZ_PLAIN = register("prissaz_plain");

    public LCRBiomes() {
    }

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
    }
}
