
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class GuardianStareItem extends Item {
    public GuardianStareItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.guardian_stare.description_0"));
        list.add(Component.translatable("item.caerula_arbor.guardian_stare.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack stack = ar.getObject();
        if (!stack.getOrCreateTag().getBoolean("Used")) {
            Relic.UTIL_STARE.gainAndSync(entity);

            if (world instanceof ServerLevel server) {
                server.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1);
                server.sendParticles(ParticleTypes.NAUTILUS, x, (y + 0.5), z, 72, 1, 1, 1, 1);
            } else {
                world.playLocalSound(x, y, z, SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1, false);
            }

            entity.giveExperienceLevels(4);
            stack.getOrCreateTag().putBoolean("Used", true);
        }
        return ar;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);

        if (entity instanceof LivingEntity living && living.hasEffect(MobEffects.DIG_SLOWDOWN)) {
            living.removeEffect(MobEffects.DIG_SLOWDOWN);
        }
    }
}
