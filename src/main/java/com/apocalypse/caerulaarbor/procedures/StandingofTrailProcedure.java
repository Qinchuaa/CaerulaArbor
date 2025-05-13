package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.init.CaerulaArborModBlocks;

public class StandingofTrailProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CaerulaArborModBlocks.SEA_TRAIL_GROWN.get()) {
			if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.INVISIBILITY) ? _livEnt.getEffect(MobEffects.INVISIBILITY).getDuration() : 0) <= 5) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0));
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SMOKE, x, (y + 1), z, 4, 0.4, 2, 0.4, 0.01);
		}
	}
}
