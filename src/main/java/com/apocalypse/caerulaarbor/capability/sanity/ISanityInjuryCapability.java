package com.apocalypse.caerulaarbor.capability.sanity;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ISanityInjuryCapability extends INBTSerializable<CompoundTag> {

    double getValue();

    double setValue(double value);

    double injure(double damage);

    double heal(double health);
}
