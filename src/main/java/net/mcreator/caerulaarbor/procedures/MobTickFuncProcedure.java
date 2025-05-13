package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;
import net.mcreator.caerulaarbor.init.CaerulaArborModGameRules;
import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

@Mod.EventBusSubscriber
public class MobTickFuncProcedure {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean valid = false;
		double modifi = 0;
		double suitArchfi = 0;
		double suitKing = 0;
		double amplifi = 0;
		double enchant = 0;
		double pnt = 0;
		if ((entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()) ? _livingEntity0.getAttribute(CaerulaArborModAttributes.SANITY.get()).getBaseValue() : 0) < 0) {
			if (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
				_livingEntity1.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(0);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get(), 200, 0, false, false));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.DIZZY.get(), 200, 0, false, false));
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0, false, true));
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FELL_OUT_OF_WORLD)), 12);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 2.2, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.elder_guardian.curse")), SoundSource.NEUTRAL, (float) 2.2, 1, false);
				}
			}
			if (!(entity instanceof LivingEntity _entity ? _entity.canBeAffected(new MobEffectInstance(CaerulaArborModMobEffects.SANITY_IMMUE.get())) : true)) {
				if (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(CaerulaArborModAttributes.SANITY.get()))
					_livingEntity9.getAttribute(CaerulaArborModAttributes.SANITY.get()).setBaseValue(1000);
			}
		}
		if (!(world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(new ResourceLocation("caerula_arbor:sea_trail")))) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(CaerulaArborModMobEffects.TRAIL_BUFF.get());
		}
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
			if (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting >= 3) {
				if (!(entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(MobEffects.DAMAGE_RESISTANCE))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, (int) (CaerulaArborModVariables.MapVariables.get(world).strategy_subsisting - 3)));
				}
			}
			if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence > 0) {
				if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence >= 3) {
					if (!(entity instanceof LivingEntity _livEnt16 && _livEnt16.hasEffect(CaerulaArborModMobEffects.BOOST_OF_SILENCE.get()))) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.BOOST_OF_SILENCE.get(), 20, (int) (CaerulaArborModVariables.MapVariables.get(world).strategy_silence - 1)));
					}
					if (!(entity instanceof LivingEntity _livEnt18 && _livEnt18.hasEffect(CaerulaArborModMobEffects.STRENGTH_OF_CROWD.get()))) {
						amplifi = -1;
						if (CaerulaArborModVariables.MapVariables.get(world).strategy_silence >= 4) {
							{
								final Vec3 _center = new Vec3(x, y, z);
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entityiterator == entity) && entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
										amplifi = amplifi + 2;
									}
									if (amplifi > 29) {
										amplifi = 29;
										break;
									}
								}
							}
						} else {
							{
								final Vec3 _center = new Vec3(x, y, z);
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entityiterator == entity) && entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))) {
										amplifi = amplifi + 1;
									}
									if (amplifi > 9) {
										amplifi = 9;
										break;
									}
								}
							}
						}
						if (amplifi >= 0) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.STRENGTH_OF_CROWD.get(), 20, (int) amplifi, false, false));
						}
					}
				} else {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.5) {
						if (!(entity instanceof LivingEntity _livEnt28 && _livEnt28.hasEffect(CaerulaArborModMobEffects.BOOST_OF_SILENCE.get()))) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.BOOST_OF_SILENCE.get(), 20, (int) (CaerulaArborModVariables.MapVariables.get(world).strategy_silence - 1)));
						}
					}
				}
				if (!(entity instanceof LivingEntity _livEnt30 && _livEnt30.hasEffect(MobEffects.REGENERATION))) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, (int) CaerulaArborModVariables.MapVariables.get(world).strategy_silence));
				}
				if (entity instanceof Mob _mobEnt32 && _mobEnt32.isAggressive()) {
					if (!(entity instanceof LivingEntity _livEnt33 && _livEnt33.hasEffect(MobEffects.MOVEMENT_SPEED))) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, (int) CaerulaArborModVariables.MapVariables.get(world).strategy_silence));
					}
				}
			}
			if (world.getLevelData().getGameRules().getBoolean(CaerulaArborModGameRules.NATURAL_EVOLUTION)) {
				if (Math.random() < 0.008 && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 96, 96, 96), e -> true).isEmpty()) {
					pnt = Mth.nextDouble(RandomSource.create(), 0, 0.05);
					CaerulaArborModVariables.MapVariables.get(world).evo_point_migration = CaerulaArborModVariables.MapVariables.get(world).evo_point_migration + pnt;
					CaerulaArborModVariables.MapVariables.get(world).syncData(world);
					UpgradeMigraProcedure.execute(world);
					UpgradeSilenceProcedure.execute(world, entity, pnt);
				}
			}
		}
	}
}
