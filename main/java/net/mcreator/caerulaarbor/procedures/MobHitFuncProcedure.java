package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;
import net.mcreator.caerulaarbor.entity.PredatorAbyssalEntity;
import net.mcreator.caerulaarbor.CaerulaArborMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MobHitFuncProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(),
					event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, world, x, y, z, damagesource, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (damagesource == null || entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		if (entity instanceof PredatorAbyssalEntity) {
			if (!(entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(CaerulaArborModMobEffects.DIZZY.get()) || entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(CaerulaArborModMobEffects.FROZEN.get())
					|| entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) || entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(MobEffects.SLOW_FALLING))
					&& !(damagesource.is(DamageTypes.GENERIC_KILL) || damagesource.is(DamageTypes.FELL_OUT_OF_WORLD) || damagesource.is(DamageTypes.SONIC_BOOM) || damagesource.is(DamageTypes.WITHER))) {
				if (Math.random() < 0.8) {
					if (entity instanceof PredatorAbyssalEntity) {
						((PredatorAbyssalEntity) entity).setAnimation("animation.predator.miss");
					}
					if (event != null && event.isCancelable()) {
						event.setCanceled(true);
					}
				}
			}
		}
		if (CaerulaArborModVariables.MapVariables.get(world).strategy_grow >= 3) {
			if (sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring"))) && !damagesource.is(DamageTypes.MAGIC)) {
				CaerulaArborMod.queueServerWork(10, () -> {
					entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), immediatesourceentity, sourceentity),
							(float) (amount * 0.2 * (CaerulaArborModVariables.MapVariables.get(world).strategy_grow - 2)));
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.ENCHANTED_HIT, x, (y + 1), z, 48, 1.5, 1.5, 1.5, 0.2);
				});
			}
		}
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring"))) && !sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
			if (CaerulaArborModVariables.MapVariables.get(world).strategy_migration > 0) {
				for (Entity entityiterator : world.getEntities(entity,
						new AABB((x - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)), (y - 16), (z - (8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)),
								(x + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24), (y + 16), (z + 8 + CaerulaArborModVariables.MapVariables.get(world).strategy_migration * 24)))) {
					if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
						if (entityiterator instanceof Mob _entity)
							_entity.getNavigation().moveTo(x, y, z, 1);
						if (entityiterator instanceof Mob _entity && sourceentity instanceof LivingEntity _ent)
							_entity.setTarget(_ent);
					}
				}
			}
		}
		if (sourceentity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
			CaerulaArborModVariables.MapVariables.get(world).evo_point_grow = CaerulaArborModVariables.MapVariables.get(world).evo_point_grow + amount * 0.025;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
			UpgradeGrowProcedure.execute(world);
		}
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
			CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting = CaerulaArborModVariables.MapVariables.get(world).evo_point_subsisting + amount * 0.025;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
			UpgradeSubsisProcedure.execute(world);
		}
	}
}
