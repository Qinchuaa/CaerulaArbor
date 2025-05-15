package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.apocalypse.caerulaarbor.init.ModMobEffects;

public class GiveLessArmorProcedure {
	public static void execute(Entity obj, double limit) {
		if (obj == null)
			return;
		if (obj instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.LESS_ARMOR.get())) {
			if ((obj instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.LESS_ARMOR.get()) ? _livEnt.getEffect(ModMobEffects.LESS_ARMOR.get()).getAmplifier() : 0) < limit) {
				if (obj instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(ModMobEffects.LESS_ARMOR.get(), 300,
							(int) ((obj instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.LESS_ARMOR.get()) ? _livEnt.getEffect(ModMobEffects.LESS_ARMOR.get()).getAmplifier() : 0) + 1), false, true));
			}
		} else {
			if (obj instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ModMobEffects.LESS_ARMOR.get(), 300, 0, false, true));
		}
	}
}
