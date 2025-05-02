
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

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

import net.mcreator.caerulaarbor.CaerulaArborMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CaerulaArborModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CaerulaArborMod.MODID);
	public static final RegistryObject<Attribute> SANITY = REGISTRY.register("sanity", () -> new RangedAttribute("attribute.caerula_arbor.sanity", 1000, -1, 1000).setSyncable(true));
	public static final RegistryObject<Attribute> SANITY_MODIFIER = REGISTRY.register("sanity_modifier", () -> new RangedAttribute("attribute.caerula_arbor.sanity_modifier", 1, 0, 999).setSyncable(true));
	public static final RegistryObject<Attribute> SANITY_RATE = REGISTRY.register("sanity_rate", () -> new RangedAttribute("attribute.caerula_arbor.sanity_rate", 0, 0, 999).setSyncable(true));
	public static final RegistryObject<Attribute> EVOLVED = REGISTRY.register("evolved", () -> new RangedAttribute("attribute.caerula_arbor.evolved", 0, 0, 1).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.getTypes().forEach(entity -> event.add(entity, SANITY.get()));
		event.getTypes().forEach(entity -> event.add(entity, SANITY_MODIFIER.get()));
		event.getTypes().forEach(entity -> event.add(entity, SANITY_RATE.get()));
		event.getTypes().forEach(entity -> event.add(entity, EVOLVED.get()));
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
		}
	}
}
