package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.menu.CaerulaRecorderMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CaerulaRecorderItem extends Item {

    public CaerulaRecorderItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide) {
            return InteractionResultHolder.success(stack);
        } else {
            pPlayer.openMenu(new SimpleMenuProvider((i, inventory, player) -> new CaerulaRecorderMenu(i, inventory), Component.empty()));
            return InteractionResultHolder.consume(stack);
        }
    }
}
