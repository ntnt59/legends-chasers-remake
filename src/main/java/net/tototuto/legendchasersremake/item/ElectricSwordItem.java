package net.tototuto.legendchasersremake.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.tototuto.legendchasersremake.init.LegendChasersRemakeModItems;

import java.util.List;

public class ElectricSwordItem extends SwordItem {
    public ElectricSwordItem() {
        super(new Tier() {
            public int getUses() {
                return 336;
            }

            public float getSpeed() {
                return 7f;
            }

            public float getAttackDamageBonus() {
                return 2.5f;
            }

            public int getLevel() {
                return 2;
            }

            public int getEnchantmentValue() {
                return 12;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(LegendChasersRemakeModItems.ELECTRIC_SWORD.get()));
            }
        }, 3, -2.4f, new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.legend_chasers_remake.electric_sword"));
    }
}
