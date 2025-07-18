package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.entity.RouteFractalEntity;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SummonFractalProcedure {
    public static void execute(Level world, double x, double y, double z) {
        final Vec3 center = new Vec3(x, y, z);
        var num = world.getEntitiesOfClass(RouteFractalEntity.class, new AABB(center, center).inflate(32), e -> true).size();

        if (num < 12) {
            var dx = Mth.nextDouble(RandomSource.create(), -0.5, 0.5);
            var dz = Mth.nextDouble(RandomSource.create(), -0.5, 0.5);

            if (world instanceof ServerLevel server) {
                Entity entityToSpawn = ModEntities.ROUTE_FRACTAL.get().spawn(server, BlockPos.containing(x + dx, y, z + dz), MobSpawnType.MOB_SUMMONED);
                if (entityToSpawn != null) {
                    entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
                }

                server.sendParticles(ParticleTypes.CLOUD, (x + dx), y, (z + dz), 48, 0.5, 1, 0.5, 0.1);
            }
        }
    }
}
