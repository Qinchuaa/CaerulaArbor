package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ReviveLightsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).light + 8;
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
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0));
	}
}
