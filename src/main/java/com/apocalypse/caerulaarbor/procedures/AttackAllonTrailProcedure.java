package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.FishSplashEntity;
import com.apocalypse.caerulaarbor.init.ModEntities;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

public class AttackAllonTrailProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double num = 0;
		double rand = 0;
		num = 0;
		for (Entity entityiterator : world.getEntities(entity, new AABB((x + 48), (y + 6), (z + 48), (x - 48), (y - 6), (z - 48)))) {
			if ((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) < 5) {
				continue;
			}
			if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
				continue;
			}
			if (entityiterator instanceof Player) {
				if ((entityiterator.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization >= 3) {
					continue;
				}
			}
			rand = Mth.nextDouble(RandomSource.create(), 7, 11);
			if ((world.getBlockState(BlockPos.containing(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()))).getBlock() == ModBlocks.SEA_TRAIL_GROWN.get()) {
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
							AbstractArrow entityToSpawn = new FishSplashEntity(ModEntities.FISH_SPLASH.get(), level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.setBaseDamage(damage);
							entityToSpawn.setKnockback(knockback);
							entityToSpawn.setSilent(true);
							entityToSpawn.setCritArrow(true);
							return entityToSpawn;
						}
					}.getArrow(projectileLevel, entity, 5, 0);
					_entityToSpawn.setPos((entityiterator.getX()), (entityiterator.getY() + rand), (entityiterator.getZ()));
					_entityToSpawn.shoot(0, (-1), 0, (float) 1.5, 0);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
				num = num + 1;
				new Object() {
					void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.END_ROD, (entityiterator.getX() + ((x - entityiterator.getX()) / 40) * timedloopiterator), (entityiterator.getY() + 9 + ((y - (entityiterator.getY() + 9)) / 40) * timedloopiterator),
									(entityiterator.getZ() + ((z - entityiterator.getZ()) / 40) * timedloopiterator), 4, 0.1, 0.1, 0.1, 0.01);
						final int tick2 = ticks;
						CaerulaArborMod.queueServerWork(tick2, () -> {
							if (timedlooptotal > timedloopiterator + 1) {
								timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
							}
						});
					}
				}.timedLoop(0, 40, 1);
			}
			if (num >= 3) {
				break;
			}
		}
	}
}
