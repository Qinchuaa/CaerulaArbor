package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.client.model.Modelfakebullet;
import com.apocalypse.caerulaarbor.client.model.Modelfishbullet_Converted;
import com.apocalypse.caerulaarbor.client.model.Modelfleefish;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModModels {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(Modelfishbullet_Converted.LAYER_LOCATION, Modelfishbullet_Converted::createBodyLayer);
        event.registerLayerDefinition(Modelfakebullet.LAYER_LOCATION, Modelfakebullet::createBodyLayer);
        event.registerLayerDefinition(Modelfleefish.LAYER_LOCATION, Modelfleefish::createBodyLayer);
    }
}
