package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.init.CaerulaArborModEntities;

public class SummonEliteFishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double rand = 0;
		rand = Mth.nextInt(RandomSource.create(), 0, 8);
		if (rand == 0) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.BASELAYER_ABYSSAL.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z),
						MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 1) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.CRACKER_ABYSSAL.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 2) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.CREEPER_FISH.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 3) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.GUIDE_ABYSSAL.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 4) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.PUNCTURE_FISH.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 5) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.REAPER_FISH.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 6) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.UMBRELLA_ABYSSAL.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z),
						MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 7) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.PREGNANT_FISH.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (rand == 8) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = CaerulaArborModEntities.FLEE_FISH.get().spawn(_level, BlockPos.containing(Mth.nextDouble(RandomSource.create(), -1, 1) + x, y, Mth.nextDouble(RandomSource.create(), -1, 1) + z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.CLOUD, x, y, z, 32, 1, 1, 1, 0.1);
	}
}
