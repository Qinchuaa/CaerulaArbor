package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.entity.PregnantFishEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class RandomAnimaProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive()) && Math.random() < 0.001) {
			if (entity instanceof PregnantFishEntity) {
				((PregnantFishEntity) entity).setAnimation("animation.pregnant.random");
			}
		}
	}
}
