package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;

public class GetSanityIndexProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.ceil(
				(entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0) / 50);
	}
}
