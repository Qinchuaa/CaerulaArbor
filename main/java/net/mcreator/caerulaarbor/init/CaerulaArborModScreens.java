
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.caerulaarbor.client.gui.RelicShowcaseScreen;
import net.mcreator.caerulaarbor.client.gui.InfoStrategySubsisScreen;
import net.mcreator.caerulaarbor.client.gui.InfoStrategyMigrationScreen;
import net.mcreator.caerulaarbor.client.gui.InfoStrategyGrowScreen;
import net.mcreator.caerulaarbor.client.gui.InfoStrategyBreedScreen;
import net.mcreator.caerulaarbor.client.gui.InfoStrategyAllScreen;
import net.mcreator.caerulaarbor.client.gui.CaerulaRecordGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CaerulaArborModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(CaerulaArborModMenus.CAERULA_RECORD_GUI.get(), CaerulaRecordGUIScreen::new);
			MenuScreens.register(CaerulaArborModMenus.RELIC_SHOWCASE.get(), RelicShowcaseScreen::new);
			MenuScreens.register(CaerulaArborModMenus.INFO_STRATEGY_SUBSIS.get(), InfoStrategySubsisScreen::new);
			MenuScreens.register(CaerulaArborModMenus.INFO_STRATEGY_BREED.get(), InfoStrategyBreedScreen::new);
			MenuScreens.register(CaerulaArborModMenus.INFO_STRATEGY_MIGRATION.get(), InfoStrategyMigrationScreen::new);
			MenuScreens.register(CaerulaArborModMenus.INFO_STRATEGY_GROW.get(), InfoStrategyGrowScreen::new);
			MenuScreens.register(CaerulaArborModMenus.INFO_STRATEGY_ALL.get(), InfoStrategyAllScreen::new);
		});
	}
}
