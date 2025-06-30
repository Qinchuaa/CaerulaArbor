package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ReviveSanityLightProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			{
				double _setval = (entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).light + 0.5;
				entity.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(capability -> {
					capability.light = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).light > 100) {
				{
					double _setval = 100;
					entity.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(capability -> {
						capability.light = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
			if (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttributes().hasAttribute(ModAttributes.SANITY.get()))
				_livingEntity2.getAttribute(ModAttributes.SANITY.get()).setBaseValue(
						((entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(ModAttributes.SANITY.get()) ? _livingEntity1.getAttribute(ModAttributes.SANITY.get()).getBaseValue() : 0)
								+ 40));
			if ((entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(ModAttributes.SANITY.get()) ? _livingEntity3.getAttribute(ModAttributes.SANITY.get()).getBaseValue() : 0) > 1000) {
				if (entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(ModAttributes.SANITY.get()))
					_livingEntity4.getAttribute(ModAttributes.SANITY.get()).setBaseValue(1000);
			}
		}
	}
}
