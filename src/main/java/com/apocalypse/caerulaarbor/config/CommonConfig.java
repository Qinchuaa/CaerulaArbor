package com.apocalypse.caerulaarbor.config;

import com.apocalypse.caerulaarbor.config.common.GameplayConfig;
import com.apocalypse.caerulaarbor.config.common.RelicsConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

    public static ForgeConfigSpec init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        RelicsConfig.init(builder);
        GameplayConfig.init(builder);

        return builder.build();
    }
}
