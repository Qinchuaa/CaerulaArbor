
package net.mcreator.caerulaarbor.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class OceanMachineItem extends Item {
	public OceanMachineItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.UNCOMMON));
	}
}
