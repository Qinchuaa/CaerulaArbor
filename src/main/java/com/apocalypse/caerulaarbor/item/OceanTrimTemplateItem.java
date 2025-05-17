
package com.apocalypse.caerulaarbor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class OceanTrimTemplateItem extends Item {
	public OceanTrimTemplateItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.COMMON));
	}
}
