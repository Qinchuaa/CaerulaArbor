package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;

public class HealSanityProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		double newsanity = 0;
		newsanity = (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0)
				+ 100 * (amplifier + 1);
		if (newsanity > 1000) {
			newsanity = 1000;
		}
		if (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
			_livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(newsanity);
	}
}
