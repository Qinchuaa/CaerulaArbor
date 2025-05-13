package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;

public class GiveLessArmorProcedure {
	public static void execute(Entity obj, double limit) {
		if (obj == null)
			return;
		if (obj instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(CaerulaArborModMobEffects.LESS_ARMOR.get())) {
			if ((obj instanceof LivingEntity _livEnt && _livEnt.hasEffect(CaerulaArborModMobEffects.LESS_ARMOR.get()) ? _livEnt.getEffect(CaerulaArborModMobEffects.LESS_ARMOR.get()).getAmplifier() : 0) < limit) {
				if (obj instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.LESS_ARMOR.get(), 300,
							(int) ((obj instanceof LivingEntity _livEnt && _livEnt.hasEffect(CaerulaArborModMobEffects.LESS_ARMOR.get()) ? _livEnt.getEffect(CaerulaArborModMobEffects.LESS_ARMOR.get()).getAmplifier() : 0) + 1), false, true));
			}
		} else {
			if (obj instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.LESS_ARMOR.get(), 300, 0, false, true));
		}
	}
}
