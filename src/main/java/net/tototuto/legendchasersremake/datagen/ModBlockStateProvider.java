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
        blockWithItem(LegendChasersRemakeModBlocks.AQUAMARINE_BLOCK);


        simpleBlock(LegendChasersRemakeModBlocks.PRISS_GRASS.get(), models().cross(
                LegendChasersRemakeModBlocks.PRISS_GRASS.getId().getPath(),
                modLoc("block/priss_grass")
        ).renderType("cutout"));
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.PRISS_GRASS.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/priss_grass")
        );


        simpleBlock(LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.get(), models().cross(
                LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.getId().getPath(),
                modLoc("block/blue_priss_grass")
        ).renderType("cutout"));
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/blue_priss_grass")
        );

        simpleBlock(LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.get(), models().cross(
                                LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.getId().getPath(),
                            modLoc("block/purple_priss_grass")
                        ).renderType("cutout"));
                        itemModels().singleTexture(
                                LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.getId().getPath(),
                                mcLoc("item/generated"),
                                "layer0",
                                modLoc("block/purple_priss_grass")
                        );

                                simpleBlock(LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.get(), models().cross(
                                                LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.getId().getPath(),
                                            modLoc("block/pink_priss_grass")
                                        ).renderType("cutout"));
                                        itemModels().singleTexture(
                                                LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.getId().getPath(),
                                                mcLoc("item/generated"),
                                                "layer0",
                                                modLoc("block/pink_priss_grass")
                                        );

                                                simpleBlock(LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.get(), models().cross(
                                                                LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.getId().getPath(),
                                                            modLoc("block/green_priss_grass")
                                                        ).renderType("cutout"));
                                                        itemModels().singleTexture(
                                                                LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.getId().getPath(),
                                                                mcLoc("item/generated"),
                                                                "layer0",
                                                                modLoc("block/green_priss_grass")
                                                        );

        String name = LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.getId().getPath();
        simpleBlockWithItem(
                LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get(),
                models().cube(
                        name,
                        modLoc("block/priss_stone"),
                        modLoc("block/" + name + "_top"),
                        modLoc("block/" + name + "_side"),
                        modLoc("block/" + name + "_side"),
                        modLoc("block/" + name + "_side"),
                        modLoc("block/" + name + "_side")
                ).texture("particle", modLoc("block/" + name + "_side"))
        );
        blockWithItem(LegendChasersRemakeModBlocks.PROTIUM_LEAVES);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}