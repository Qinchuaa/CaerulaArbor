package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;

public class DamageByInfestedProcedure {
	public static void execute(LevelAccessor world, Entity entity, double amplifier) {
		if (entity == null)
			return;
		double dam = 0;
		if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_oceanization < 3) {
			dam = (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * Mth.nextDouble(RandomSource.create(), 0.1, 0.25) * (amplifier + 1);
			if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(CaerulaArborModMobEffects.POWER_OF_ANCHOR.get())) {
				dam = dam * 0.1;
			}
			entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("caerula_arbor:oceanize_damage")))), (float) dam);
			if (!(entity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(CaerulaArborModMobEffects.POWER_OF_ANCHOR.get()))) {
				if (Math.random() < 0.33) {
					dam = Mth.nextInt(RandomSource.create(), 0, 7);
					if (dam == 0) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 160, (int) amplifier));
					} else if (dam == 1) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 160, (int) amplifier));
					} else if (dam == 2) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 160, (int) amplifier));
					} else if (dam == 3) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 160, (int) amplifier));
					} else if (dam == 4) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 160, (int) amplifier));
					} else if (dam == 5) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 160, 0));
					} else if (dam == 6) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.FROZEN.get(), 160, 0));
					} else if (dam == 7) {
						if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.DIZZY.get(), 160, 0));
					}
				}
			}
		}
	}
}
