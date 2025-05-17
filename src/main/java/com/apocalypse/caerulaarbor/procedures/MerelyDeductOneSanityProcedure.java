package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class MerelyDeductOneSanityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amplifier) {
		if (entity == null)
			return;
		DeductPlayerSanityProcedure.execute(entity, amplifier + 1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 1), z, (int) (amplifier + 1), 0.5, 0.8, 0.5, 0.1);
	}
}
