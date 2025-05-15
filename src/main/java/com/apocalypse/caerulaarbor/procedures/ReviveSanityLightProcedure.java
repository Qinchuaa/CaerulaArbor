package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;

public class ReviveSanityLightProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			{
				double _setval = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).light + 0.5;
				entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.light = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).light > 100) {
				{
					double _setval = 100;
					entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.light = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
				_livingEntity2.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(
						((entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0)
								+ 40));
			if ((entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity3.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0) > 1000) {
				if (entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
					_livingEntity4.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(1000);
			}
		}
	}
}
