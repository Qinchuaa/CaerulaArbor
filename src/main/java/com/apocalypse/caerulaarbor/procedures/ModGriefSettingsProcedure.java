package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.configuration.CaerulaConfigsConfiguration;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;

public class ModGriefSettingsProcedure {
	public static boolean execute(LevelAccessor world) {
		if (CaerulaConfigsConfiguration.BREAKABLE.get()) {
			return world.getLevelData().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
		}
		return false;
	}
}
