package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.init.CaerulaArborModEntities;
import net.mcreator.caerulaarbor.entity.ReaperFishEntity;

public class EntityReaperFishProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? new ReaperFishEntity(CaerulaArborModEntities.REAPER_FISH.get(), _level) : null;
	}
}
