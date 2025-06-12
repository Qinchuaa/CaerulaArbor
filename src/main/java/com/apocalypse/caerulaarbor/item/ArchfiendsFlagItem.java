
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArchfiendsFlagItem extends RelicItem {
    public ArchfiendsFlagItem() {
        super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.archfiends_flag.description_0"));
        list.add(Component.translatable("item.caerula_arbor.archfiends_flag.description_1"));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.SARKAZ_KING_FLAG;
    }

    @Override
    public @NotNull ParticleOptions getGainParticle() {
        return ParticleTypes.DRIPPING_LAVA;
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.TOTEM_USE;
    }
}
