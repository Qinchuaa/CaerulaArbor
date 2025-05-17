package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GiveTrailBuffProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.TRAIL_BUFF.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ModMobEffects.TRAIL_BUFF.get(), 40, 0, false, false));
		}
	}
}
