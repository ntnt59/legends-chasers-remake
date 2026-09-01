package net.tototuto.legendchasersremake.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "legend_chasers_remake", exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_COBBLESTONE);
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_STONE);
        blockWithItem(LegendChasersRemakeModBlocks.AQUAMARINE_BLOCK);

        // Grande herbe à 2 blocs
        tallGrassBlock1(LegendChasersRemakeModBlocks.TALL_PRISS_GRASS, "tall_priss_grass");
        tallGrassBlock2(LegendChasersRemakeModBlocks.TALL_RAINBOW_PRISS_GRASS, "tall_rainbow_priss_grass");

        // TES AUTRES BLOCS (Priss Grass, etc.)
        ModelFile modelA = models().cross("priss_grass_a", modLoc("block/priss_grass_a")).renderType("cutout");
        ModelFile modelB = models().cross("priss_grass_b", modLoc("block/priss_grass_b")).renderType("cutout");
        ModelFile modelC = models().cross("priss_grass_c", modLoc("block/priss_grass_c")).renderType("cutout");
        getVariantBuilder(LegendChasersRemakeModBlocks.PRISS_GRASS.get())
                .partialState()
                .addModels(
                        new ConfiguredModel(modelA, 0, 0, false, 1),
                        new ConfiguredModel(modelB, 0, 0, false, 1),
                        new ConfiguredModel(modelC, 0, 0, false, 1)
                );
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.PRISS_GRASS.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/priss_grass_b")
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

        simpleBlock(LegendChasersRemakeModBlocks.FLOROPHYTE.get(), models().cross(
                LegendChasersRemakeModBlocks.FLOROPHYTE.getId().getPath(),
                modLoc("block/florophyte")
        ).renderType("cutout"));
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.FLOROPHYTE.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/florophyte")
        );

        simpleBlock(LegendChasersRemakeModBlocks.PARAPHYTE.get(), models().cross(
                LegendChasersRemakeModBlocks.PARAPHYTE.getId().getPath(),
                modLoc("block/paraphyte")
        ).renderType("cutout"));
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.PARAPHYTE.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/paraphyte")
        );

        simpleBlock(LegendChasersRemakeModBlocks.BUBBLEPHYTE.get(), models().cross(
                LegendChasersRemakeModBlocks.BUBBLEPHYTE.getId().getPath(),
                modLoc("block/bubblephyte")
        ).renderType("cutout"));
        itemModels().singleTexture(
                LegendChasersRemakeModBlocks.BUBBLEPHYTE.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/bubblephyte")
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

    private void tallGrassBlock1(RegistryObject<Block> blockReg, String name) {
        ModelFile bottomModel = models().cross(name + "_bottom", modLoc("block/" + name + "_bottom")).renderType("cutout");
        ModelFile topModel = models().cross(name + "_top", modLoc("block/" + name + "_top")).renderType("cutout");

        getVariantBuilder(blockReg.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(bottomModel).addModel()
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(topModel).addModel();

        itemModels().singleTexture(
                blockReg.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/" + name + "_top")
        );
    }

    private void tallGrassBlock2(RegistryObject<Block> blockReg, String name) {
        ModelFile bottomModel = models().cross(name + "_bottom", modLoc("block/" + name + "_bottom")).renderType("cutout");
        ModelFile topModel = models().cross(name + "_top", modLoc("block/" + name + "_top")).renderType("cutout");

        getVariantBuilder(blockReg.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(bottomModel).addModel()
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(topModel).addModel();

        itemModels().singleTexture(
                blockReg.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/" + name + "_top")
        );
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}