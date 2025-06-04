package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CaerulaArborMod.MODID);

    public static final RegistryObject<Enchantment> OCEANOSPR_KILLER = REGISTRY.register("oceanospr_killer", OceanosprKillerEnchantment::new);
    public static final RegistryObject<Enchantment> SANITY_REAPER = REGISTRY.register("sanity_reaper", SanityReaperEnchantment::new);
    public static final RegistryObject<Enchantment> SANITY_DEFEND = REGISTRY.register("sanity_defend", SanityDefendEnchantment::new);
    public static final RegistryObject<Enchantment> REFLECTION = REGISTRY.register("reflection", ReflectionEnchantment::new);
    public static final RegistryObject<Enchantment> SYNESTHESIA = REGISTRY.register("synesthesia", SynesthesiaEnchantment::new);
    public static final RegistryObject<Enchantment> METABOLISM = REGISTRY.register("metabolism", MetabolismEnchantment::new);
}
