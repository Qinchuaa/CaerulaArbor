package com.apocalypse.caerulaarbor.item.relic;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public interface IRelic {

    @Nullable
    default Relic getRelic() {
        return null;
    }

    default int onCriticalHit(Player player, int level) {
        return 0;
    }

    @NotNull
    default Map<Attribute, AttributeModifier> getRelicAttributeModifiers(Player player) {
        return new HashMap<>();
    }
}
