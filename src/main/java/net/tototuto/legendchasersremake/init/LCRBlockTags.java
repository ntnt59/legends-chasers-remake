package net.tototuto.legendchasersremake.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;

public class LCRBlockTags {
    public static class Blocks {
        public static final TagKey<Block> IS_LCR_PLANTABLE = tag("is_lcr_plantable");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LegendChasersRemakeMod.MODID, name));
        }
    }
}