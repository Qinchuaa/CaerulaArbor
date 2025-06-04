package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModGameRules;
import com.apocalypse.caerulaarbor.init.ModParticleTypes;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerDiedFuncProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
		execute(null, world, x, y, z, damagesource, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
		if (damagesource == null || entity == null)
			return;
		double light_cost;
		double dx;
		double dz;
		if ((ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString()).equals("minecraft:player")) {
			light_cost = 0;
			if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).shield > 0) {
				{
					double _setval = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).shield - 1;
					entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
						capability.shield = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:target_damaged")), SoundSource.PLAYERS, 2, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:target_damaged")), SoundSource.PLAYERS, 2, 1, false);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (ModParticleTypes.SHIELDLOSS.get()), x, (y + 0.95), z, 72, 0.75, 0.55, 0.75, 0.2);
				if (!damagesource.is(DamageTypes.GENERIC_KILL)) {
					if (world.getLevelData().getGameRules().getBoolean(ModGameRules.TARGET_LIFE_FUNCTION)) {
						if (event != null && event.isCancelable()) {
							event.setCanceled(true);
						}
						if (entity instanceof LivingEntity _entity)
							_entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.5));
						if (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(ModAttributes.SANITY.get()))
							_livingEntity8.getAttribute(ModAttributes.SANITY.get()).setBaseValue(1000);
					}
				}
			} else {
				if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).lives > 0) {
					{
						double _setval = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).lives - 1;
						entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
							capability.lives = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (!damagesource.is(DamageTypes.GENERIC_KILL)) {
						if (world.getLevelData().getGameRules().getBoolean(ModGameRules.TARGET_LIFE_FUNCTION)) {
							if (event != null && event.isCancelable()) {
								event.setCanceled(true);
							}
							if (entity instanceof LivingEntity _entity)
								_entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.5));
							if (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttributes().hasAttribute(ModAttributes.SANITY.get()))
								_livingEntity13.getAttribute(ModAttributes.SANITY.get()).setBaseValue(1000);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:target_damaged")), SoundSource.PLAYERS, 2, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("caerula_arbor:target_damaged")), SoundSource.PLAYERS, 2, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (ModParticleTypes.LIFELOSS.get()), x, (y + 0.95), z, 72, 0.75, 0.55, 0.75, 0.2);
					if (damagesource.is(DamageTypes.LIGHTNING_BOLT) || damagesource.is(DamageTypes.FELL_OUT_OF_WORLD) || damagesource.is(DamageTypes.OUTSIDE_BORDER) || damagesource.is(DamageTypes.CRAMMING)) {
						light_cost = 15;
					} else if ((damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL) || damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.FIREWORKS)) == (damagesource.is(DamageTypes.SONIC_BOOM)
							|| damagesource.is(DamageTypes.DRAGON_BREATH) || damagesource.is(DamageTypes.LAVA) || damagesource.is(DamageTypes.FALLING_ANVIL)) || damagesource.is(DamageTypes.FALLING_STALACTITE)
							|| damagesource.is(DamageTypes.STALAGMITE)) {
						light_cost = 10;
					} else {
						light_cost = 5;
					}
				} else {
					light_cost = 50;
				}
			}
			{
				double _setval = (entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).light - light_cost;
				entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
					capability.light = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables())).light < 0) {
				{
					double _setval = 0;
					entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
						capability.light = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
		dx = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			dz = -1;
			for (int index1 = 0; index1 < 3; index1++) {
				if ((world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock() == ModBlocks.SEA_TRAIL_INIT.get()
						|| (world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock() == ModBlocks.SEA_TRAIL_GROWING.get()) {
					{
						int _value = ((world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip36
								? (world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getValue(_getip36)
								: -1) + 4;
						BlockPos _pos = BlockPos.containing(x + dx, y, z + dz);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				}
				if ((world.getBlockState(BlockPos.containing(x + dx, y - 1, z + dz))).getBlock() == ModBlocks.SEA_TRAIL_INIT.get()
						|| (world.getBlockState(BlockPos.containing(x + dx, y - 1, z + dz))).getBlock() == ModBlocks.SEA_TRAIL_GROWING.get()) {
					{
						int _value = ((world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _getip43
								? (world.getBlockState(BlockPos.containing(x + dx, y, z + dz))).getValue(_getip43)
								: -1) + 4;
						BlockPos _pos = BlockPos.containing(x + dx, y - 1, z + dz);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("grow_age") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				}
				dz = dz + 1;
			}
			dx = dx + 1;
		}
	}
}
