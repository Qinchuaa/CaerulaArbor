package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.entity.GuideAbyssalEntity;

public class CanAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.tickCount - (entity instanceof LivingEntity _livEnt ? _livEnt.getLastHurtMobTimestamp() : 0) >= (entity instanceof GuideAbyssalEntity _datEntI ? _datEntI.getEntityData().get(GuideAbyssalEntity.DATA_delay) : 0);
	}
}
