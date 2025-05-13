package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

public class InsSanityProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		DeductPlayerSanityProcedure.execute(entity, 125 * (amplifier + 1));
	}
}
