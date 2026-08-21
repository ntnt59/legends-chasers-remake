package net.tototuto.legendchasersremake.datagen;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "legend_chasers_remake", exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_COBBLESTONE);
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_STONE);
    }

    // Helper qui crée en 1 ligne : Blockstate + Modèle 3D + Modèle de l'Item d'inventaire
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}