package net.tototuto.legendchasersremake.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.worldgen.feature.GiantCanopyTreeFeature;

public class LCRFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, LegendChasersRemakeMod.MODID);

    public static final RegistryObject<Feature<TreeConfiguration>> GIANT_CANOPY_TREE =
            FEATURES.register("giant_canopy_tree", () -> new GiantCanopyTreeFeature(TreeConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}