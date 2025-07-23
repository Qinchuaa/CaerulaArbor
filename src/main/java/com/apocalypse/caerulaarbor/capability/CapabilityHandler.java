package com.apocalypse.caerulaarbor.capability;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import com.apocalypse.caerulaarbor.capability.sanity.ISanityInjuryCapability;
import com.apocalypse.caerulaarbor.capability.sanity.SanityInjuryCapability;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CapabilityHandler {

    public static ISanityInjuryCapability getSanityInjury(LivingEntity entity) {
        return entity.getCapability(ModCapabilities.SANITY_INJURY).orElseGet(
                () -> {
                    CaerulaArborMod.LOGGER.warn("Failed to get capability {} for entity {} ", ModCapabilities.SANITY_INJURY, entity);
                    return new SanityInjuryCapability(entity);
                }
        );
    }

    public static PlayerVariable getPlayerVariables(Entity entity) {
        return entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElseGet(
                () -> {
                    CaerulaArborMod.LOGGER.warn("Failed to get capability {} for entity {} ", ModCapabilities.PLAYER_VARIABLE, entity);
                    return new PlayerVariable();
                }
        );
    }
}
