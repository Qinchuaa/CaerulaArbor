package com.apocalypse.caerulaarbor.item.relic.rare;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.init.ModSounds;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class WeirdFluteItem extends RelicItem {

    public WeirdFluteItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 40;
    }

    @Override
    public boolean isInstantUse() {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.weird_flute.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.weird_flute.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand hand) {
        ItemStack stack = pPlayer.getItemInHand(hand);
        pPlayer.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public boolean checkUsedMark() {
        return true;
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.UTIL_FLUTE;
    }

    @Override
    public void afterUse(ItemStack stack, Level pLevel, LivingEntity entity) {
        super.afterUse(stack, pLevel, entity);

        if (!pLevel.isClientSide) {
            entity.addEffect(new MobEffectInstance(ModMobEffects.ADD_REACH.get(), 300, 0, false, false));
        }

        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 280);
        }
    }

    @Override
    public int getAddedExperience() {
        return 28;
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return ModSounds.FLUTESONG.get();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pLevel instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.NOTE, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(),
                    1, 1, 1, 1, 1);
        }
    }
}
