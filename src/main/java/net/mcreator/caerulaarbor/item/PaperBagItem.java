
package net.mcreator.caerulaarbor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PaperBagItem extends Item {
	public PaperBagItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
