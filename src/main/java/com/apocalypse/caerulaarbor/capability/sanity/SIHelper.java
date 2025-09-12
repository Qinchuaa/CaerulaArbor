package com.apocalypse.caerulaarbor.capability.sanity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class SIHelper {
    public static void causeSanityInjury(LivingEntity target, double value) {
        causeSanityInjury(target, null, value);
    }

    public static void causeSanityInjury(LivingEntity target, @Nullable LivingEntity attacker, double value) {
        ModCapabilities.getSanityInjury(target).hurt(value);
    }

    public static void causeSanityInjuryWithParticles(LivingEntity target, double value) {
        causeSanityInjury(target, null, value);
    }

    public static void causeSanityInjuryWithParticles(LivingEntity target, @Nullable LivingEntity attacker, double value) {
        causeSanityInjury(target, attacker, value);
        for (int i = 1; i < 4; i++) {
            CaerulaArborMod.queueServerWork(i * 3, () -> {
                if (target.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            target.getX(), (target.getY() + 0.5 * target.getBbHeight()), target.getZ(),
                            24, 0.86, 1.2, 0.86, 0.1);
                }
            });
        }
    }
}
