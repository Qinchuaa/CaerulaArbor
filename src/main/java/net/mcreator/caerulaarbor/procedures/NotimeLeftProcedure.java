package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.caerulaarbor.entity.RouteFractalEntity;

public class NotimeLeftProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double timel = 0;
		timel = entity instanceof RouteFractalEntity _datEntI ? _datEntI.getEntityData().get(RouteFractalEntity.DATA_time_left) : 0;
		if (timel <= 0) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FELL_OUT_OF_WORLD)), 999999);
		}
		if (entity instanceof RouteFractalEntity _datEntSetI)
			_datEntSetI.getEntityData().set(RouteFractalEntity.DATA_time_left, (int) (timel - 1));
	}
}
