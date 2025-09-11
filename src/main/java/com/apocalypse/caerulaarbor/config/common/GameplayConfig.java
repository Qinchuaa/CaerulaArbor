package com.apocalypse.caerulaarbor.config.common;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class GameplayConfig {

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> LIGHTS_RECOVERY_FOOD_LIST;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> KETTLE_HEAT_SOURCE;

    public static ForgeConfigSpec.DoubleValue EVOLUTION_POINT_COEFFICIENT;
    public static ForgeConfigSpec.BooleanValue ENABLE_MOB_BREAK;
    public static ForgeConfigSpec.BooleanValue ENABLE_EVOLUTION_SOUND;

    public static void init(ForgeConfigSpec.Builder builder) {
        builder.push("gameplay");

        // 可用于恢复灯火的食物及其恢复量，“注册名, 最小恢复量, 最大恢复量”，不限制空格
        builder.comment("Foods that can be used to recover lights, \"registry name, min recovery amount, max recovery amount\".");
        LIGHTS_RECOVERY_FOOD_LIST = builder.defineList("lights_recovery_food_list",
                List.of(
                        "minecraft:glow_berries, 2, 3",
                        "minecraft:golden_carrot, 1, 2",
                        "minecraft:golden_apple, 2, 3",
                        "minecraft:enchanted_golden_apple, 3, 5"
                ),
                entry -> {
                    if (!(entry instanceof String string)) return false;
                    String[] split = string.trim().replace(" ", "").split(",");
                    if (split.length != 3) return false;
                    try {
                        Integer.parseInt(split[1]);
                        Integer.parseInt(split[2]);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                });

        // 其它能加热热水壶的方块，最好带点火
        builder.comment("Other blocks that can heat the kettle. It should have fire");
        KETTLE_HEAT_SOURCE = builder.defineList("kettle_heat_source", List.of("create:blaze_burner", "cataclysm:altar_of_fire"), entry -> true);

        // 进化点数系数，实际进化所需点数 = (下一阶段代数^3) * 系数
        builder.comment("The evolution point coefficient, actual evolution points required = (next stage index^3) * coefficient");
        EVOLUTION_POINT_COEFFICIENT = builder.defineInRange("evolution_point_coefficient", 200d, 1, 100000);

        // 开启后，模组生物的行为将受游戏规则：生物破坏的影响
        builder.comment("Enable this, the behavior of mod mobs will be affected by the game rule MobGriefing");
        ENABLE_MOB_BREAK = builder.define("enable_mob_break", true);

        // 允许进化进入新阶段后播放音效提醒（不影响用命令调整进化时的音效）
        builder.comment("Enable this, the evolution sound will be played after entering a new stage");
        ENABLE_EVOLUTION_SOUND = builder.define("enable_evolution_sound", true);

        builder.pop();
    }
}
