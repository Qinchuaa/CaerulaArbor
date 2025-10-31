package com.apocalypse.caerulaarbor.capability.apoptosis;
//凋亡损伤 后期实现
import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;

public class ApoptosisInjuryCapability implements IApoptosisInjuryCapability {
    public static final ResourceLocation ID = CaerulaArborMod.loc("apoptosis_injury");

    private final LivingEntity owner;
    private double value;
    private boolean recovering;
    private boolean locked;

    public ApoptosisInjuryCapability(LivingEntity owner) {
        this(owner, 1000);
    }

    public ApoptosisInjuryCapability(LivingEntity owner, double value) {
        this.owner = owner;
        this.value = Math.max(0, Math.min(1000, value));
        this.recovering = false;
        this.locked = false;
    }

    public double getValue() {
        return value;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lockToMax() {
        this.value = 1000;
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    @Override
    public boolean hurt(double amount) {
        if (locked) return false;
        if (recovering) return false;
        if (amount <= 0) return false;
        this.value -= amount;
        if (this.value <= 0) {
            apoptosisBreak();
            this.value = 0;
            this.recovering = true;
        }
        return true;
    }

    @Override
    public void heal(double amount) {
        if (locked) return;
        if (recovering) return;
        if (amount <= 0) return;
        this.value = Math.min(this.value + amount, 1000);
    }

    @Override
    public void tick() {
        if (recovering) {
            boolean fast = owner.hasEffect(ModMobEffects.ESSENCE_RESISTANCE.get());
            double step = 1000.0 / (fast ? 100.0 : 200.0);
            this.value = Math.min(1000.0, this.value + step);
            if (this.value >= 1000.0) {
                this.value = 1000.0;
                this.recovering = false;
                ModCapabilities.getSanityInjury(owner).unlock();
            }
        }
    }

    private void apoptosisBreak() {
        ModCapabilities.getSanityInjury(owner).lockToMax();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("ApoptosisInjury", this.value);
        tag.putBoolean("ApoptosisRecovering", this.recovering);
        tag.putBoolean("ApoptosisLocked", this.locked);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.value = nbt.getDouble("ApoptosisInjury");
        this.recovering = nbt.getBoolean("ApoptosisRecovering");
        this.locked = nbt.getBoolean("ApoptosisLocked");
    }
}