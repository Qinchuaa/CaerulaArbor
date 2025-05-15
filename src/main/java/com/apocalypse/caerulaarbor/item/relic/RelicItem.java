package com.apocalypse.caerulaarbor.item.relic;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RelicItem extends Item implements IRelic {

    public RelicItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getTag() != null && pStack.getTag().getBoolean("Used")) {
            pTooltipComponents.add(Component.translatable("item.caerula_arbor.relics.used").withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!this.isInstantUse()) {
            return super.use(pLevel, pPlayer, pUsedHand);
        }
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        this.afterUse(stack, pLevel, pPlayer);

        return InteractionResultHolder.consume(stack);
    }

    public void afterUse(ItemStack stack, Level pLevel, LivingEntity entity) {
        if (pLevel instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(this.getGainParticle(), entity.getX(), entity.getY(), entity.getZ(), 72, 0.75, 1, 0.75, 1);

            if (this.getAddedExperience() > 0) {
                serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, entity.getX(), entity.getY(), entity.getZ(), this.getAddedExperience()));
            }
        } else {
            Minecraft.getInstance().gameRenderer.displayItemActivation(stack);

            if (this.getAddedLives() != 0) {
                double lives = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new CaerulaArborModVariables.PlayerVariables())).player_maxlive + this.getAddedLives();
                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.player_maxlive = lives;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (this.getAddedMaxLives() != 0) {
                double maxLives = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new CaerulaArborModVariables.PlayerVariables())).player_lives + this.getAddedMaxLives();
                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.player_lives = maxLives;
                    capability.syncPlayerVariables(entity);
                });
            }
            if (this.getAddedShield() != 0) {
                double shield = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                        .orElse(new CaerulaArborModVariables.PlayerVariables())).player_shield + this.getAddedShield();
                entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.player_shield = shield;
                    capability.syncPlayerVariables(entity);
                });
            }
        }

        this.playGainSound(pLevel, entity);

        if (!this.getRewardItemStack().isEmpty() && entity instanceof Player player) {
            ItemHandlerHelper.giveItemToPlayer(player, this.getRewardItemStack());
            stack.shrink(1);
        }
    }

    public void playGainSound(Level pLevel, LivingEntity entity) {
        if (!pLevel.isClientSide) {
            pLevel.playSound(null, entity.getOnPos(), this.getGainSound(), SoundSource.NEUTRAL, 2, 1);
        } else {
            pLevel.playLocalSound(entity.getOnPos(), this.getGainSound(), SoundSource.NEUTRAL, 2, 1, false);
        }
    }

    /**
     * 是否是右键就立刻使用的，如果不是则走原版使用流程
     */
    public boolean isInstantUse() {
        return !this.isEdible();
    }

    public int getAddedLives() {
        return 0;
    }

    public int getAddedMaxLives() {
        return 0;
    }

    public int getAddedShield() {
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
