package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PureGoldExpeditionItem extends RelicItem {

    public PureGoldExpeditionItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_3").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_4").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_5").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public boolean checkUsedMark() {
        return true;
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.UTIL_VOYGOLD;
    }

    @Override
    public void afterUse(ItemStack stack, Level pLevel, LivingEntity entity) {
        super.afterUse(stack, pLevel, entity);

        if (!pLevel.isClientSide) {
            entity.addEffect(new MobEffectInstance(ModMobEffects.ADD_REACH.get(), 400, 1, false, false));
        }

    }

    @Override
    public int getAddedExperience() {
        return 32;
    }
}
