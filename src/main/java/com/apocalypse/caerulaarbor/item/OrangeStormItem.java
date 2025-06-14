package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrangeStormItem extends RelicItem {

    public OrangeStormItem() {
        super(new Item.Properties().food(
                (new FoodProperties.Builder()).nutrition(3).saturationMod(0.8f).alwaysEat()
                        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0), 1.0f).build()
        ));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 30;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.orange_storm.des_1"));
        list.add(Component.translatable("item.caerula_arbor.orange_storm.des_2"));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.ORANGE_STORM;
    }

    @Override
    public ItemStack getRewardItemStack() {
        return new ItemStack(ModItems.PAPER_BAG.get());
    }
}
