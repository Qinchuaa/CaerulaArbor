package com.apocalypse.caerulaarbor.item.relic;

import com.apocalypse.caerulaarbor.capability.Relic;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public interface IRelic {

    @Nullable
    default Relic getRelic() {
        return null;
    }

    default int onCriticalHit(Player player, int level) {
        return 0;
    }
}
