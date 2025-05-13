package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

public class FrozenBuffProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Creeper) {
			if (entity instanceof Creeper _creeper)
				_creeper.setSwellDir(0);
		}
		if (entity instanceof Blaze) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FREEZE)), 1);
		}
		if (entity instanceof MagmaCube) {
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FREEZE)), 1);
		}
	}
}
