package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;

public class HasOceanize2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(CaerulaArborModMobEffects.INFESTED.get())) {
			return (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CaerulaArborModMobEffects.INFESTED.get()) ? _livEnt.getEffect(CaerulaArborModMobEffects.INFESTED.get()).getAmplifier() : 0) > 1;
		}
		return false;
	}
}
