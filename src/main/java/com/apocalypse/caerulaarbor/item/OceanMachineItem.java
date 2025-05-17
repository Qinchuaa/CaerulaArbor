
package com.apocalypse.caerulaarbor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class OceanMachineItem extends Item {
	public OceanMachineItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
