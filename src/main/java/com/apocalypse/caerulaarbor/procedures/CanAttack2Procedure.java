package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.entity.PunctureFishEntity;

public class CanAttack2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.tickCount - (entity instanceof LivingEntity _livEnt ? _livEnt.getLastHurtMobTimestamp() : 0) >= (entity instanceof PunctureFishEntity _datEntI ? _datEntI.getEntityData().get(PunctureFishEntity.DATA_delay) : 0);
	}
}
