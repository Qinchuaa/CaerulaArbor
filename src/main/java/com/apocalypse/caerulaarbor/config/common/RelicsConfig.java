package com.apocalypse.caerulaarbor.config.common;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class RelicsConfig {

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_OF_CHOKER;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_FIREWORK;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_ENGRAVE;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> CRIMSON_TREATY;

    // TODO 规范化命名
    public static void init(ForgeConfigSpec.Builder builder) {
        builder.push("relics");

        // 其它能使收藏品扼喉之手生效的物品，最好是弩
        builder.comment("Other items that can make the Hand of Choker effective. It should be a crossbow");
        HAND_OF_CHOKER = builder.defineList("hand_of_choker", List.of("CrossbowMod2:item.woodenCrossbow"), entry -> true);

        // 其它能使收藏品烟花之手生效的物品。最好是弓
        builder.comment("Other items that can make the Hand of Fireworks effective. It should be a bow");
        HAND_FIREWORK = builder.defineList("hand_firework_recognizable", List.of("twilightforest:triple_bow"), entry -> true);

        // 其它能使收藏品刻勋之手生效的物品。最好是三叉戟
        builder.comment("Other items that can make the Hand of Engraving effective. It should be a trident");
        HAND_ENGRAVE = builder.defineList("hand_engrave_recognizable", List.of("iceandfire:tide_trident"), entry -> true);

        // 其它能使收藏品绯红盟约生效的生物。最好来自下界
        builder.comment("Other creatures that can make the Crimson Treaty effective. It should be from the Nether");
        CRIMSON_TREATY = builder.defineList("crimson_treaty_recognizable", List.of("cataclysm:ignis", "cataclysm:netherite_monstrosity"), entry -> true);

        builder.pop();
    }
}
