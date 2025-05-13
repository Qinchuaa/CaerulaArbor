package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.entity.RouteShaperEntity;

public class RouteSkilladdProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sklp = 0;
		sklp = entity instanceof RouteShaperEntity _datEntI ? _datEntI.getEntityData().get(RouteShaperEntity.DATA_skillp) : 0;
		if (entity instanceof RouteShaperEntity _datEntSetI)
			_datEntSetI.getEntityData().set(RouteShaperEntity.DATA_skillp, (int) (sklp + 1));
		if (sklp + 1 >= 8) {
			SummonFractalProcedure.execute(world, x, y, z);
			if (entity instanceof RouteShaperEntity _datEntSetI)
				_datEntSetI.getEntityData().set(RouteShaperEntity.DATA_skillp, 0);
		}
	}
}
