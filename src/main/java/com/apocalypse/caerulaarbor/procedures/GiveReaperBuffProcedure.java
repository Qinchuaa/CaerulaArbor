package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;

public class GiveReaperBuffProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive() && !(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(CaerulaArborModMobEffects.FISH_REAP.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.FISH_REAP.get(), 100, 0, false, false));
		}
	}
}
