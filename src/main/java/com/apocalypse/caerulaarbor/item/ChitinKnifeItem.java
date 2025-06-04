
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModItems;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChitinKnifeItem extends RelicItem {
    public ChitinKnifeItem() {
        super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.chitin_knife.description_0"));
        list.add(Component.translatable("item.caerula_arbor.chitin_knife.description_1"));
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.BELL_BLOCK;
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.LEGEND_CHITIN;
    }

    @Override
    public void afterUse(ItemStack stack, Level pLevel, LivingEntity entity) {
        super.afterUse(stack, pLevel, entity);

        if (entity.level() instanceof ServerLevel server) {
            ItemEntity entityToSpawn = new ItemEntity(server, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.OCEAN_TRIM_TEMPLATE.get()));
            entityToSpawn.setPickUpDelay(5);
            entityToSpawn.setUnlimitedLifetime();
            server.addFreshEntity(entityToSpawn);
        }
    }
}
