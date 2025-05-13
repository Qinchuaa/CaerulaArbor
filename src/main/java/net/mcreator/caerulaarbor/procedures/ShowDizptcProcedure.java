package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.caerulaarbor.init.CaerulaArborModParticleTypes;

public class ShowDizptcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CaerulaArborModParticleTypes.DIZZINESS.get()), x, y, z, 2, 1, 1, 1, 0.1);
		world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.DIZZINESS.get()), x, y, z, (0.5 - Math.random()), 0.1, (0.5 - Math.random()));
	}
}
