package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

public class FleeBulletHitProcedure {
	public static void execute(Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!(entity == sourceentity)) {
			GiveLessArmorProcedure.execute(entity, 3);
		}
	}
}
