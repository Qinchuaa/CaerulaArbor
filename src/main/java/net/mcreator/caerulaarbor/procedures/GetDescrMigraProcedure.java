package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class GetDescrMigraProcedure {
	public static String execute(LevelAccessor world) {
		return Component.translatable(("item.caerula_arbor.sample_migration.description_" + Math.round(CaerulaArborModVariables.MapVariables.get(world).strategy_migration))).getString();
	}
}
