package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;

import net.mcreator.caerulaarbor.configuration.CaerulaConfigsConfiguration;

public class ModGriefSettingsProcedure {
	public static boolean execute(LevelAccessor world) {
		if (CaerulaConfigsConfiguration.BREAKABLE.get()) {
			return world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
		}
		return false;
	}
}
