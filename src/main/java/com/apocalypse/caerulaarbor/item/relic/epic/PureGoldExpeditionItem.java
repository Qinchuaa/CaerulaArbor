package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class PureGoldExpeditionItem extends RelicItem {

    public PureGoldExpeditionItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_3").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_4").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item.caerula_arbor.pure_gold_expedition.des_5").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!stack.getOrCreateTag().getBoolean("used")) {
            stack.getOrCreateTag().putBoolean("used", true);
            if (!pLevel.isClientSide) {
                pPlayer.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.ADD_REACH.get(), 400, 1, false, false));
            }
            pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.relic_util_VOYGOLD = true;
                capability.syncPlayerVariables(pPlayer);
            });
            return super.use(pLevel, pPlayer, pUsedHand);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public int getAddedExperience() {
        return 32;
    }
}
