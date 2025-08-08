package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.client.gui.CaerulaRecordGUIScreen;
import com.apocalypse.caerulaarbor.client.gui.EvoTreeScreen;
import com.apocalypse.caerulaarbor.client.gui.RelicShowcaseScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.CAERULA_RECORDER.get(), CaerulaRecordGUIScreen::new);
            MenuScreens.register(ModMenus.RELIC_SHOWCASE.get(), RelicShowcaseScreen::new);
            MenuScreens.register(ModMenus.EVO_TREE.get(), EvoTreeScreen::new);
        });
    }
}
