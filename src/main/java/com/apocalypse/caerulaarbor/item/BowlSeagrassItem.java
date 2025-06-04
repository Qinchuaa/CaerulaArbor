
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BowlSeagrassItem extends Item {
    public BowlSeagrassItem() {
        super(new Item.Properties().stacksTo(16).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.8f).alwaysEat().build()));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 30;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.bowl_seagrass.description_0"));
        list.add(Component.translatable("item.caerula_arbor.bowl_seagrass.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        super.finishUsingItem(stack, world, entity);

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 0));
        }

        Relic.modify(entity, cap -> Relic.UTIL_SEAGRASS.gain(entity));

        if (stack.isEmpty()) {
            return new ItemStack(Items.BOWL);
        } else {
            if (entity instanceof Player player && !player.getAbilities().instabuild) {
                if (!player.getInventory().add(new ItemStack(Items.BOWL))) {
                    player.drop(new ItemStack(Items.BOWL), false);
                }
            }
            return stack;
        }
    }
}
