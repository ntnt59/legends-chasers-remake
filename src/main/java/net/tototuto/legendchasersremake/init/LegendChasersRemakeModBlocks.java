package net.tototuto.legendchasersremake.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.block.FoundryBlock;
import net.tototuto.legendchasersremake.block.GraphiteOre;
import net.tototuto.legendchasersremake.block.PrissGrass;

public class LegendChasersRemakeModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "legend_chasers_remake");

    // Minerai de graphite
    public static final RegistryObject<Block> GRAPHITE_ORE =
            BLOCKS.register("graphite_ore", () -> new GraphiteOre(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(3.0f, 3.0f)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> IRON_FRAME =
            BLOCKS.register("iron_frame", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(5.0f, 6.0f)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
                ));
    public static final RegistryObject<Block> FOUNDRY_CASING =
            BLOCKS.register("foundry_casing", () -> new Block (
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength (5.0f, 6.0f)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> FOUNDRY =
            BLOCKS.register("foundry", () -> new FoundryBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(6.0f, 6.5f)
                            .sound(SoundType.METAL)
                            .requiresCorrectToolForDrops()

            ));
    public static final RegistryObject<Block> PRISS_STONE =
            BLOCKS.register("priss_stone", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.5f, 6.0f)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()

            ));
    public static final RegistryObject<Block> PRISS_COBBLESTONE =
            BLOCKS.register("priss_cobblestone", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.5f, 6.0f)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()

            ));
    public static final RegistryObject<Block> BLUE_MYSTERIOUS_BRICKS =
            BLOCKS.register("blue_mysterious_bricks", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.5f, 6.0f)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()

            ));
    public static final RegistryObject<Block> AQUAMARINE_BLOCK =
            BLOCKS.register("aquamarine_block", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(1.5f, 1.5f)
                            .sound(SoundType.AMETHYST)
                            .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> ABYSSAL_NYLIUM_BLOCK =
            BLOCKS.register("abyssal_nylium_block", () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.TERRACOTTA_WHITE)
                            .strength(1.5f, 6.0f)
                            .sound(SoundType.STONE)
                            .requiresCorrectToolForDrops()
            ));
    public static final RegistryObject<Block> PROTIUM_LEAVES =
            BLOCKS.register("protium_leaves", () -> new LeavesBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_CYAN)
                            .strength(0.6f, 0.6f)
                            .randomTicks()
                            .sound(SoundType.AZALEA_LEAVES)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false)
                            .isSuffocating((state, level, pos) -> false)
            ));
    
    public static final RegistryObject<Block> PRISS_GRASS =
            BLOCKS.register("priss_grass", () -> new PrissGrass(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
            ));
    // to add in BlockStateProvider : simpleBlock(PRISS_GRASS.get());
    // to add in BlockStateProvider : simpleBlock(PROTIUM_LEAVES.get());
    // to add in BlockStateProvider : simpleBlock(ABYSSAL_NYLIUM_BLOCK.get());
}
