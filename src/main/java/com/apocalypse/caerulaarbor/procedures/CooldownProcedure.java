package com.apocalypse.caerulaarbor.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.ModItems;

public class CooldownProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		itemstack.setDamageValue((int) (itemstack.getDamageValue() + 1));
		if (itemstack.getDamageValue() >= 1199) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(ModItems.CANNED_BOILED_WATER.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(ModItems.CANNED_WATER.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
