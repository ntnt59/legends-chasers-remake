package net.tototuto.legendchasersremake.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.block.GlowingCoralBlock;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, "legend_chasers_remake", exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        registerPottedPlant(
                LegendChasersRemakeModBlocks.POTTED_PARAPHYTE.get(),
                LegendChasersRemakeModBlocks.PARAPHYTE.get()
        );
        registerPottedPlant(
                LegendChasersRemakeModBlocks.POTTED_FLOROPHYTE.get(),
                LegendChasersRemakeModBlocks.FLOROPHYTE.get()
        );
        registerPottedPlant(
                LegendChasersRemakeModBlocks.POTTED_BUBBLEPHYTE.get(),
                LegendChasersRemakeModBlocks.BUBBLEPHYTE.get()
        );

        // 1. On définit proprement les modèles de blocs pour qu'ils pointent vers Minecraft (et non vers eux-mêmes)
        models().withExistingParent("priss_stone_button", mcLoc("block/button"))
                .texture("texture", modLoc("block/priss_stone"));

        models().withExistingParent("priss_stone_button_pressed", mcLoc("block/button_pressed"))
                .texture("texture", modLoc("block/priss_stone"));

        // 2. Ton code que tu veux garder
        buttonBlock(
                (ButtonBlock) LegendChasersRemakeModBlocks.PRISS_STONE_BUTTON.get(),
                modLoc("block/priss_stone")
        );

        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_STONE_BUTTON.get(),
                models().withExistingParent(
                        LegendChasersRemakeModBlocks.PRISS_STONE_BUTTON.getId().getPath(),
                        mcLoc("block/button")
                ).texture("texture", modLoc("block/priss_stone"))
        );

        blockWithItem(LegendChasersRemakeModBlocks.PRISS_COBBLESTONE);
        blockWithItem(LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE);
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_STONE);
        blockWithItem(LegendChasersRemakeModBlocks.AQUAMARINE_BLOCK);
        blockWithItem(LegendChasersRemakeModBlocks.PROTIUM_PLANKS);
        blockWithItem(LegendChasersRemakeModBlocks.PRISS_BRICK);
        blockWithItem(LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK);
        logBlockWithItem(LegendChasersRemakeModBlocks.PROTIUM_LOG);
        logBlockWithItem(LegendChasersRemakeModBlocks.STRIPPED_PROTIUM_LOG);

        getVariantBuilder(LegendChasersRemakeModBlocks.ABYSSAL_CORAL_BLOCK.get())
                .forAllStates(state -> {
                    int light = state.getValue(GlowingCoralBlock.AMBIENT_LIGHT);
                    // Si light vaut 0 (la nuit), on met la texture lumineuse. Sinon (15), la normale.
                    String textureName = light == 0 ? "bioluminescent_abyssal_coral_block" : "abyssal_coral_block";

                    return ConfiguredModel.builder()
                            .modelFile(models().cubeAll(textureName, modLoc("block/" + textureName)))
                            .build();
                });

        // ##################

        String trapdoorName = LegendChasersRemakeModBlocks.PROTIUM_TRAPDOOR.getId().getPath();
        ModelFile trapdoorBottom = models().trapdoorBottom(trapdoorName + "_bottom", modLoc("block/" + trapdoorName)).renderType("cutout");
        ModelFile trapdoorTop = models().trapdoorTop(trapdoorName + "_top", modLoc("block/" + trapdoorName)).renderType("cutout");
        ModelFile trapdoorOpen = models().trapdoorOpen(trapdoorName + "_open", modLoc("block/" + trapdoorName)).renderType("cutout");

        // Utilisation de la méthode avec les modèles explicitement définis
        this.trapdoorBlock(
                (TrapDoorBlock) LegendChasersRemakeModBlocks.PROTIUM_TRAPDOOR.get(),
                trapdoorBottom,
                trapdoorTop,
                trapdoorOpen,
                true
        );

        // ##########################

        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.PRISS_BRICK_STAIR.get(),
                modLoc("block/priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_BRICK_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.PRISS_BRICK_STAIR.getId().getPath(),
                        modLoc("block/priss_brick"),
                        modLoc("block/priss_brick"),
                        modLoc("block/priss_brick")
                )
        );
        slabBlock(
                (SlabBlock) LegendChasersRemakeModBlocks.PRISS_BRICK_SLAB.get(),
                modLoc("block/priss_brick"),
                modLoc("block/priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_BRICK_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.PRISS_BRICK_SLAB.getId().getPath(),
                        modLoc("block/priss_brick"),
                        modLoc("block/priss_brick"),
                        modLoc("block/priss_brick")
                )
        );

        wallBlock(
                (WallBlock) LegendChasersRemakeModBlocks.PRISS_BRICK_WALL.get(),
                modLoc("block/priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_BRICK_WALL.get(),
                models().wallInventory(
                        LegendChasersRemakeModBlocks.PRISS_BRICK_WALL.getId().getPath(),
                        modLoc("block/priss_brick")
                )
        );
        // ##########################

        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_STAIR.get(),
                modLoc("block/mossy_priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_STAIR.getId().getPath(),
                        modLoc("block/mossy_priss_brick"),
                        modLoc("block/mossy_priss_brick"),
                        modLoc("block/mossy_priss_brick")
                )
        );
        slabBlock(
                (SlabBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_SLAB.get(),
                modLoc("block/mossy_priss_brick"),
                modLoc("block/mossy_priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_SLAB.getId().getPath(),
                        modLoc("block/mossy_priss_brick"),
                        modLoc("block/mossy_priss_brick"),
                        modLoc("block/mossy_priss_brick")
                )
        );

        wallBlock(
                (WallBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_WALL.get(),
                modLoc("block/mossy_priss_brick")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_WALL.get(),
                models().wallInventory(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_BRICK_WALL.getId().getPath(),
                        modLoc("block/mossy_priss_brick")
                )
        );

        // ##########################

        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_STAIR.get(),
                modLoc("block/mossy_priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_STAIR.getId().getPath(),
                        modLoc("block/mossy_priss_cobblestone"),
                        modLoc("block/mossy_priss_cobblestone"),
                        modLoc("block/mossy_priss_cobblestone")
                )
        );
        slabBlock(
                (SlabBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_SLAB.get(),
                modLoc("block/mossy_priss_cobblestone"),
                modLoc("block/mossy_priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_SLAB.getId().getPath(),
                        modLoc("block/mossy_priss_cobblestone"),
                        modLoc("block/mossy_priss_cobblestone"),
                        modLoc("block/mossy_priss_cobblestone")
                )
        );

        wallBlock(
                (WallBlock) LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_WALL.get(),
                modLoc("block/mossy_priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_WALL.get(),
                models().wallInventory(
                        LegendChasersRemakeModBlocks.MOSSY_PRISS_COBBLESTONE_WALL.getId().getPath(),
                        modLoc("block/mossy_priss_cobblestone")
                )
        );

        // ##########################

        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_STAIR.get(),
                modLoc("block/priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_STAIR.getId().getPath(),
                        modLoc("block/priss_cobblestone"),
                        modLoc("block/priss_cobblestone"),
                        modLoc("block/priss_cobblestone")
                )
        );
        slabBlock(
                (SlabBlock) LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_SLAB.get(),
                modLoc("block/priss_cobblestone"),
                modLoc("block/priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_SLAB.getId().getPath(),
                        modLoc("block/priss_cobblestone"),
                        modLoc("block/priss_cobblestone"),
                        modLoc("block/priss_cobblestone")
                )
        );

        wallBlock(
                (WallBlock) LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_WALL.get(),
                modLoc("block/priss_cobblestone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_WALL.get(),
                models().wallInventory(
                        LegendChasersRemakeModBlocks.PRISS_COBBLESTONE_WALL.getId().getPath(),
                        modLoc("block/priss_cobblestone")
                )
        );

        // ##########################

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

        this.doorBlock(
                (DoorBlock) LegendChasersRemakeModBlocks.PROTIUM_DOOR.get(),
                modLoc("block/protium_door_bottom"),
                modLoc("block/protium_door_top")
        );

        // ###############################

        fenceBlock(
                (net.minecraft.world.level.block.FenceBlock) LegendChasersRemakeModBlocks.PROTIUM_FENCE.get(),
                modLoc("block/protium_planks")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PROTIUM_FENCE.get(),
                models().fenceInventory(
                        LegendChasersRemakeModBlocks.PROTIUM_FENCE.getId().getPath() + "_inventory",
                        modLoc("block/protium_planks")
                )
        );

        fenceGateBlock(
                (FenceGateBlock) LegendChasersRemakeModBlocks.PROTIUM_FENCE_GATE.get(),
                modLoc("block/protium_planks")
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

        // #################################

        slabBlock(
                (net.minecraft.world.level.block.SlabBlock) LegendChasersRemakeModBlocks.PROTIUM_SLAB.get(),
                modLoc("block/protium_planks"),
                modLoc("block/protium_planks")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PROTIUM_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.PROTIUM_SLAB.getId().getPath(),
                        modLoc("block/protium_planks"),
                        modLoc("block/protium_planks"),
                        modLoc("block/protium_planks")
                )
        );
        slabBlock(
                (net.minecraft.world.level.block.SlabBlock) LegendChasersRemakeModBlocks.PRISS_STONE_SLAB.get(),
                modLoc("block/priss_stone"),
                modLoc("block/priss_stone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_STONE_SLAB.get(),
                models().slab(
                        LegendChasersRemakeModBlocks.PRISS_STONE_SLAB.getId().getPath(),
                        modLoc("block/priss_stone"),
                        modLoc("block/priss_stone"),
                        modLoc("block/priss_stone")
                )
        );

        pressurePlateBlock(
                (net.minecraft.world.level.block.PressurePlateBlock) LegendChasersRemakeModBlocks.PRISS_STONE_PRESSURE_PLATE.get(),
                modLoc("block/priss_stone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_STONE_PRESSURE_PLATE.get(),
                models().pressurePlate(
                        LegendChasersRemakeModBlocks.PRISS_STONE_PRESSURE_PLATE.getId().getPath(),
                        modLoc("block/priss_stone")
                )
        );

        // ###############################

        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.PROTIUM_STAIR.get(),
                modLoc("block/protium_planks")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PROTIUM_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.PROTIUM_STAIR.getId().getPath(),
                        modLoc("block/protium_planks"),
                        modLoc("block/protium_planks"),
                        modLoc("block/protium_planks")
                )
        );
        stairsBlock(
                (net.minecraft.world.level.block.StairBlock) LegendChasersRemakeModBlocks.PRISS_STONE_STAIR.get(),
                modLoc("block/priss_stone")
        );
        simpleBlockItem(
                LegendChasersRemakeModBlocks.PRISS_STONE_STAIR.get(),
                models().stairs(
                        LegendChasersRemakeModBlocks.PRISS_STONE_STAIR.getId().getPath(),
                        modLoc("block/priss_stone"),
                        modLoc("block/priss_stone"),
                        modLoc("block/priss_stone")
                )
        );

        // ###############################

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

    private void registerPottedPlant(net.minecraft.world.level.block.Block pottedBlock, net.minecraft.world.level.block.Block plantBlock) {
        String pottedName = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(pottedBlock).getPath();
        String plantName = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(plantBlock).getPath();

        // 1. On crée le modèle du bloc en définissant explicitement le cutout
        ModelFile pottedModel = models().withExistingParent(pottedName, "minecraft:block/flower_pot_cross")
                .texture("plant", modLoc("block/" + plantName + "_pottered"))
                .renderType("cutout");

        // 2. On enregistre le blockstate pour que le jeu sache quel modèle afficher
        simpleBlock(pottedBlock, pottedModel);

        // 3. On génère le modèle d'item pour l'inventaire
        itemModels().singleTexture(pottedName, mcLoc("item/generated"), "layer0", modLoc("block/" + plantName));
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

    private void logBlockWithItem(RegistryObject<Block> blockReg) {
        RotatedPillarBlock block = (RotatedPillarBlock) blockReg.get();
        String name = blockReg.getId().getPath();

        ModelFile model = models().cubeColumn(name, modLoc("block/" + name), modLoc("block/" + name + "_top"));

        axisBlock(block, model, model);

        simpleBlockItem(block, model);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

