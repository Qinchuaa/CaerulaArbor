package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.CaerulaArborModEntities;
import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;

public class EntityReaperFishProcedure {
	public static Entity execute(LevelAccessor world) {
		return world instanceof Level _level ? new ReaperFishEntity(CaerulaArborModEntities.REAPER_FISH.get(), _level) : null;
	}
}
