
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.caerulaarbor.client.particle.ShieldlossParticle;
import net.mcreator.caerulaarbor.client.particle.MissParticle;
import net.mcreator.caerulaarbor.client.particle.LifelossParticle;
import net.mcreator.caerulaarbor.client.particle.KnifeptcParticle;
import net.mcreator.caerulaarbor.client.particle.KingSlayRedParticle;
import net.mcreator.caerulaarbor.client.particle.KingSlayParticle;
import net.mcreator.caerulaarbor.client.particle.DizzinessParticle;
import net.mcreator.caerulaarbor.client.particle.CrackerBuff1Particle;
import net.mcreator.caerulaarbor.client.particle.CrackerBuff0Particle;
import net.mcreator.caerulaarbor.client.particle.BloodoozeParticle;
import net.mcreator.caerulaarbor.client.particle.ArchfiendResevParticle;
import net.mcreator.caerulaarbor.client.particle.ArchfiendKeepParticle;

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
