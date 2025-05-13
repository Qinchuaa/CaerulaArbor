package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;

public class EatCaramelCakeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1));
		if (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
			_livingEntity2.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(
					((entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0)
							+ 125));
	}
}
