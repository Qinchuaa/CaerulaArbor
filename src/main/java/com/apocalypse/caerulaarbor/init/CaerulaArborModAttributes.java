
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CaerulaArborMod.MODID);
	public static final RegistryObject<Attribute> SANITY = REGISTRY.register("sanity", () -> new RangedAttribute("attribute.caerula_arbor.sanity", 1000, -1, 1000).setSyncable(true));
	public static final RegistryObject<Attribute> SANITY_MODIFIER = REGISTRY.register("sanity_modifier", () -> new RangedAttribute("attribute.caerula_arbor.sanity_modifier", 1, 0, 999).setSyncable(true));
	public static final RegistryObject<Attribute> SANITY_RATE = REGISTRY.register("sanity_rate", () -> new RangedAttribute("attribute.caerula_arbor.sanity_rate", 0, 0, 999).setSyncable(true));
	public static final RegistryObject<Attribute> EVOLVED = REGISTRY.register("evolved", () -> new RangedAttribute("attribute.caerula_arbor.evolved", 0, 0, 1).setSyncable(true));
	public static final RegistryObject<Attribute> MISS_CHANCE = REGISTRY.register("miss_chance", () -> new RangedAttribute("attribute.caerula_arbor.miss_chance", 0, 0, 100).setSyncable(true));
	public static final RegistryObject<Attribute> SUMMONABLE = REGISTRY.register("summonable", () -> new RangedAttribute("attribute.caerula_arbor.summonable", 1, 0, 1).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.getTypes().forEach(entity -> event.add(entity, SANITY.get()));
		event.getTypes().forEach(entity -> event.add(entity, SANITY_MODIFIER.get()));
		event.getTypes().forEach(entity -> event.add(entity, SANITY_RATE.get()));
		event.getTypes().forEach(entity -> event.add(entity, EVOLVED.get()));
		event.getTypes().forEach(entity -> event.add(entity, MISS_CHANCE.get()));
		event.getTypes().forEach(entity -> event.add(entity, SUMMONABLE.get()));
	}

	@Mod.EventBusSubscriber
	public static class PlayerAttributesSync {
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			Player oldPlayer = event.getOriginal();
			Player newPlayer = event.getEntity();
			newPlayer.getAttribute(SANITY.get()).setBaseValue(oldPlayer.getAttribute(SANITY.get()).getBaseValue());
			newPlayer.getAttribute(SANITY_MODIFIER.get()).setBaseValue(oldPlayer.getAttribute(SANITY_MODIFIER.get()).getBaseValue());
			newPlayer.getAttribute(SANITY_RATE.get()).setBaseValue(oldPlayer.getAttribute(SANITY_RATE.get()).getBaseValue());
			newPlayer.getAttribute(EVOLVED.get()).setBaseValue(oldPlayer.getAttribute(EVOLVED.get()).getBaseValue());
			newPlayer.getAttribute(MISS_CHANCE.get()).setBaseValue(oldPlayer.getAttribute(MISS_CHANCE.get()).getBaseValue());
			newPlayer.getAttribute(SUMMONABLE.get()).setBaseValue(oldPlayer.getAttribute(SUMMONABLE.get()).getBaseValue());
		}
	}
}
