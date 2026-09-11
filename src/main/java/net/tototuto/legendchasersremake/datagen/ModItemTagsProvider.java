package net.tototuto.legendchasersremake.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, "legend_chasers_remake", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Ajoute la planche au tag d'items "planks" (vanilla et forge)
        this.tag(ItemTags.PLANKS)
                .add(LegendChasersRemakeModBlocks.PROTIUM_PLANKS.get().asItem());

    }
}