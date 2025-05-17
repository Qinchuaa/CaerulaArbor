
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CaerulaArborModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(CaerulaArborModParticleTypes.LIFELOSS.get(), LifelossParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.SHIELDLOSS.get(), ShieldlossParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.BLOODOOZE.get(), BloodoozeParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.KING_SLAY.get(), KingSlayParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.KING_SLAY_RED.get(), KingSlayRedParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.ARCHFIEND_KEEP.get(), ArchfiendKeepParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.ARCHFIEND_RESEV.get(), ArchfiendResevParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.DIZZINESS.get(), DizzinessParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.KNIFEPTC.get(), KnifeptcParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.MISS.get(), MissParticle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.CRACKER_BUFF_0.get(), CrackerBuff0Particle::provider);
		event.registerSpriteSet(CaerulaArborModParticleTypes.CRACKER_BUFF_1.get(), CrackerBuff1Particle::provider);
	}
}
