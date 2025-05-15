package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.apocalypse.caerulaarbor.init.ModMobEffects;

public class EatShinePeduncleProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ModMobEffects.DEDUCT_ONE_SANITY.get(), 600, 0, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ModMobEffects.ADD_ATTACK_PERCLY.get(), 600, 3, false, true));
	}
}
