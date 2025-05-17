package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class AttractBatProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if ((entityiterator instanceof Bat || entityiterator instanceof LivingEntity _livEnt1 && _livEnt1.getMobType() == MobType.UNDEAD)
						&& new Vec3((x + 0.5), (y + 1), (z + 0.5)).distanceTo(new Vec3((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()))) > 1) {
					if (Math.random() < 0.2) {
						if (entityiterator instanceof Mob _entity)
							_entity.getNavigation().moveTo((x + Mth.nextDouble(RandomSource.create(), -3, 4)), y, (z + Mth.nextDouble(RandomSource.create(), -3, 4)), 1);
					}
				}
			}
		}
	}
}
