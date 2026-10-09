package net.tototuto.legendchasersremake.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.worldgen.feature.DoublePlantFeature;
import net.tototuto.legendchasersremake.worldgen.feature.GiantCanopyTreeFeature;
import net.tototuto.legendchasersremake.worldgen.feature.LCRCoralTreeFeature;

public class LCRFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, LegendChasersRemakeMod.MODID);

    public static final RegistryObject<Feature<TreeConfiguration>> GIANT_CANOPY_TREE =
            FEATURES.register("giant_canopy_tree", () -> new GiantCanopyTreeFeature(TreeConfiguration.CODEC));

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> DOUBLE_PLANT =
            FEATURES.register("double_plant", () -> new DoublePlantFeature(SimpleBlockConfiguration.CODEC));

    // Correction ici : le type doit être Feature<SimpleBlockConfiguration> et non LCRCoralTreeFeature
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LCR_CORAL_TREE_FEATURE =
            FEATURES.register("lcr_coral_tree_feature", () -> new LCRCoralTreeFeature(NoneFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}