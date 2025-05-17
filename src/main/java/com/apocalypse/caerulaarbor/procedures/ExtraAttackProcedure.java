package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.CaerulaArborModEnchantments;
import com.apocalypse.caerulaarbor.item.LegendarySpearItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;

public class ExtraAttackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		{
			ItemStack _ist = itemstack;
			if (_ist.hurt(1, RandomSource.create(), null)) {
				_ist.shrink(1);
				_ist.setDamageValue(0);
			}
		}
		if (!(sourceentity instanceof Player _plrCldCheck3 && _plrCldCheck3.getCooldowns().isOnCooldown(itemstack.getItem()))
				&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()) {
			if (sourceentity.isShiftKeyDown()) {
				if (itemstack.getItem() instanceof LegendarySpearItem)
					itemstack.getOrCreateTag().putString("geckoAnim", "animation.lengendspear.swing2");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 25);
				CaerulaArborMod.queueServerWork(10, () -> {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, (float) 3.5, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.sweep")), SoundSource.NEUTRAL, (float) 3.5, 1, false);
						}
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator.isAlive() && !(entityiterator == sourceentity)) {
								if ((sourceentity != null ? entityiterator.distanceTo(sourceentity) : -1) <= 3) {
									entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.TRIDENT), sourceentity),
											(float) ((sourceentity instanceof LivingEntity _livingEntity16 && _livingEntity16.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE)
													? _livingEntity16.getAttribute(Attributes.ATTACK_DAMAGE).getValue()
													: 0) * (1 + 0.2 * itemstack.getEnchantmentLevel(CaerulaArborModEnchantments.SYNESTHESIA.get()))));
								}
							}
						}
					}
				});
			} else if (sourceentity.getDeltaMovement().y() < -0.1) {
				if (itemstack.getItem() instanceof LegendarySpearItem)
					itemstack.getOrCreateTag().putString("geckoAnim", "animation.lengendspear.srike");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 25);
				CaerulaArborMod.queueServerWork(10, () -> {
					if (entity.isAlive()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.hit_ground")), SoundSource.NEUTRAL, (float) 3.5, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.hit_ground")), SoundSource.NEUTRAL, (float) 3.5, 1, false);
							}
						}
						if ((sourceentity != null ? entity.distanceTo(sourceentity) : -1) <= 4) {
							entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.TRIDENT), sourceentity),
									(float) ((sourceentity instanceof LivingEntity _livingEntity31 && _livingEntity31.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity31.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)
											* (1 + 0.2 * itemstack.getEnchantmentLevel(CaerulaArborModEnchantments.SYNESTHESIA.get()))));
						}
					}
				});
			} else if (sourceentity.getDeltaMovement().y() > 0.1) {
				if (itemstack.getItem() instanceof LegendarySpearItem)
					itemstack.getOrCreateTag().putString("geckoAnim", "animation.lengendspear.swing");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 25);
				CaerulaArborMod.queueServerWork(10, () -> {
					if (entity.isAlive()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, (float) 3.5, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.throw")), SoundSource.NEUTRAL, (float) 3.5, 1, false);
							}
						}
						if ((sourceentity != null ? entity.distanceTo(sourceentity) : -1) <= 4) {
							entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.TRIDENT), sourceentity),
									(float) ((sourceentity instanceof LivingEntity _livingEntity45 && _livingEntity45.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity45.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)
											* (1 + 0.2 * itemstack.getEnchantmentLevel(CaerulaArborModEnchantments.SYNESTHESIA.get()))));
							entity.push(0, 0.5, 0);
						}
					}
				});
			} else {
				if (itemstack.getItem() instanceof LegendarySpearItem)
					itemstack.getOrCreateTag().putString("geckoAnim", "animation.lengendspear.stab");
				if (sourceentity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 25);
				CaerulaArborMod.queueServerWork(10, () -> {
					if (entity.isAlive()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.hit")), SoundSource.NEUTRAL, (float) 3.5, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.trident.hit")), SoundSource.NEUTRAL, (float) 3.5, 1, false);
							}
						}
						if ((sourceentity != null ? entity.distanceTo(sourceentity) : -1) <= 4) {
							entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.TRIDENT), sourceentity),
									(float) ((sourceentity instanceof LivingEntity _livingEntity59 && _livingEntity59.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity59.getAttribute(Attributes.ATTACK_DAMAGE).getValue() : 0)
											* (1 + 0.2 * itemstack.getEnchantmentLevel(CaerulaArborModEnchantments.SYNESTHESIA.get()))));
							entity.push((sourceentity.getLookAngle().x), 0, (sourceentity.getLookAngle().z));
						}
					}
				});
			}
		}
	}
}
