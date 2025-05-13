package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;

public class PokeSlightlyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization < 3) {
			DeductPlayerSanityProcedure.execute(entity, Mth.nextInt(RandomSource.create(), 16, 32));
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, (x + 0.5), (y + 0.5), (z + 0.5), 12, 0.75, 0.75, 0.75, 0.1);
	}
}
