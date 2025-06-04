package com.apocalypse.caerulaarbor.init;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CaerulaArborMod.MODID);

    public static final RegistryObject<SimpleParticleType> LIFELOSS = REGISTRY.register("lifeloss", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SHIELDLOSS = REGISTRY.register("shieldloss", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BLOODOOZE = REGISTRY.register("bloodooze", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> KING_SLAY = REGISTRY.register("king_slay", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> KING_SLAY_RED = REGISTRY.register("king_slay_red", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ARCHFIEND_KEEP = REGISTRY.register("archfiend_keep", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ARCHFIEND_RESEV = REGISTRY.register("archfiend_resev", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> DIZZINESS = REGISTRY.register("dizziness", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> KNIFEPTC = REGISTRY.register("knifeptc", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MISS = REGISTRY.register("miss", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CRACKER_BUFF_0 = REGISTRY.register("cracker_buff_0", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CRACKER_BUFF_1 = REGISTRY.register("cracker_buff_1", () -> new SimpleParticleType(false));
}
