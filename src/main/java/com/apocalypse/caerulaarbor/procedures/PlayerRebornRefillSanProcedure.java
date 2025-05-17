package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerRebornRefillSanProcedure {
    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        var entity = event.getEntity();

        var san = entity.getAttribute(CaerulaArborModAttributes.SANITY.get());
        if (san != null) {
            san.setBaseValue(1000);
        }
    }
}
