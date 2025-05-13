
package com.apocalypse.caerulaarbor.anvilrecipes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.AnvilUpdateEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import com.apocalypse.caerulaarbor.init.CaerulaArborModItems;

@Mod.EventBusSubscriber
public class FixMusicBoxAnvilRecipe {
	@SubscribeEvent
	public static void execute(AnvilUpdateEvent event) {
		if ((event.getLeft().getItem() == CaerulaArborModItems.SOLO_MUSIC_BOX.get()) && (event.getRight().getItem() == Items.COPPER_INGOT)) {
			if ((event.getLeft().getCount() == 1) && (event.getRight().getCount() >= 1)) {
				event.setMaterialCost(1);
				event.setCost(4);
				event.setOutput(new ItemStack(CaerulaArborModItems.MUSIC_BOX_FIXED.get()));
			}
		}
	}
}
