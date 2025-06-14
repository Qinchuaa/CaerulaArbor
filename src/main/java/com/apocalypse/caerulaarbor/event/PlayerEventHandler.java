package com.apocalypse.caerulaarbor.event;

import com.apocalypse.caerulaarbor.item.relic.IRelic;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerCriticalHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;
        var player = event.getEntity();

        var cap = player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables());
        cap.relics.forEach((relic, integer) -> {
            if (!relic.gained(player)) return;
            var relicItem = relic.relic;
            if (relicItem instanceof IRelic iRelic) {
                relic.set(player, iRelic.onCriticalHit(player, integer));
            }
        });
    }
}
