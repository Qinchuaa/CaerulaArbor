package com.apocalypse.caerulaarbor.block.entity;

import com.apocalypse.caerulaarbor.block.AnchorMediumBlock;
import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.init.ModBlockEntityTypes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class AnchorBlockEntity extends BlockEntity {
    public AnchorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.ANCHOR.get(), pPos, pBlockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && level instanceof ServerLevel serverLevel) ModCapabilities.getAnchorRecord(serverLevel).addAnchor(worldPosition);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (level != null && level instanceof ServerLevel serverLevel) ModCapabilities.getAnchorRecord(serverLevel).removeAnchor(worldPosition);
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        if (level != null && level instanceof ServerLevel serverLevel) ModCapabilities.getAnchorRecord(serverLevel).addAnchor(worldPosition);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, AnchorBlockEntity pBlockEntity) {
        pBlockEntity.serverTick(pLevel, pPos, pState);
    }

    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (pLevel.getGameTime() % 80 != 0) return;
        if (!pState.getValue(AnchorMediumBlock.ACTIVATED)) return;
        for (LivingEntity entity : pLevel.getEntitiesOfClass(LivingEntity.class, (new AABB(pPos)).inflate(24, 8, 24))) {
            entity.addEffect(new MobEffectInstance(ModMobEffects.POWER_OF_ANCHOR.get(), 200, 0, true, true));
        }
    }
}
