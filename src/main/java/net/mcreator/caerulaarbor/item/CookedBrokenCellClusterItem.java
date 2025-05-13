
package net.mcreator.caerulaarbor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CookedBrokenCellClusterItem extends Item {
	public CookedBrokenCellClusterItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.5f).meat().build()));
	}
}
