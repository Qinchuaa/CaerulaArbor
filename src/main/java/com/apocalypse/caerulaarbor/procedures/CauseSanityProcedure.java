package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class CauseSanityProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        DeductPlayerSanityProcedure.execute(entity, 120);

        for (int i = 0; i < 3; i++) {
            CaerulaArborMod.queueServerWork(i * 5, () -> {
                if (world instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 0.5 * entity.getBbHeight()), z, 24, 0.86, 1.2, 0.86, 0.1);
                }
            });
        }
    }
}
