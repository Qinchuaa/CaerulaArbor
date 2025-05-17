package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.CaerulaArborModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

public class ShowDizptcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CaerulaArborModParticleTypes.DIZZINESS.get()), x, y, z, 2, 1, 1, 1, 0.1);
		world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.DIZZINESS.get()), x, y, z, (0.5 - Math.random()), 0.1, (0.5 - Math.random()));
	}
}
