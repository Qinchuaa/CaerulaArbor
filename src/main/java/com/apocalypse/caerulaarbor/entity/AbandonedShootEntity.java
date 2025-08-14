
package com.apocalypse.caerulaarbor.entity;

import com.apocalypse.caerulaarbor.entity.base.SeaMonster;
import com.apocalypse.caerulaarbor.init.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import java.util.List;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class AbandonedShootEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Blocks.BLUE_CANDLE);

	public AbandonedShootEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(ModEntities.ABANDONED_SHOOT.get(), world);
	}

	public AbandonedShootEntity(EntityType<? extends AbandonedShootEntity> type, Level world) {
		super(type, world);
	}

	public AbandonedShootEntity(EntityType<? extends AbandonedShootEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public AbandonedShootEntity(EntityType<? extends AbandonedShootEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		rangedAttack(new Vec3(entityHitResult.getLocation().toVector3f()));
		super.onHitEntity(entityHitResult);
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		rangedAttack(new Vec3(blockHitResult.getLocation().toVector3f().add(0.5f,0.5f,0.5f)));
		super.onHitBlock(blockHitResult);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
	}

	public static AbandonedShootEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 1.5f, 3, 0);
	}

	public static AbandonedShootEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 1.5f, 3, 0);
	}

	public static AbandonedShootEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		AbandonedShootEntity entityarrow = new AbandonedShootEntity(ModEntities.ABANDONED_SHOOT.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.SHULKER_SHOOT, SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static AbandonedShootEntity shoot(LivingEntity entity, LivingEntity target) {
		AbandonedShootEntity entityarrow = new AbandonedShootEntity(ModEntities.ABANDONED_SHOOT.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1.5f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(3);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.SHULKER_SHOOT, SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}

	private void rangedAttack(Vec3 center){
		Entity owner = this.getOwner();
		Level level = this.level();
		if(owner == null)return;
		if(owner instanceof SeaMonster _seaborn){
			AABB aabb = new AABB(center.add(-4.5,-4.5,-4.5),center.add(4.5,4.5,4.5));
			List<LivingEntity> ents = level.getEntitiesOfClass(LivingEntity.class,aabb, _seaborn::isLegalTarget);
			float damage = (float) _seaborn.getAttributeValue(Attributes.ATTACK_DAMAGE);
			ents.forEach(e->{
				e.hurt(level.damageSources().indirectMagic(owner,owner),damage);
			});
		}
		this.discard();
	}
}
