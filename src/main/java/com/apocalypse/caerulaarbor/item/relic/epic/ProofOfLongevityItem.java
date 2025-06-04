package com.apocalypse.caerulaarbor.item.relic.epic;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ProofOfLongevityItem extends RelicItem {

    public ProofOfLongevityItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.proof_of_longevity.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.proof_of_longevity.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, InteractionHand hand) {
        Relic.UTIL_LONGEVITY.gainAndSync(pPlayer);

        pPlayer.getItemInHand(hand).shrink(1);
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
