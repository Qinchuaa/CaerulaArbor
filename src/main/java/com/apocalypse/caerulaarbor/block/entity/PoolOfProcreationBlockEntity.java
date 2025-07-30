package com.apocalypse.caerulaarbor.block.entity;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.apocalypse.caerulaarbor.init.ModBlockEntityTypes;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

public class PoolOfProcreationBlockEntity extends BlockEntity {
    private int delay;
    private boolean active;

    public PoolOfProcreationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.POOL_OF_PROCREATION.get(), pPos, pBlockState);
        delay = getRandomDelay(pBlockState.is(ModBlocks.NOURISHED_POOL_OF_PROCREATION.get()));
        active = true;
    }

    /**
     * @param isRed 是否是富营养育生池
     * @return 下一次生成前的随机间隔刻数，对于普通应该是1秒至400秒间，对于富营养应该是1秒至600秒间，符合瑞利分布（？）
     */
    private static int getRandomDelay(boolean isRed) {
        int factor = isRed ? 1200 : 800;
        double result = factor * Math.sqrt(-2 * Math.log(1 - Math.random()));
        return Mth.clamp((int) result, 20, factor * 10);
    }

    public void activate() {
        active = true;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, PoolOfProcreationBlockEntity pBlockEntity) {
        if (!pLevel.isClientSide()) pBlockEntity.serverTick((ServerLevel) pLevel, pPos, pState);
    }

    public void serverTick(ServerLevel level, BlockPos blockPos, BlockState blockState) {
        final boolean isRed = blockState.is(ModBlocks.NOURISHED_POOL_OF_PROCREATION.get());
        if (!isRed && !active) return;
        if (delay > 0) {
            --delay;
            setChanged();
            return;
        }
        if (!spawnMob(level, blockPos.above(), isRed)) {
            delay = 20;
            setChanged();
            return;
        }
        delay = getRandomDelay(isRed);
        if (!isRed) active = false;
        setChanged();
    }

    private static boolean spawnMob(ServerLevel level, BlockPos posToSpawn, boolean isRed) {
        boolean spawnElite = level.random.nextFloat() < getEliteChance(level, isRed);
        TagKey<EntityType<?>> entityTagToSpawn =
                spawnElite ? ModTags.EntityTypes.SEA_BORN : ModTags.EntityTypes.SEABORN_ELITE;
        Optional<EntityType<?>> optionalEntityType = randomEntityTypeInTag(level, entityTagToSpawn);
        if (optionalEntityType.isEmpty()) return false;
        EntityType<?> entityType = optionalEntityType.get();
        if (!level.noCollision(entityType.getAABB(
                posToSpawn.getX(), posToSpawn.getY(), posToSpawn.getZ()))) return false;
        return entityType.spawn(level, posToSpawn, MobSpawnType.SPAWNER) != null;
    }

    public static float getEliteChance(Level level, boolean isRed) {
        int breed = MapVariables.get(level).strategyBreed;
        if (breed >= 4) return isRed ? 0.7F : 0.1F;
        if (breed >= 2) return isRed ? 0.65F : 0.08F;
        return isRed ? 0.5F : 0.05F;
    }

    public static Optional<EntityType<?>> randomEntityTypeInTag(Level level, TagKey<EntityType<?>> tag) {
        Registry<EntityType<?>> registry = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE);
        Optional<HolderSet.Named<EntityType<?>>> optional = registry.getTag(tag);
        if (optional.isEmpty()) return Optional.empty();
        List<Holder<EntityType<?>>> entitiesInTag = optional.get().stream().toList();
        if (entitiesInTag.isEmpty()) return Optional.empty();
        RandomSource random = level.getRandom();
        EntityType<?> selected = entitiesInTag.get(random.nextInt(entitiesInTag.size())).value();
        return Optional.of(selected);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("delay", delay);
        pTag.putBoolean("active", active);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void load(CompoundTag pTag) {
        super.load(pTag);
        delay = pTag.getInt("delay");
        active = pTag.getBoolean("active");
    }
}
