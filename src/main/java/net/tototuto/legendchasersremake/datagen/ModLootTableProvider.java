package net.tototuto.legendchasersremake.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModBlocks;
import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {

    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class Blocks extends BlockLootSubProvider {
        public Blocks() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            // 1. Drop simple (Le bloc se drop lui-même)
            this.dropSelf(LegendChasersRemakeModBlocks.IRON_FRAME.get());
            this.dropSelf(LegendChasersRemakeModBlocks.FOUNDRY_CASING.get());
            this.dropSelf(LegendChasersRemakeModBlocks.FOUNDRY.get());
            this.dropSelf(LegendChasersRemakeModBlocks.PRISS_COBBLESTONE.get());

            // 2. Minerai classique : Silk Touch donne le bloc, sinon drop un Item avec Fortune
            // Remplace "Items.RAW_IRON" par ton propre item (ex: LegendChasersRemakeModItems.GRAPHITE.get())
            this.add(LegendChasersRemakeModBlocks.GRAPHITE_ORE.get(), block ->
                    createOreDrop(block, LegendChasersRemakeModItems.GRAPHITE.get())
            );

            this.add(LegendChasersRemakeModBlocks.PRISS_STONE.get(), block ->
                    this.createSingleItemTable(LegendChasersRemakeModItems.PRISS_COBBLESTONE.get()));
            // 3. Cas complexe : Poids (weights), Quantités personnalisées (rolls) et Fortune
            // Exemple : Drop entre 2 et 5 items, sensible au Fortune
            /*
            this.add(LegendChasersRemakeModBlocks.MON_BLOC_COMPLEXE.get(), block ->
                    createMultipleItemDropWithFortune(block, Items.QUARTZ, 2.0f, 5.0f)
            );
            */

            // 4. Cas sur-mesure avec Pool, Poids (Weights) et Rolls
            /*
            this.add(LegendChasersRemakeModBlocks.AUTRE_BLOC.get(), block ->
                    LootTable.lootTable().withPool(
                            LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1)) // Nombre de tirages
                                    .add(LootItem.lootTableItem(Items.COAL).setWeight(5) // Poids de 5 (plus fréquent)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f))))
                                    .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1) // Poids de 1 (plus rare)
                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                    )
            );
            */
        }

        // Helper personnalisé pour du drop multiple avec Fortune
        protected LootTable.Builder createMultipleItemDropWithFortune(Block block, Item item, float min, float max) {
            return createSilkTouchDispatchTable(block,
                    this.applyExplosionDecay(block,
                            LootItem.lootTableItem(item)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                    )
            );
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return LegendChasersRemakeModBlocks.BLOCKS.getEntries()
                    .stream()
                    .map(entry -> (Block) entry.get())
                    ::iterator;
        }
    }
}