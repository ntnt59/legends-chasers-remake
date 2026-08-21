package net.tototuto.legendchasersremake.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.tototuto.legendchasersremake.LegendChasersRemakeMod;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LegendChasersRemakeModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LegendChasersRemakeMod.MODID);

	public static final RegistryObject<CreativeModeTab> LCR_FUNCTIONAL_BLOCKS = REGISTRY.register("lcr_functional_blocks",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.legend_chasers_remake.lcr_functional_blocks"))
					.icon(() -> new ItemStack(LegendChasersRemakeModBlocks.FOUNDRY.get()))
					.displayItems((parameters, tabData) -> {
								tabData.accept(LegendChasersRemakeModItems.IRON_FRAME.get());
								tabData.accept(LegendChasersRemakeModItems.FOUNDRY_CASING.get());
								tabData.accept(LegendChasersRemakeModItems.FOUNDRY.get());
							}
					)
					.build());

	public static final RegistryObject<CreativeModeTab> LCR_COMBAT = REGISTRY.register("lcr_combat",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.legend_chasers_remake.lcr_combat"))
					.icon(() -> new ItemStack(LegendChasersRemakeModItems.ELECTRIC_SWORD.get()))
					.displayItems((parameters, tabData) -> {
								tabData.accept(LegendChasersRemakeModItems.ELECTRIC_SWORD.get());
							}
					)
					.build());

	public static final RegistryObject<CreativeModeTab> LCR_NATURAL_BLOCKS = REGISTRY.register("lcr_natural_blocks",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.legend_chasers_remake.lcr_natural_blocks"))
					.icon(() -> new ItemStack(LegendChasersRemakeModItems.PRISS_STONE.get()))
					.displayItems((parameters, tabData) -> {
								tabData.accept(LegendChasersRemakeModItems.GRAPHITE_ORE.get());
								tabData.accept(LegendChasersRemakeModItems.PRISS_STONE.get());
								tabData.accept(LegendChasersRemakeModItems.PRISS_COBBLESTONE.get());
							}
					)
					.build());

	public static final RegistryObject<CreativeModeTab> LCR_INGREDIENTS = REGISTRY.register("lcr_ingredients",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.legend_chasers_remake.lcr_ingredients"))
					.icon(() -> new ItemStack(LegendChasersRemakeModItems.GRAPHITE.get()))
					.displayItems((parameters, tabData) -> {
								tabData.accept(LegendChasersRemakeModItems.GRAPHITE.get());
							}
					)
					.build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
	}
}