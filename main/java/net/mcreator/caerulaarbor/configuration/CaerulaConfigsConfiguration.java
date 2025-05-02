package net.mcreator.caerulaarbor.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CaerulaConfigsConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> LIGHTS_FOOD;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_STRANGLE;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_FIREWORK;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_ENGRAVE;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CRIMSON_TREATY;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> BOIL_WATER;
	public static final ForgeConfigSpec.ConfigValue<Double> COEFFICIENT;
	static {
		BUILDER.push("foods");
		LIGHTS_FOOD = BUILDER.comment("可用于恢复灯火的食物及其恢复量，“注册名, 最小恢复量/最大恢复量”，逗号后有1空格。").defineList("lights_revovery",
				List.of("minecraft:glow_berries, 2/3", "minecraft:golden_carrot, 1/2", "minecraft:golden_apple, 2/3", "minecraft:enchanted_golden_apple, 3/5"), entry -> true);
		BUILDER.pop();
		BUILDER.push("relics");
		HAND_STRANGLE = BUILDER.comment("其它能使收藏品扼喉之手生效的物品。最好是弩。").defineList("hand_strangle_recognizable", List.of("CrossbowMod2:item.woodenCrossbow"), entry -> true);
		HAND_FIREWORK = BUILDER.comment("其它能使收藏品烟花之手生效的物品。最好是弓。").defineList("hand_firework_recognizable", List.of("twilightforest:triple_bow"), entry -> true);
		HAND_ENGRAVE = BUILDER.comment("其它能使收藏品刻勋之手生效的物品。最好是三叉戟。").defineList("hand_engrave_recognizable", List.of("iceandfire:tide_trident"), entry -> true);
		CRIMSON_TREATY = BUILDER.comment("其它能使收藏品绯红盟约生效的生物。最好来自下界。").defineList("crimson_treaty_recognizable", List.of("cataclysm:ignis", "cataclysm:netherite_monstrosity"), entry -> true);
		BUILDER.pop();
		BUILDER.push("blocks");
		BOIL_WATER = BUILDER.comment("其它能加热热水壶的方块。最好带点火。").defineList("water_boil_block", List.of("create:blaze_burner", "cataclysm:altar_of_fire"), entry -> true);
		BUILDER.pop();
		BUILDER.push("gameplay");
		COEFFICIENT = BUILDER.comment("进化点数系数，实际进化所需点数=(下一阶段代数^3)*系数。").define("evolution_point_coeffiient", (double) 200);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
