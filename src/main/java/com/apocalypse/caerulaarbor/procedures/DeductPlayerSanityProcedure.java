package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;

public class DeductPlayerSanityProcedure {
	public static void execute(Entity player, double num) {
		if (player == null)
			return;
		double snt = 0;
		if (!(player instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModMobEffects.SANITY_IMMUE.get()))) {
			snt = (player instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0)
					- num * (player instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY_MODIFIER.get())
							? _livingEntity2.getAttribute(CaerulaArborModAttributes.SANITY_MODIFIER.get()).getValue()
							: 0);
			if (snt < -1) {
				snt = -1;
			}
			if (snt > 1000) {
				snt = 1000;
			}
			if (player instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
				_livingEntity3.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(snt);
		}
	}
}
