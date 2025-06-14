package com.apocalypse.caerulaarbor.item.relic.rare;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraPungentCoffeeBeansItem extends RelicItem {

    public ExtraPungentCoffeeBeansItem() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON).food(
                (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6f).alwaysEat()
                        .effect(() -> new MobEffectInstance(MobEffects.JUMP, 240, 0), 1.0f).build()
        ));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 40;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.extra_pungent_coffee_beans.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.extra_pungent_coffee_beans.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public boolean isInstantUse() {
        return false;
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.EXTRA_PUNGENT_COFFEE_BEANS;
    }

    @Override
    public int getAddedShield() {
        return 1;
    }
}
