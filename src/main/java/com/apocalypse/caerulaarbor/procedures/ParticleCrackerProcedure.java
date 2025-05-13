package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import com.apocalypse.caerulaarbor.init.CaerulaArborModParticleTypes;

public class ParticleCrackerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amplifier) {
		if (entity == null)
			return;
		double angle = 0;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CaerulaArborModParticleTypes.CRACKER_BUFF_0.get()), x, (y + entity.getBbHeight() * 0.5), z, (int) (amplifier + 1), 0.8, 1.5, 0.8, 0.3);
		if (amplifier > 6) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (CaerulaArborModParticleTypes.CRACKER_BUFF_1.get()), x, y, z, 3, 1, 0.5, 1, 0.3);
		}
	}
}
