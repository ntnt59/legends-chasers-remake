package net.tototuto.legendchasersremake.datagen;

import net.minecraftforge.common.Tags;
import net.tototuto.legendchasersremake.init.LCRBlockTags;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "legend_chasers_remake", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.PLANKS)
                .add(LegendChasersRemakeModBlocks.PROTIUM_PLANKS.get());

        // Définir l'outil nécessaire
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(LegendChasersRemakeModBlocks.GRAPHITE_ORE.get(),
                        LegendChasersRemakeModBlocks.IRON_FRAME.get(),
                        LegendChasersRemakeModBlocks.FOUNDRY_CASING.get(),
                        LegendChasersRemakeModBlocks.FOUNDRY.get(),
                        LegendChasersRemakeModBlocks.PRISS_STONE.get(),
                        LegendChasersRemakeModBlocks.PRISS_COBBLESTONE.get(),
                        LegendChasersRemakeModBlocks.AQUAMARINE_BLOCK.get(),
                        LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get());

        this.tag(BlockTags.FENCES)
                .add(LegendChasersRemakeModBlocks.PROTIUM_FENCE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(LegendChasersRemakeModBlocks.PROTIUM_LOG.get(),
                        LegendChasersRemakeModBlocks.PROTIUM_PLANKS.get(),
                        LegendChasersRemakeModBlocks.PROTIUM_FENCE.get(),
                        LegendChasersRemakeModBlocks.PROTIUM_FENCE_GATE.get(),
                        LegendChasersRemakeModBlocks.PROTIUM_SLAB.get(),
                        LegendChasersRemakeModBlocks.PROTIUM_STAIR.get());

        // Définir le niveau de minerai requis

        // FER
        this.tag(BlockTags.NEEDS_IRON_TOOL);

        // PIERRE
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(LegendChasersRemakeModBlocks.IRON_FRAME.get(),
                        LegendChasersRemakeModBlocks.FOUNDRY_CASING.get(),
                        LegendChasersRemakeModBlocks.FOUNDRY.get());

        this.tag(LCRBlockTags.Blocks.IS_LCR_PLANTABLE)
                .add(LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get());
        /*
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add();
         */
    }
}