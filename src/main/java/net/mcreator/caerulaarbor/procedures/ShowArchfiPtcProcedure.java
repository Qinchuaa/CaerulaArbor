package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModParticleTypes;

public class ShowArchfiPtcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amplifier) {
		if (entity == null)
			return;
		if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).kingShowPtc) {
			if (amplifier < 1) {
				world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.ARCHFIEND_KEEP.get()), (x + Mth.nextDouble(RandomSource.create(), -0.55, 0.55)), (y + Mth.nextDouble(RandomSource.create(), 0, entity.getBbHeight() * 0.6)),
						(z + Mth.nextDouble(RandomSource.create(), -0.55, 0.55)), Math.sin(Mth.nextDouble(RandomSource.create(), 0, 6.283)), 0.05, Math.cos(Mth.nextDouble(RandomSource.create(), 0, 6.283)));
			} else {
				world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.ARCHFIEND_RESEV.get()), (x + Mth.nextDouble(RandomSource.create(), -0.55, 0.55)), (y + Mth.nextDouble(RandomSource.create(), 0, entity.getBbHeight() * 0.6)),
						(z + Mth.nextDouble(RandomSource.create(), -0.55, 0.55)), Math.sin(Mth.nextDouble(RandomSource.create(), 0, 6.283)), 0.05, Math.cos(Mth.nextDouble(RandomSource.create(), 0, 6.283)));
			}
		}
	}
}
