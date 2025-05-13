package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;

public class DeductBufflvlProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		if (amplifier >= 2) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.REEF_CRACKER.get(), 60, (int) (amplifier - 2), false, false));
		}
	}
}
