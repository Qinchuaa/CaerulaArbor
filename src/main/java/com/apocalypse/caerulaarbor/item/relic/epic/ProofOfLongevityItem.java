package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProofOfLongevityItem extends RelicItem {

    public ProofOfLongevityItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.proof_of_longevity.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.proof_of_longevity.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        ItemStack stack = pPlayer.getItemInHand(hand);
        pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.relic_util_LONGEVITY = true;
            capability.syncPlayerVariables(pPlayer);
        });
        stack.shrink(1);
        return super.use(pLevel, pPlayer, hand);
    }

    @Override
    public int getAddedLives() {
        return 6;
    }

    @Override
    public int getAddedMaxLives() {
        return 6;
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.AMBIENT_WARPED_FOREST_MOOD.get();
    }
}
