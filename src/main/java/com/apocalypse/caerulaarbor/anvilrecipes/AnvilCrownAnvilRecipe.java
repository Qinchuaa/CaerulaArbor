
package com.apocalypse.caerulaarbor.anvilrecipes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.AnvilUpdateEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import com.apocalypse.caerulaarbor.init.ModItems;

@Mod.EventBusSubscriber
public class AnvilCrownAnvilRecipe {
	@SubscribeEvent
	public static void execute(AnvilUpdateEvent event) {
		if ((event.getLeft().getItem() == Items.IRON_CHESTPLATE) && (event.getRight().getItem() == ModItems.KINGS_ARMOUR.get())) {
			if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
				event.setMaterialCost(1);
				event.setCost(5);
				event.setOutput(new ItemStack(ModItems.WEARABLE_CHEST_CHESTPLATE.get()));
			}
		}
	}
}
