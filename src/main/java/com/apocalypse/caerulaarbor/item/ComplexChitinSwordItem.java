package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.sanity.SIHelper;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;

public class ComplexChitinSwordItem extends SwordItem {

    public ComplexChitinSwordItem() {
        super(new Tier() {
            public int getUses() {
                return 3374;
            }

            public float getSpeed() {
                return 15f;
            }

            public float getAttackDamageBonus() {
                return 7f;
            }

            public int getLevel() {
                return 3;
            }

            public int getEnchantmentValue() {
                return 18;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(ModItems.COMPLEX_CHITIN.get()));
            }
        }, 3, -2.4f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity pAttacker) {
        SIHelper.causeSanityInjuryWithParticles(entity, 120);
        return super.hurtEnemy(itemstack, entity, pAttacker);
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

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.complex_chitin_sword.description_0"));
    }
}
