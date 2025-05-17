package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GiveSpearFightProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.SPEAR_FIGHT.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ModMobEffects.SPEAR_FIGHT.get(), 60, 0, false, false));
		}
	}
}
