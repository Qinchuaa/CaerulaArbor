
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.procedures.GainRelicSPEARProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class TheSpearItem extends SwordItem {
    public TheSpearItem() {
        super(new Tier() {
            public int getUses() {
                return 385;
            }

            public float getSpeed() {
                return 8f;
            }

            public float getAttackDamageBonus() {
                return 8f;
            }

            public int getLevel() {
                return 3;
            }

            public int getEnchantmentValue() {
                return 9;
            }

            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.5f, new Item.Properties().fireResistant());
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        GainRelicSPEARProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
        return ar;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);

        if (!selected || !(entity instanceof LivingEntity living) || !living.hasEffect(ModMobEffects.SPEAR_FIGHT.get()))
            return;

        if (!living.level().isClientSide) {
            living.addEffect(new MobEffectInstance(ModMobEffects.SPEAR_FIGHT.get(), 60, 0, false, false));
        }
    }
}
