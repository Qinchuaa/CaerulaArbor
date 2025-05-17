
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class TrailedIronSwordItem extends SwordItem {
    public TrailedIronSwordItem() {
        super(new Tier() {
            public int getUses() {
                return 250;
            }

            public float getSpeed() {
                return 6f;
            }

            public float getAttackDamageBonus() {
                return 2f;
            }

            public int getLevel() {
                return 2;
            }

            public int getEnchantmentValue() {
                return 17;
            }

            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(Items.IRON_INGOT));
            }
        }, 3, -2.4f, new Item.Properties());
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        double dam = 60 + 12 * itemstack.getEnchantmentLevel(Enchantments.SHARPNESS);
        DeductPlayerSanityProcedure.execute(entity, dam);

        for (int i = 0; i < 5; i++) {
            CaerulaArborMod.queueServerWork(i, () -> {
                if (entity.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK, entity.getX(), (entity.getY() + entity.getBbHeight() * 0.5), entity.getZ(), 10, 1.2, 1.5, 1.2, 0.1);
                }
            });
        }

        return super.hurtEnemy(itemstack, entity, sourceentity);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        ItemStack remain = new ItemStack(this);
        remain.setDamageValue(itemstack.getDamageValue() + 1);
        if (remain.getDamageValue() >= remain.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return remain;
    }

    @Override
    public boolean isRepairable(@NotNull ItemStack itemstack) {
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.trailed_iron_sword.description_0"));
        list.add(Component.translatable("item.caerula_arbor.trailed_iron_sword.description_1"));
    }
}
