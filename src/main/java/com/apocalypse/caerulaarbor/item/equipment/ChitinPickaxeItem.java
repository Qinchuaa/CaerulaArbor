package com.apocalypse.caerulaarbor.item.equipment;

import com.apocalypse.caerulaarbor.tiers.ModItemTier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;

public class ChitinPickaxeItem extends PickaxeItem {

    public ChitinPickaxeItem() {
        super(ModItemTier.CHITIN, -1, -2.8f, new Item.Properties());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        ItemStack stack = new ItemStack(this);
        stack.setDamageValue(itemstack.getDamageValue() + 1);
        if (stack.getDamageValue() >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return stack;
    }
}
