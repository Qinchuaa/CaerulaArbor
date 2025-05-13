
package com.apocalypse.caerulaarbor.anvilrecipes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.AnvilUpdateEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import com.apocalypse.caerulaarbor.init.CaerulaArborModItems;

@Mod.EventBusSubscriber
public class AnvilChestpAnvilRecipe {
	@SubscribeEvent
	public static void execute(AnvilUpdateEvent event) {
		if ((event.getLeft().getItem() == Items.IRON_HELMET) && (event.getRight().getItem() == CaerulaArborModItems.RELIC_CROWN.get())) {
			if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
				event.setMaterialCost(1);
				event.setCost(5);
				event.setOutput(new ItemStack(CaerulaArborModItems.WEARABLE_CROWN_HELMET.get()));
			}
		}
	}
}
