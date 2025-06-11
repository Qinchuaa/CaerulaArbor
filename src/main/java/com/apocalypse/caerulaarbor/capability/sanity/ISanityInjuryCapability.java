package com.apocalypse.caerulaarbor.capability.sanity;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ISanityInjuryCapability extends INBTSerializable<CompoundTag> {

    double getValue();

    void setValue(double value);

    double injure(double damage);

    /**
     * 在受到神经损伤且未爆发的时候，恢复神经损伤
     * @param health 恢复的神经损伤值
     * @return 真正恢复的神经损伤值
     */
    double heal(double health);

    /**
     * 在损伤爆发的时候，自然恢复的方法
     * @param amount 恢复的神经损伤值
     */
    void regenerate(double amount);

    boolean isImmune();

    void setImmune(boolean immune);
}
