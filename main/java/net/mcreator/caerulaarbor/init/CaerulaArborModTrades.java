
/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CaerulaArborModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == CaerulaArborModVillagerProfessions.MYSTERIOUS_TRADER.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 8),

					new ItemStack(CaerulaArborModItems.MEAT_CAN.get()), 12, 5, 0f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 8),

					new ItemStack(CaerulaArborModItems.BOWL_SEAGRASS.get()), 12, 5, 0f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 8),

					new ItemStack(CaerulaArborModItems.GOLDEN_STORM.get()), 12, 5, 0f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.COFFEE_CANDY.get()), 8, 8, 0.05f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16),

					new ItemStack(CaerulaArborModItems.RAINBOW_CANDY.get()), 5, 12, 0.08f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.CANNED_CHERRY.get()), 8, 5, 0.05f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.AROMATIC_COFFEE.get()), 6, 6, 0.05f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.SOLO_MUSIC_BOX.get()), 3, 8, 0.05f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16),

					new ItemStack(CaerulaArborModBlocks.REDSTONEIRIS_SEEDING.get()), 5, 12, 0.08f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.PIGLIN_DIARY.get()), 2, 8, 0.05f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.TOPONYM_TEXTOLOGY.get()), 1, 8, 0.05f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12),

					new ItemStack(CaerulaArborModItems.ODD_FLUTE.get()), 2, 8, 0.05f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16),

					new ItemStack(CaerulaArborModItems.VOYAGE_OF_GOLD.get()), 3, 12, 0.08f));
		}
	}
}
