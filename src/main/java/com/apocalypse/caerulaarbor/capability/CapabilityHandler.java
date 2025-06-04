package com.apocalypse.caerulaarbor.capability;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.sanity.SanityInjuryProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = CaerulaArborMod.MODID)
public class CapabilityHandler {

    @SubscribeEvent
    public static void registerCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity) {
            event.addCapability(CaerulaArborMod.loc("sanity_injury"), new SanityInjuryProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        Player player = event.getEntity();
        Player oldPlayer = event.getOriginal();

        handleChaosCap(player, oldPlayer);
    }

    private static void handleChaosCap(Player player, Player oldPlayer) {
        oldPlayer.revive();
        var oldChaosCap = oldPlayer.getCapability(ModCapabilities.SANITY_INJURY).resolve();
        var newChaosCap = player.getCapability(ModCapabilities.SANITY_INJURY).resolve();

        if (oldChaosCap.isEmpty() || newChaosCap.isEmpty()) return;

        var newCap = newChaosCap.get();
        var oldCap = oldChaosCap.get();
        newCap.deserializeNBT(oldCap.serializeNBT());
    }
}
