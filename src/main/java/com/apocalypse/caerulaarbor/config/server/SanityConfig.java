package com.apocalypse.caerulaarbor.config.server;

import net.minecraftforge.common.ForgeConfigSpec;

public class SanityConfig {

    public static ForgeConfigSpec.BooleanValue CREATIVE_RECEIVE_SANITY_INJURY;

    public static void init(ForgeConfigSpec.Builder builder) {
        builder.push("sanity");

        CREATIVE_RECEIVE_SANITY_INJURY = builder.define("creative_receive_sanity_injury", false);

        builder.pop();
    }
}