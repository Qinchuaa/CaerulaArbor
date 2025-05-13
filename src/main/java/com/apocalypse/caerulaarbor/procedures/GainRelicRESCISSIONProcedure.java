package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.apocalypse.caerulaarbor.init.CaerulaArborModMobEffects;

import java.util.List;
import java.util.Comparator;

public class GainRelicRESCISSIONProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		Entity owner = null;
		if (!itemstack.getOrCreateTag().getBoolean("used")) {
			{
				boolean _setval = true;
				entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.relic_util_RESCISSION = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(2);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.levelup")), SoundSource.NEUTRAL, 2, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.levelup")), SoundSource.NEUTRAL, 2, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ASH, x, y, z, 72, 1, 1, 1, 1);
			itemstack.getOrCreateTag().putBoolean("used", true);
		} else {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator == entity) {
						continue;
					}
					owner = entityiterator;
					if (entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false) {
						owner = entityiterator instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null;
					}
					if (owner == entity) {
						if (entityiterator instanceof LivingEntity _livEnt11 && _livEnt11.hasEffect(CaerulaArborModMobEffects.UNTAME_CONFIRM.get())) {
							if (entityiterator instanceof TamableAnimal _ent) {
								_ent.setTame(false);
							}
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((entityiterator.getDisplayName().getString() + "" + Component.translatable("item.caerula_arbor.language_key.description_2").getString())), false);
							if (entityiterator instanceof LivingEntity _entity)
								_entity.removeEffect(CaerulaArborModMobEffects.UNTAME_CONFIRM.get());
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.ASH, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 72, 1, 1, 1, 0.5);
							itemstack.shrink(1);
							if (entityiterator instanceof Wolf) {
								CaerulaArborMod.queueServerWork(Mth.nextInt(RandomSource.create(), 40, 80), () -> {
									if (entity instanceof Player _player && !_player.level().isClientSide())
										_player.displayClientMessage(Component.literal(("\u00A7o" + Component.translatable("item.caerula_arbor.language_key.description_3").getString())), false);
								});
							}
						} else {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("item.caerula_arbor.language_key.description_0").getString() + entityiterator.getDisplayName().getString()
										+ Component.translatable("item.caerula_arbor.language_key.description_1").getString())), false);
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.UNTAME_CONFIRM.get(), 300, 0, false, false));
						}
						break;
					}
				}
			}
		}
	}
}
