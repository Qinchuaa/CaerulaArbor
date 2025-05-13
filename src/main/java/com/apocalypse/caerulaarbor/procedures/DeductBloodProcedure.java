package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import com.apocalypse.caerulaarbor.init.CaerulaArborModParticleTypes;

public class DeductBloodProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double health_cur = 0;
		if (Math.round(entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < Math.round(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
			health_cur = (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) * 0.9;
			if (health_cur < 0.5) {
				health_cur = 0.5;
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0.5 && entity.isAlive()) {
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) health_cur);
				for (int index0 = 0; index0 < 24; index0++) {
					world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.BLOODOOZE.get()), x, (y + 1.33), z, (Mth.nextDouble(RandomSource.create(), -1.25, 1.25)), (Mth.nextDouble(RandomSource.create(), -0.05, 0.05)),
							(Mth.nextDouble(RandomSource.create(), -1.25, 1.25)));
				}
			}
		}
	}
}
