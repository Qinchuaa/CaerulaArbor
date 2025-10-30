package com.apocalypse.caerulaarbor.config.server;

import net.minecraftforge.common.ForgeConfigSpec;

public class SanityConfig {

    public static ForgeConfigSpec.BooleanValue CREATIVE_RECEIVE_SANITY_INJURY;
    public static ForgeConfigSpec.BooleanValue PLAYER_BREAK_USES_PALSY;

    public static void init(ForgeConfigSpec.Builder builder) {
        builder.push("sanity");

        CREATIVE_RECEIVE_SANITY_INJURY = builder.define("creative_receive_sanity_injury", false);
        // 玩家爆条时是否改用麻痹效果（默认关闭，保持原来的眩晕/失明）
        PLAYER_BREAK_USES_PALSY = builder.define("player_break_uses_palsy", false);

        builder.pop();
    }
}