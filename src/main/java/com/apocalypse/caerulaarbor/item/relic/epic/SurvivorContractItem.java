package com.apocalypse.caerulaarbor.item.relic.epic;

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

public class SurvivorContractItem extends RelicItem {

    public SurvivorContractItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.survivor_contract.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.survivor_contract.des_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player entity, InteractionHand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
        if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR < 0) {
            entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                capability.relic_SURVIVOR = 0;
                capability.syncPlayerVariables(entity);
            });
            return super.use(world, entity, hand);
        }
        return InteractionResultHolder.fail(itemstack);
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.BEACON_ACTIVATE;
    }

    @Override
    public @NotNull ParticleOptions getGainParticle() {
        return ParticleTypes.GLOW;
    }
}
