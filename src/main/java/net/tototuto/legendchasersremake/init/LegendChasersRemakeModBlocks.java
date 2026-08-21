package net.tototuto.legendchasersremake.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.block.FoundryBlock;
import net.tototuto.legendchasersremake.block.GraphiteOre;

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
}
