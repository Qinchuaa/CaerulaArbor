
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class CannedNoodleItem extends Item {
    public CannedNoodleItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(18).saturationMod(1f).build()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        super.finishUsingItem(itemstack, world, entity);

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 220, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 220, 0));
        }

        ItemStack can = new ItemStack(ModItems.EMPTY_CAN.get());
        if (itemstack.isEmpty()) {
            return can;
        } else {
            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(can)) {
                    player.drop(can, false);
                }
            }
            return itemstack;
        }
    }
}
