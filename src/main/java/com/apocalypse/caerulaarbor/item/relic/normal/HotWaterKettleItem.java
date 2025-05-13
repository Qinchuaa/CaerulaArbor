package com.apocalypse.caerulaarbor.item.relic.normal;

import com.apocalypse.caerulaarbor.init.CaerulaArborModBlocks;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class HotWaterKettleItem extends RelicItem {

    public HotWaterKettleItem() {
        super(new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.caerula_arbor.hot_water_kettle.des_1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.hot_water_kettle.des_2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        InteractionResultHolder<ItemStack> resultHolder = super.use(pLevel, player, pUsedHand);

        player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.relic_util_KETTLE = true;
            capability.syncPlayerVariables(player);
        });

        return resultHolder;
    }

    @Override
    public ItemStack getRewardItemStack() {
        return new ItemStack(CaerulaArborModBlocks.BLOCK_KETTLE.get());
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
