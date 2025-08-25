package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.LIFELOSS.get(), LifelossParticle::provider);
        event.registerSpriteSet(ModParticleTypes.SHIELDLOSS.get(), ShieldlossParticle::provider);
        event.registerSpriteSet(ModParticleTypes.BLOODOOZE.get(), BloodoozeParticle::provider);
        event.registerSpriteSet(ModParticleTypes.KING_SLAY.get(), KingSlayParticle::provider);
        event.registerSpriteSet(ModParticleTypes.KING_SLAY_RED.get(), KingSlayRedParticle::provider);
        event.registerSpriteSet(ModParticleTypes.ARCHFIEND_KEEP.get(), ArchfiendKeepParticle::provider);
        event.registerSpriteSet(ModParticleTypes.ARCHFIEND_RESEV.get(), ArchfiendResevParticle::provider);
        event.registerSpriteSet(ModParticleTypes.DIZZINESS.get(), DizzinessParticle::provider);
        event.registerSpriteSet(ModParticleTypes.KNIFEPTC.get(), KnifeptcParticle::provider);
        event.registerSpriteSet(ModParticleTypes.MISS.get(), MissParticle::provider);
        event.registerSpriteSet(ModParticleTypes.CRACKER_BUFF_0.get(), CrackerBuff0Particle::provider);
        event.registerSpriteSet(ModParticleTypes.CRACKER_BUFF_1.get(), CrackerBuff1Particle::provider);

        event.registerSpriteSet(ModParticleTypes.PERMANENT_YELLOW.get(), PermanentYellowParticle::provider);
        event.registerSpriteSet(ModParticleTypes.PERMANENT_BLUE.get(), PermanentBlueParticle::provider);
        event.registerSpriteSet(ModParticleTypes.PERMANENT_VIOLET.get(), PermanentVioletParticle::provider);
    }
}
