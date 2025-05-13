package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.particles.ParticleTypes;

public class BulletParticleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, ((-0.05) * immediatesourceentity.getDeltaMovement().x()), ((-0.05) * immediatesourceentity.getDeltaMovement().y()), ((-0.05) * immediatesourceentity.getDeltaMovement().z()));
	}
}
