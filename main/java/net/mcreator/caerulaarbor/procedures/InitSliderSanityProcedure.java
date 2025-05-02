package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;

public class InitSliderSanityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY_RATE.get()))
			_livingEntity0.getAttribute(CaerulaArborModAttributes.SANITY_RATE.get()).setBaseValue(10);
	}
}
