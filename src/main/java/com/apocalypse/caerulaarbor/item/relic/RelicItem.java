package com.apocalypse.caerulaarbor.item.relic;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class RelicItem extends Item {

    public RelicItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!this.isInstantUse()) {
            return super.use(pLevel, pPlayer, pUsedHand);
        }

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (pLevel instanceof ServerLevel serverLevel) {
            pLevel.playSound(null, pPlayer.getOnPos(), this.getGainSound(), SoundSource.NEUTRAL, 2, 1);
            serverLevel.sendParticles(this.getGainParticle(), pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), 24, 0.75, 1, 0.75, 1);

            if (this.getAddedExperience() > 0) {
                serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), this.getAddedExperience()));
            }
        } else {
            Minecraft.getInstance().gameRenderer.displayItemActivation(stack);

            pLevel.playLocalSound(pPlayer.getOnPos(), this.getGainSound(), SoundSource.NEUTRAL, 2, 1, false);

            if (this.getAddedLives() != 0) {
                double lives = (pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new CaerulaArborModVariables.PlayerVariables())).player_maxlive + this.getAddedLives();
                pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.player_maxlive = lives;
                    capability.syncPlayerVariables(pPlayer);
                });
            }
            if (this.getAddedMaxLives() != 0) {
                double maxLives = (pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new CaerulaArborModVariables.PlayerVariables())).player_lives + this.getAddedMaxLives();
                pPlayer.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.player_lives = maxLives;
                    capability.syncPlayerVariables(pPlayer);
                });
            }
        }

        stack.shrink(1);

        if (!this.getRewardItemStack().isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(pPlayer, this.getRewardItemStack());
        }
        return InteractionResultHolder.consume(stack);
    }

    /**
     * 是否是右键就立刻使用的，如果不是则走原版使用流程
     */
    public boolean isInstantUse() {
        return true;
    }

    public int getAddedLives() {
        return 0;
    }

    public int getAddedMaxLives() {
        return 0;
    }

    public int getAddedExperience() {
        return 0;
    }

    @NotNull
    public SoundEvent getGainSound() {
        return SoundEvents.PLAYER_LEVELUP;
    }

    @NotNull
    public ParticleOptions getGainParticle() {
        return ParticleTypes.HAPPY_VILLAGER;
    }

    public ItemStack getRewardItemStack() {
        return ItemStack.EMPTY;
    }
}
