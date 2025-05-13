package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;

public class Revive15SanityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
			_livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(
					((entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0)
							+ 15));
	}
}
