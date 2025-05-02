package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;

public class UmbrellaRimProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double angle = 0;
		for (int index0 = 0; index0 < 8; index0++) {
			angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, (x + 2.5 * Math.sin(angle)), (y + 0.4), (z + 2.5 * Math.cos(angle)), 2, 0.1, 0.1, 0.1, 0.1);
			angle = Mth.nextDouble(RandomSource.create(), 0, 6.283);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, (x + 2.5 * Math.sin(angle)), (y + 0.4), (z + 2.5 * Math.cos(angle)), 2, 0.1, 0.1, 0.1, 0.1);
		}
		if (!(entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(CaerulaArborModMobEffects.UMBRELLA_SETTLE.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.UMBRELLA_SETTLE.get(), 120, 0, false, false));
		}
	}
}
