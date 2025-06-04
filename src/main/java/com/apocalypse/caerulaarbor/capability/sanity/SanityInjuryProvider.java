package com.apocalypse.caerulaarbor.capability.sanity;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SanityInjuryProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    private ISanityInjuryCapability capability;

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapabilities.SANITY_INJURY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @NotNull
    private ISanityInjuryCapability getOrCreateCapability() {
        if (capability == null) {
            this.capability = new SanityInjuryCapability();
        }
        return this.capability;
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getOrCreateCapability().deserializeNBT(nbt);
    }
}
