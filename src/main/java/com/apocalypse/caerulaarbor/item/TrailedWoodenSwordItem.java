
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TrailedWoodenSwordItem extends SwordItem {
    public TrailedWoodenSwordItem() {
        super(new Tier() {
            public int getUses() {
                return 59;
            }

            public float getSpeed() {
                return 2f;
            }

            public float getAttackDamageBonus() {
                return 0f;
            }

            public int getLevel() {
                return 0;
            }

            public int getEnchantmentValue() {
                return 18;
            }

            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(ItemTags.PLANKS);
            }
        }, 3, -2.4f, new Item.Properties());
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, @NotNull LivingEntity source) {
        LevelAccessor world = entity.level();
        double dam = 40 + 8 * itemstack.getEnchantmentLevel(Enchantments.SHARPNESS);
        DeductPlayerSanityProcedure.execute(entity, dam);

        for (int i = 0; i < 5; i++) {
            CaerulaArborMod.queueServerWork(i, () -> {
                if (world instanceof ServerLevel server) {
                    server.sendParticles(
                            ParticleTypes.ELECTRIC_SPARK,
                            entity.getX(),
                            entity.getY() + entity.getBbHeight() * 0.5,
                            entity.getZ(),
                            8, 1.2, 1.5, 1.2, 0.1
                    );
                }
            });
        }

        return super.hurtEnemy(itemstack, entity, source);
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
    public boolean isRepairable(@NotNull ItemStack itemstack) {
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.trailed_wooden_sword.description_0"));
        list.add(Component.translatable("item.caerula_arbor.trailed_wooden_sword.description_1"));
    }
}
