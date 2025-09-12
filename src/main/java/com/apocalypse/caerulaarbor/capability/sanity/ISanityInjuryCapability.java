package com.apocalypse.caerulaarbor.capability.sanity;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ISanityInjuryCapability extends INBTSerializable<CompoundTag> {
    boolean hurt(double amount);

    void heal(double amount);

    void tick();
}

//抗性 SANITY_INJURY_RESISTANCE
//损伤固定值 SANITY_INJURY_DAMAGE
//损伤加成系数 SANITY_INJURY_DAMAGE_RATE
//损伤对于伤害值的比例 SANITY_RATE (曾用名)
