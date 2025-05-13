package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.caerulaarbor.CaerulaArborMod;

public class EatPeduncleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		new Object() {
			void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
				DeductPlayerSanityProcedure.execute(entity, 20);
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 0.9), z, 16, 0.55, 1, 0.55, 0.1);
				if (world instanceof Level _level) {
					if (_level.isClientSide()) {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.slime.squish_small")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				entity.push((Mth.nextDouble(RandomSource.create(), -0.1, 0.1)), (Mth.nextDouble(RandomSource.create(), 0, 0.1)), (Mth.nextDouble(RandomSource.create(), -0.1, 0.1)));
				final int tick2 = ticks;
				CaerulaArborMod.queueServerWork(tick2, () -> {
					if (timedlooptotal > timedloopiterator + 1) {
						timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
					}
				});
			}
		}.timedLoop(0, 4, 10);
	}
}
