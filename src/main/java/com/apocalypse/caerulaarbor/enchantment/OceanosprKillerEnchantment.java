
package com.apocalypse.caerulaarbor.enchantment;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OceanosprKillerEnchantment extends Enchantment {
    private static final EnchantmentCategory ENCHANTMENT_CATEGORY = EnchantmentCategory.create("caerula_arbor_oceanospr_killer", item -> Ingredient.of(ItemTags.SWORDS).test(new ItemStack(item)));

    public OceanosprKillerEnchantment() {
        super(Enchantment.Rarity.COMMON, ENCHANTMENT_CATEGORY, EquipmentSlot.values());
    }

    @Override
    public int getMinCost(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxCost(int level) {
        return 6 + level * 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && !List.of(Enchantments.BANE_OF_ARTHROPODS, Enchantments.SMITE).contains(enchantment);
    }
}
