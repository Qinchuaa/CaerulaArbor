package com.apocalypse.caerulaarbor.item.relic.rare;

import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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

public class DurinOvergroundOdysseyItem extends RelicItem {

    public DurinOvergroundOdysseyItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.durin_overground_odyssey.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.durin_overground_odyssey.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!stack.getOrCreateTag().getBoolean("Used")) {
            stack.getOrCreateTag().putBoolean("Used", true);

            pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                capability.relic_util_DURIN = true;
                capability.syncPlayerVariables(pPlayer);
            });

            return super.use(pLevel, pPlayer, pUsedHand);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public int getAddedShield() {
        return 4;
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.AMETHYST_BLOCK_RESONATE;
    }

    @Override
    public @NotNull ParticleOptions getGainParticle() {
        return ParticleTypes.NAUTILUS;
    }
}
