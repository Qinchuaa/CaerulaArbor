package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;

public class CanAttack3Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.tickCount - (entity instanceof LivingEntity _livEnt ? _livEnt.getLastHurtMobTimestamp() : 0) >= (entity instanceof ReaperFishEntity _datEntI ? _datEntI.getEntityData().get(ReaperFishEntity.DATA_delay) : 0);
	}
}
