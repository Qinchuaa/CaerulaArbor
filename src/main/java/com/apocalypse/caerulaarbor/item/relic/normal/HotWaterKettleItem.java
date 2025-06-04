package com.apocalypse.caerulaarbor.item.relic.normal;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class HotWaterKettleItem extends RelicItem {

    public HotWaterKettleItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.hot_water_kettle.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.hot_water_kettle.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        InteractionResultHolder<ItemStack> resultHolder = super.use(pLevel, player, pUsedHand);

        Relic.UTIL_KETTLE.gainAndSync(player);

        return resultHolder;
    }

    @Override
    public ItemStack getRewardItemStack() {
        return new ItemStack(ModBlocks.BLOCK_KETTLE.get());
    }

    @Override
    public int getAddedLives() {
        return 1;
    }

    @Override
    public int getAddedMaxLives() {
        return 1;
    }

    @Override
    public int getAddedExperience() {
        return 8;
    }
}
