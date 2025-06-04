package com.apocalypse.caerulaarbor.capability.sanity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class SanityInjuryCapability implements ISanityInjuryCapability {

    private double value;

    public SanityInjuryCapability() {
        this.value = 1000;
    }

    public SanityInjuryCapability(double value) {
        this.value = Mth.clamp(value, -1, 1000);
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public double setValue(double value) {
        return Mth.clamp(value, -1, 1000);
    }

    @Override
    public double injure(double damage) {
        if (this.value < 0) return 0;
        if (damage <= 0) return 0;

        if (this.value > damage) {
            this.value -= damage;
            return damage;
        } else {
            double injured = this.value + 1;
            this.value = -1;
            return injured;
        }
    }

    @Override
    public double heal(double health) {
        if (this.value < 0 || this.value >= 1000) return 0;
        if (health <= 0) return 0;

        if (this.value < 1000 - health) {
            this.value += health;
            return health;
        } else {
            double healed = 1000 - this.value;
            this.value = 1000;
            return healed;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("SanityInjury", this.value);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.value = nbt.getDouble("SanityInjury");
    }
}
