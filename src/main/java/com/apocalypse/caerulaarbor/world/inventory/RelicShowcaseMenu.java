package com.apocalypse.caerulaarbor.world.inventory;

import com.apocalypse.caerulaarbor.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class RelicShowcaseMenu extends AbstractContainerMenu {

    public final Player player;
    public final Inventory inventory;

    public RelicShowcaseMenu(int id, Inventory inventory) {
        super(ModMenus.RELIC_SHOWCASE.get(), id);
        this.player = inventory.player;
        this.inventory = inventory;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
