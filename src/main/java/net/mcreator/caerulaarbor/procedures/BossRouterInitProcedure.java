package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;
import net.mcreator.caerulaarbor.entity.RouteShaperEntity;

public class BossRouterInitProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.INVULNERABLE.get(), 10, 0, false, false));
		if (entity instanceof RouteShaperEntity) {
			((RouteShaperEntity) entity).setAnimation("animation.routeshaper.start");
		}
	}
}
