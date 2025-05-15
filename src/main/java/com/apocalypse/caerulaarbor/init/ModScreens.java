package com.apocalypse.caerulaarbor.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import com.apocalypse.caerulaarbor.client.gui.RelicShowcaseScreen;
import com.apocalypse.caerulaarbor.client.gui.InfoStrategySubsisScreen;
import com.apocalypse.caerulaarbor.client.gui.InfoStrategyMigrationScreen;
import com.apocalypse.caerulaarbor.client.gui.InfoStrategyGrowScreen;
import com.apocalypse.caerulaarbor.client.gui.InfoStrategyBreedScreen;
import com.apocalypse.caerulaarbor.client.gui.InfoStrategyAllScreen;
import com.apocalypse.caerulaarbor.client.gui.EvoTreeScreen;
import com.apocalypse.caerulaarbor.client.gui.CaerulaRecordGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.CAERULA_RECORD_GUI.get(), CaerulaRecordGUIScreen::new);
            MenuScreens.register(ModMenus.RELIC_SHOWCASE.get(), RelicShowcaseScreen::new);
            MenuScreens.register(ModMenus.INFO_STRATEGY_SUBSIS.get(), InfoStrategySubsisScreen::new);
            MenuScreens.register(ModMenus.INFO_STRATEGY_BREED.get(), InfoStrategyBreedScreen::new);
            MenuScreens.register(ModMenus.INFO_STRATEGY_MIGRATION.get(), InfoStrategyMigrationScreen::new);
            MenuScreens.register(ModMenus.INFO_STRATEGY_GROW.get(), InfoStrategyGrowScreen::new);
            MenuScreens.register(ModMenus.INFO_STRATEGY_ALL.get(), InfoStrategyAllScreen::new);
            MenuScreens.register(ModMenus.EVO_TREE.get(), EvoTreeScreen::new);
        });
    }
}
