package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MobInitProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof IronGolem livingEntity1) {
			if (livingEntity1.getAttributes().hasAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()))
				livingEntity1.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()).setBaseValue(0.1);
		}
		if (entity.getType().is(ModTags.EntityTypes.SEA_BORN)) {
			if (entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()))
				_livingEntity3.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()).setBaseValue(0.33);
		}
		if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:bossoffspring")))) {
			if (entity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()))
				_livingEntity5.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()).setBaseValue(0.16);
		}
		if (entity instanceof LivingEntity _livEnt6 && _livEnt6.getMobType() == MobType.UNDEAD) {
			if (entity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()))
				_livingEntity7.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()).setBaseValue(0.5);
		}
		if (entity instanceof Warden livingEntity9) {
			if (livingEntity9.getAttributes().hasAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()))
				livingEntity9.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()).setBaseValue(0.25);
		}
	}
}
