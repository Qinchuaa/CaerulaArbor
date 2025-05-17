
package com.apocalypse.caerulaarbor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ComplexChitinItem extends Item {
	public ComplexChitinItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
