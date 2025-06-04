package com.apocalypse.caerulaarbor.capability;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public enum Relic {
    CURSED_EMELIGHT,
    CURSED_GLOWBODY,
    CURSED_RESEARCH,
    KING_CROWN,
    KING_ARMOR,
    KING_SPEAR,
    KING_EXTENSION,
    KING_CRYSTAL,
    HAND_THORNS,
    HAND_STRANGLE,
    HAND_FERTILITY,
    HAND_SPEED,
    HAND_BARREN,
    HAND_SWIPE,
    ARCHFI_ARTIFACT,
    HAND_FIREWORK,
    ARCHFI_FLAG,
    HAND_ENGRAVE(-1, 99, -1),
    ARCHFI_BED,
    SURVIVOR(-1, 32, -1),
    TREATY,
    ARCHIFI_RYLFATE,
    UTIL_MEATCAN,
    UTIL_SEAGRASS,
    UTIL_ORANGE,
    UTIL_COFFEE,
    UTIL_BERRIES,
    UTIL_MUSICBOX,
    UTIL_IRIS,
    UTIL_FLUTE,
    UTIL_VOYGOLD,
    UTIL_DURIN,
    UTIL_TOPONYM,
    UTIL_KETTLE,
    LEGEND_CHITIN,
    UTIL_ALLEY,
    UTIL_BATBED,
    UTIL_LONGEVITY,
    UTIL_OMNIKEY,
    UTIL_SCORE,
    UTIL_RESCISSION,
    UTIL_STARE,
    HAND_SWORD,
    CURSED_HEART;

    public final int minLevel;
    public final int maxLevel;
    public final int defaultLevel;

    Relic() {
        this(0, 1, 0);
    }

    Relic(int minLevel, int maxLevel, int defaultLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.defaultLevel = defaultLevel;
    }

    public int get(Entity player) {
        return player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .map(this::get)
                .orElse(defaultLevel);
    }

    public int get(CaerulaArborModVariables.PlayerVariables variables) {
        return variables.relics.getOrDefault(this, defaultLevel);
    }

    public boolean gained(Entity player) {
        return player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .map(this::gained)
                .orElse(false);
    }

    public boolean gained(CaerulaArborModVariables.PlayerVariables variables) {
        return this.get(variables) != defaultLevel;
    }

    public void reset(Entity player) {
        player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(this::reset);
    }

    public void reset(CaerulaArborModVariables.PlayerVariables variables) {
        variables.relics.remove(this);
    }

    public void set(Entity player, int level) {
        player.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY)
                .ifPresent(c -> set(c, level));
    }

    public void set(CaerulaArborModVariables.PlayerVariables variables, int level) {
        if (level == defaultLevel) {
            variables.relics.remove(this);
            return;
        }
        variables.relics.put(this, Mth.clamp(level, minLevel, maxLevel));
    }
}
