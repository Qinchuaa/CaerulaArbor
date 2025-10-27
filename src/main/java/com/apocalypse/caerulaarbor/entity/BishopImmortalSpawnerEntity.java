package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

public class BishopImmortalSpawnerEntity extends Entity {
    private boolean hasSpawned = false;

    public BishopImmortalSpawnerEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.BISHOP_IMMORTAL.get(), world);
    }

    public BishopImmortalSpawnerEntity(EntityType<BishopImmortalSpawnerEntity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.hasSpawned) {
            this.discard();
            return;
        }
        this.hasSpawned = true;

        ServerLevel server = (ServerLevel) this.level();
        RandomSource rand = server.random;
        double dx = Mth.nextDouble(rand, -0.5, 0.5);
        double dz = Mth.nextDouble(rand, -0.5, 0.5);
        BlockPos basePos = this.blockPosition();

        Entity eBishop = ModEntities.TIDELINKED_BISHOP.get().spawn(server, basePos.offset(1, 0, 0), MobSpawnType.MOB_SUMMONED);
        if (eBishop == null) {
            TidelinkedBishopEntity bishop = new TidelinkedBishopEntity(ModEntities.TIDELINKED_BISHOP.get(), server);
            bishop.setPos(this.getX() + Math.max(0.25, dx), this.getY(), this.getZ() + Math.max(0.25, dz));
            bishop.setYRot(server.random.nextFloat() * 360F);
            server.addFreshEntity(bishop);
            eBishop = bishop;
        }

        Entity eImmortal = ModEntities.TIDELINKED_IMMORTAL.get().spawn(server, basePos.offset(-1, 0, 0), MobSpawnType.MOB_SUMMONED);
        if (eImmortal == null) {
            TidelinkedImmortalEntity immortal = new TidelinkedImmortalEntity(ModEntities.TIDELINKED_IMMORTAL.get(), server);
            immortal.setPos(this.getX() - Math.max(0.25, dx), this.getY(), this.getZ() - Math.max(0.25, dz));
            immortal.setYRot(server.random.nextFloat() * 360F);
            server.addFreshEntity(immortal);
            eImmortal = immortal;
        }

        if (eBishop instanceof TidelinkedBishopEntity bishop && eImmortal instanceof TidelinkedImmortalEntity immortal) {
            bishop.linkWith(immortal);
        }

        this.discard();
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(@NotNull net.minecraft.nbt.CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(@NotNull net.minecraft.nbt.CompoundTag tag) {}
}