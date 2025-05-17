
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class AromaticCoffeeItem extends Item {
    public AromaticCoffeeItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(5).saturationMod(6f).alwaysEat().build()));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 40;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.aromatic_coffee.description_0"));
        list.add(Component.translatable("item.caerula_arbor.aromatic_coffee.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 240, 0));
        }

        entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(cap -> {
            cap.shield = cap.shield + 1;
            cap.player_util_AROMATIC = true;
            cap.syncPlayerVariables(entity);
        });

        return super.finishUsingItem(itemstack, world, entity);
    }
}
