
/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CaerulaArborModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.LIBRARIAN) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 8), new ItemStack(Items.PAPER), new ItemStack(CaerulaArborModItems.RESCISSION.get()), 4, 4, 0.02f));
		}
		if (event.getType() == VillagerProfession.TOOLSMITH) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12), new ItemStack(Items.EMERALD, 8), new ItemStack(CaerulaArborModItems.OMNI_KEY.get()), 2, 8, 0.05f));
		}
		if (event.getType() == CaerulaArborModVillagerProfessions.CANNOT_GOODENOUGH.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 24), new ItemStack(Blocks.RED_WOOL, 16), new ItemStack(CaerulaArborModItems.ARCHFIENDS_FLAG.get()), 1, 12, 0.04f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16), new ItemStack(Items.WHITE_BED), new ItemStack(CaerulaArborModItems.ARCHFIENDS_BED.get()), 1, 8, 0.04f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16), new ItemStack(Items.GOLD_INGOT, 8), new ItemStack(CaerulaArborModItems.KINGS_EXTENSION.get()), 1, 8, 0.04f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 24), new ItemStack(Items.NETHERITE_SCRAP), new ItemStack(CaerulaArborModItems.KINGS_SPEAR.get()), 10, 5, 0.04f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 12), new ItemStack(Items.PRISMARINE_SHARD, 16), new ItemStack(CaerulaArborModItems.ODD_FLUTE.get()), 10, 5, 0.04f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(CaerulaArborModItems.REDSTONE_INGOT.get(), 16), new ItemStack(Blocks.GRASS_BLOCK, 32), new ItemStack(CaerulaArborModBlocks.REDSTONEIRIS_SEEDING.get()), 4, 8, 0.04f));
		}
	}
}
