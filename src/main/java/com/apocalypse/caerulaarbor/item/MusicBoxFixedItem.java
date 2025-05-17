
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.init.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class MusicBoxFixedItem extends Item {
    public MusicBoxFixedItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 20;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.music_box_fixed.description_0"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return super.use(world, entity, hand);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        if (!world.isClientSide()) {
            world.playSound(null, entity.blockPosition(), ModSounds.PCEANWISH.get(), SoundSource.MUSIC, (float) 2.5, 1);
        } else {
            world.playLocalSound(entity.blockPosition(), ModSounds.PCEANWISH.get(), SoundSource.MUSIC, (float) 2.5, 1, false);
        }

        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(itemstack.getItem(), 900);
        }

        return super.finishUsingItem(itemstack, world, entity);
    }
}
