package net.tototuto.legendchasersremake.init;

import net.tototuto.legendchasersremake.LegendChasersRemakeMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem; // <-- Import nécessaire pour le bloc
import net.tototuto.legendchasersremake.item.ElectricSwordItem;

public class LegendChasersRemakeModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, LegendChasersRemakeMod.MODID);

	public static final RegistryObject<Item> ELECTRIC_SWORD;
	public static final RegistryObject<Item> GRAPHITE_ORE;
	public static final RegistryObject<Item> GRAPHITE;
	public static final RegistryObject<Item> IRON_FRAME;
	public static final RegistryObject<Item> FOUNDRY_CASING;
	public static final RegistryObject<Item> FOUNDRY;
	public static final RegistryObject<Item> PRISS_STONE;
	public static final RegistryObject<Item> PRISS_COBBLESTONE;

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
	}
}