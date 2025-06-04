
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FishShootEntity extends AbstractArrow implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.IRON_NUGGET);

    public FishShootEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ModEntities.FISH_SHOOT.get(), world);
    }

    public FishShootEntity(EntityType<? extends FishShootEntity> type, Level world) {
        super(type, world);
    }

    public FishShootEntity(EntityType<? extends FishShootEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public FishShootEntity(EntityType<? extends FishShootEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return PROJECTILE_ITEM;
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return PROJECTILE_ITEM;
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void tick() {
        super.tick();

        this.level().addParticle(ParticleTypes.DRIPPING_WATER, this.getX(), this.getY(), this.getZ(), -0.05 * this.getDeltaMovement().x(), -0.05 * this.getDeltaMovement().y(), -0.05 * this.getDeltaMovement().z());

        if (this.inGround)
            this.discard();
    }

    public static FishShootEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 1f, 2, 0);
    }

    public static FishShootEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
        return shoot(world, entity, source, pullingPower, 2, 0);
    }

    public static FishShootEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        FishShootEntity entityarrow = new FishShootEntity(ModEntities.FISH_SHOOT.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.puffer_fish.flop")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }

    public static FishShootEntity shoot(LivingEntity entity, LivingEntity target) {
        FishShootEntity arrow = new FishShootEntity(ModEntities.FISH_SHOOT.get(), entity, entity.level());
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        arrow.shoot(dx, dy - arrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
        arrow.setSilent(true);
        arrow.setBaseDamage(2);
        arrow.setKnockback(0);
        arrow.setCritArrow(false);
        entity.level().addFreshEntity(arrow);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.puffer_fish.flop")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return arrow;
    }
}
