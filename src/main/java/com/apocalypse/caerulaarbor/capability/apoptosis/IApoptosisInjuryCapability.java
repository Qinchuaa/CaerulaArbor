package com.apocalypse.caerulaarbor.capability.apoptosis;
//凋亡损伤 后期实现
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IApoptosisInjuryCapability extends INBTSerializable<CompoundTag> {
    boolean hurt(double amount);

    void heal(double amount);

    void tick();
}