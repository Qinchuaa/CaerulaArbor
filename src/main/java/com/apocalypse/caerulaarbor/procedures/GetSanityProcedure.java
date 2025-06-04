package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class GetSanityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + Math
				.round(entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(ModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(ModAttributes.SANITY.get()).getBaseValue() : 0);
	}
}
