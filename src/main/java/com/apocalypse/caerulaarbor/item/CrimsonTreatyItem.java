
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrimsonTreatyItem extends RelicItem {
    public CrimsonTreatyItem() {
        super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.crimson_treaty.description_0"));
        list.add(Component.translatable("item.caerula_arbor.crimson_treaty.description_1"));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.TREATY;
    }

    @Override
    public @NotNull ParticleOptions getGainParticle() {
        return ParticleTypes.FLAME;
    }
}
