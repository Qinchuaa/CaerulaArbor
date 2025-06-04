
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class SoloMusicBoxItem extends Item {
    public SoloMusicBoxItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.solo_music_box.description_0"));
        list.add(Component.translatable("item.caerula_arbor.solo_music_box.description_1"));
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
            for (int index0 = 0; index0 < 8; index0++) {
                if (world instanceof ServerLevel server) {
                    server.addFreshEntity(new ExperienceOrb(server, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.6, 0.75)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 4));
                }
            }

            Relic.modify(entity, cap -> Relic.UTIL_MUSICBOX.gain(entity));

            stack.getOrCreateTag().putBoolean("Used", true);

            if (world instanceof ServerLevel server) {
                world.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1);
                server.sendParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 72, 1, 1, 1, 1);
            } else {
                world.playLocalSound(x, y, z, SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1, false);
            }
        }
        return ar;
    }
}
