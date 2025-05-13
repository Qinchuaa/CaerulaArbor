package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.init.CaerulaArborModMobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerAttackFuncProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity(), event.isVanillaCritical());
	}

	public static void execute(Entity sourceentity, boolean isvanillacritical) {
		execute(null, sourceentity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, Entity sourceentity, boolean isvanillacritical) {
		if (sourceentity == null)
			return;
		ItemStack item_temp = ItemStack.EMPTY;
		if (isvanillacritical) {
			if ((sourceentity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).disoclusion == 1) {
				if (Math.random() < 0.15) {
					item_temp = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
					if (sourceentity instanceof LivingEntity _entity) {
						ItemStack _setstack = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
						_setstack.setCount((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					if (sourceentity instanceof LivingEntity _entity) {
						ItemStack _setstack = item_temp.copy();
						_setstack.setCount(item_temp.getCount());
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			}
			if ((sourceentity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_BARREN
					&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:axes")))) {
				if (sourceentity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(CaerulaArborModMobEffects.BUTCHERS_POWER.get())) {
					if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CaerulaArborModMobEffects.BUTCHERS_POWER.get()) ? _livEnt.getEffect(CaerulaArborModMobEffects.BUTCHERS_POWER.get()).getAmplifier() : 0) < 7) {
						if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.BUTCHERS_POWER.get(), 160,
									(int) ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(CaerulaArborModMobEffects.BUTCHERS_POWER.get()) ? _livEnt.getEffect(CaerulaArborModMobEffects.BUTCHERS_POWER.get()).getAmplifier() : 0)
											+ 1),
									false, false));
					} else {
						if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.BUTCHERS_POWER.get(), 160, 7, false, false));
					}
				} else {
					if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(CaerulaArborModMobEffects.BUTCHERS_POWER.get(), 160, 0, false, false));
				}
			}
		}
	}
}
