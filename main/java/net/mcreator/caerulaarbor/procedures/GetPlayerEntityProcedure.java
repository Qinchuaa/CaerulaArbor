package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.Entity;

public class GetPlayerEntityProcedure {
	public static Entity execute(Entity entity) {
		if (entity == null)
			return null;
		return entity;
	}
}
