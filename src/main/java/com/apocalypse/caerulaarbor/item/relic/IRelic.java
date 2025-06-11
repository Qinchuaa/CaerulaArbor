package com.apocalypse.caerulaarbor.item.relic;

import com.apocalypse.caerulaarbor.capability.Relic;
import org.jetbrains.annotations.Nullable;

public interface IRelic {

    @Nullable
    default Relic getRelic() {
        return null;
    }
}
