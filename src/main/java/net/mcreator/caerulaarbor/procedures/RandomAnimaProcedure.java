package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.entity.PregnantFishEntity;

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
