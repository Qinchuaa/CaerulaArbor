package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ExpFuncProcedure {
	@SubscribeEvent
	public static void onLivingDropXp(LivingExperienceDropEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getAttackingPlayer(), event.getOriginalExperience());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity, double originalexperience) {
		execute(null, world, x, y, z, sourceentity, originalexperience);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity, double originalexperience) {
		if (sourceentity == null)
			return;
		double exp_left = 0;
		if (sourceentity instanceof Player && (sourceentity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_EXTENSION) {
			if ((sourceentity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).lives <= 1) {
				exp_left = originalexperience;
				while (exp_left >= 11) {
					if (world instanceof ServerLevel _level)
						_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.1, 0.85)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 11));
					exp_left = exp_left - 11;
				}
				while (exp_left >= 5) {
					if (world instanceof ServerLevel _level)
						_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.1, 0.85)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 7));
					exp_left = exp_left - 7;
				}
				while (exp_left >= 3) {
					if (world instanceof ServerLevel _level)
						_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.1, 0.85)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 3));
					exp_left = exp_left - 3;
				}
				while (exp_left >= 1) {
					if (world instanceof ServerLevel _level)
						_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.1, 0.85)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 1));
					exp_left = exp_left - 1;
				}
			}
		}
	}
}
