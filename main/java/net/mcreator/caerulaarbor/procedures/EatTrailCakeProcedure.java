package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

public class EatTrailCakeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		DeductPlayerSanityProcedure.execute(entity, 150);
	}
}
