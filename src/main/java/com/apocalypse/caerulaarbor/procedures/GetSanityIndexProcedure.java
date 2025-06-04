package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GetSanityIndexProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.ceil(
				(entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(ModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(ModAttributes.SANITY.get()).getBaseValue() : 0) / 50);
	}
}
