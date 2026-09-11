package net.tototuto.legendchasersremake.init;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.tototuto.legendchasersremake.item.ElectricSwordItem;

public class LegendChasersRemakeModItems {
	public static final String ModClass = "LegendChasersRemakeModBlocks";
	
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, LegendChasersRemakeMod.MODID);

	public static final RegistryObject<Item> ELECTRIC_SWORD;
	public static final RegistryObject<Item> GRAPHITE_ORE;
	public static final RegistryObject<Item> GRAPHITE;
	public static final RegistryObject<Item> IRON_FRAME;
	public static final RegistryObject<Item> FOUNDRY_CASING;
	public static final RegistryObject<Item> FOUNDRY;
	public static final RegistryObject<Item> PRISS_STONE;
	public static final RegistryObject<Item> PRISS_COBBLESTONE;
	public static final RegistryObject<Item> AQUAMARINE_BLOCK;
	public static final RegistryObject<Item> BLUE_MYSTERIOUS_BRICKS;
	public static final RegistryObject<Item> AQUAMARINE;
	public static final RegistryObject<Item> ABYSSAL_NYLIUM_BLOCK;
	public static final RegistryObject<Item> PROTIUM_LEAVES;
	public static final RegistryObject<Item> PRISS_GRASS;
	public static final RegistryObject<Item> BLUE_PRISS_GRASS;
	public static final RegistryObject<Item> PINK_PRISS_GRASS;
	public static final RegistryObject<Item> GREEN_PRISS_GRASS;
	public static final RegistryObject<Item> PURPLE_PRISS_GRASS;
	public static final RegistryObject<Item> PARAPHYTE;
	public static final RegistryObject<Item> FLOROPHYTE;
	public static final RegistryObject<Item> BUBBLEPHYTE;
	public static final RegistryObject<Item> TALL_PRISS_GRASS ;
	public static final RegistryObject<Item> TALL_RAINBOW_PRISS_GRASS ;
	public static final RegistryObject<Item> PROTIUM_LOG;
	public static final RegistryObject<Item> PROTIUM_FENCE;
	public static final RegistryObject<Item> PROTIUM_PLANKS;
	public static final RegistryObject<Item> PROTIUM_STAIR;
	public static final RegistryObject<Item> PROTIUM_SLAB;
	public static final RegistryObject<Item> PROTIUM_FENCE_GATE;

	static {
		ELECTRIC_SWORD = REGISTRY.register("electric_sword", ElectricSwordItem::new);

		GRAPHITE = REGISTRY.register("graphite", () -> new Item(
				new Item.Properties()
						.stacksTo(64)
		));

		GRAPHITE_ORE = REGISTRY.register("graphite_ore", () -> new BlockItem(
				LegendChasersRemakeModBlocks.GRAPHITE_ORE.get(),
				new Item.Properties()
		));
		IRON_FRAME = REGISTRY.register("iron_frame", () -> new BlockItem(
				LegendChasersRemakeModBlocks.IRON_FRAME.get(),
				new Item.Properties()
		));
		FOUNDRY_CASING = REGISTRY.register("foundry_casing", () -> new BlockItem(
				LegendChasersRemakeModBlocks.FOUNDRY_CASING.get(),
				new Item.Properties()
						.fireResistant()
		));
		FOUNDRY = REGISTRY.register("foundry", () -> new BlockItem(
				LegendChasersRemakeModBlocks.FOUNDRY.get(),
				new Item.Properties()
						.fireResistant()
		));
		PRISS_STONE = REGISTRY.register("priss_stone", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PRISS_STONE.get(),
				new Item.Properties()
		));
		PRISS_COBBLESTONE = REGISTRY.register("priss_cobblestone", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PRISS_COBBLESTONE.get(),
				new Item.Properties()
		));
		AQUAMARINE_BLOCK = REGISTRY.register("aquamarine_block", () -> new BlockItem(
				LegendChasersRemakeModBlocks.AQUAMARINE_BLOCK.get(),
				new Item.Properties()
		));
		BLUE_MYSTERIOUS_BRICKS = REGISTRY.register("blue_mysterious_bricks", () -> new BlockItem(
				LegendChasersRemakeModBlocks.BLUE_MYSTERIOUS_BRICKS.get(),
				new Item.Properties()
		));
		AQUAMARINE = REGISTRY.register("aquamarine", () -> new Item(
				new Item.Properties()
		));
		ABYSSAL_NYLIUM_BLOCK = REGISTRY.register("abyssal_nylium_block", () -> new BlockItem(
		                LegendChasersRemakeModBlocks.ABYSSAL_NYLIUM_BLOCK.get(),
						new Item.Properties()
		));
		PROTIUM_LEAVES = REGISTRY.register("protium_leaves", () -> new BlockItem(
		                LegendChasersRemakeModBlocks.PROTIUM_LEAVES.get(),
						new Item.Properties()
		));
		PRISS_GRASS = REGISTRY.register("priss_grass", () -> new BlockItem(
		                LegendChasersRemakeModBlocks.PRISS_GRASS.get(),
						new Item.Properties()
		));
		BLUE_PRISS_GRASS = REGISTRY.register("blue_priss_grass", () -> new BlockItem(
		                LegendChasersRemakeModBlocks.BLUE_PRISS_GRASS.get(),
						new Item.Properties()
				));
		PURPLE_PRISS_GRASS = REGISTRY.register("purple_priss_grass", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PURPLE_PRISS_GRASS.get(),
				new Item.Properties()
		));
		PINK_PRISS_GRASS = REGISTRY.register("pink_priss_grass", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PINK_PRISS_GRASS.get(),
				new Item.Properties()
		));
		GREEN_PRISS_GRASS = REGISTRY.register("green_priss_grass", () -> new BlockItem(
				LegendChasersRemakeModBlocks.GREEN_PRISS_GRASS.get(),
				new Item.Properties()
		));
		PARAPHYTE = REGISTRY.register("paraphyte", () -> new BlockItem(
		                LegendChasersRemakeModBlocks.PARAPHYTE.get(),
						new Item.Properties()
				));
		FLOROPHYTE = REGISTRY.register("florophyte", () -> new BlockItem(
				LegendChasersRemakeModBlocks.FLOROPHYTE.get(),
				new Item.Properties()
		));
		BUBBLEPHYTE = REGISTRY.register("bubblephyte", () -> new BlockItem(
				LegendChasersRemakeModBlocks.BUBBLEPHYTE.get(),
				new Item.Properties()
		));
		TALL_RAINBOW_PRISS_GRASS = REGISTRY.register("tall_rainbow_priss_grass", () -> new BlockItem(
				LegendChasersRemakeModBlocks.TALL_RAINBOW_PRISS_GRASS.get(),
				new Item.Properties()
		));
		TALL_PRISS_GRASS = REGISTRY.register("tall_priss_grass", () -> new BlockItem(
				LegendChasersRemakeModBlocks.TALL_PRISS_GRASS.get(),
				new Item.Properties()
		));
		PROTIUM_LOG = REGISTRY.register("protium_log", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_LOG.get(),
				new Item.Properties()
		));
		PROTIUM_FENCE = REGISTRY.register("protium_fence", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_FENCE.get(),
				new Item.Properties()
		));
		PROTIUM_PLANKS = REGISTRY.register("protium_planks", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_PLANKS.get(),
				new Item.Properties()
		));
		PROTIUM_SLAB = REGISTRY.register("protium_slab", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_SLAB.get(),
				new Item.Properties()
		));
		PROTIUM_STAIR = REGISTRY.register("protium_stair", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_STAIR.get(),
				new Item.Properties()
		));
		PROTIUM_FENCE_GATE = REGISTRY.register("protium_fence_gate", () -> new BlockItem(
				LegendChasersRemakeModBlocks.PROTIUM_FENCE_GATE.get(),
				new Item.Properties()
		));
	}
}