
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caerulaarbor.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.caerulaarbor.enchantment.SynesthesiaEnchantment;
import net.mcreator.caerulaarbor.enchantment.SanityReaperEnchantment;
import net.mcreator.caerulaarbor.enchantment.SanityDefendEnchantment;
import net.mcreator.caerulaarbor.enchantment.ReflectionEnchantment;
import net.mcreator.caerulaarbor.enchantment.OceanosprKillerEnchantment;
import net.mcreator.caerulaarbor.enchantment.MetabolismEnchantment;
import net.mcreator.caerulaarbor.CaerulaArborMod;

public class CaerulaArborModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CaerulaArborMod.MODID);
	public static final RegistryObject<Enchantment> OCEANOSPR_KILLER = REGISTRY.register("oceanospr_killer", () -> new OceanosprKillerEnchantment());
	public static final RegistryObject<Enchantment> SANITY_REAPER = REGISTRY.register("sanity_reaper", () -> new SanityReaperEnchantment());
	public static final RegistryObject<Enchantment> SANITY_DEFEND = REGISTRY.register("sanity_defend", () -> new SanityDefendEnchantment());
	public static final RegistryObject<Enchantment> REFLECTION = REGISTRY.register("reflection", () -> new ReflectionEnchantment());
	public static final RegistryObject<Enchantment> SYNESTHESIA = REGISTRY.register("synesthesia", () -> new SynesthesiaEnchantment());
	public static final RegistryObject<Enchantment> METABOLISM = REGISTRY.register("metabolism", () -> new MetabolismEnchantment());
}
