package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.init.ModEnchantments;
import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ShootarrowProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        CaerulaArborMod.queueServerWork(24, () -> {
            if (((entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(ModItems.OCEAN_ARROW.get()))) || new Object() {
                public boolean checkGamemode(Entity _ent) {
                    if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                    } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                        return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
                    }
                    return false;
                }
            }.checkGamemode(entity) || EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.METABOLISM.get(), itemstack) != 0)
                    && ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem()
                    || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == itemstack.getItem())) {
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.8F, 1);
                    } else {
                        _level.playLocalSound(x, y, z, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.8F, 1, false);
                    }
                }
                if (itemstack.hurt(1, RandomSource.create(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.METABOLISM.get(), itemstack) != 0) {
                    Level projectileLevel = entity.level();
                    if (!projectileLevel.isClientSide()) {
                        Projectile _entityToSpawn = new Object() {
                            public Projectile getArrow(Level level, Entity shooter, float damage, int knockback, byte piercing) {
                                AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                entityToSpawn.setOwner(shooter);
                                entityToSpawn.setBaseDamage(damage);
                                entityToSpawn.setKnockback(knockback);
                                entityToSpawn.setPierceLevel(piercing);
                                entityToSpawn.setCritArrow(true);
                                entityToSpawn.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                                return entityToSpawn;
                            }
                        }.getArrow(projectileLevel, entity, (float) (7 + 1.5 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0, (byte) 1);
                        _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                        _entityToSpawn.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, (float) (3 + 0.2 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0);
                        projectileLevel.addFreshEntity(_entityToSpawn);
                    }
                } else {
                    if (!(new Object() {
                        public boolean checkGamemode(Entity _ent) {
                            if (_ent instanceof ServerPlayer _serverPlayer) {
                                return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                            } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                                        && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
                            }
                            return false;
                        }
                    }.checkGamemode(entity))) {
                        if (entity instanceof Player _player) {
                            _player.getInventory().clearOrCountMatchingItems(p -> ModItems.OCEAN_ARROW.get() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
                        }
                        Level projectileLevel = entity.level();
                        if (!projectileLevel.isClientSide()) {
                            Projectile _entityToSpawn = new Object() {
                                public Projectile getArrow(Level level, Entity shooter, float damage, int knockback, byte piercing) {
                                    AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                    entityToSpawn.setOwner(shooter);
                                    entityToSpawn.setBaseDamage(damage);
                                    entityToSpawn.setKnockback(knockback);
                                    entityToSpawn.setPierceLevel(piercing);
                                    entityToSpawn.setCritArrow(true);
                                    entityToSpawn.pickup = AbstractArrow.Pickup.ALLOWED;
                                    return entityToSpawn;
                                }
                            }.getArrow(projectileLevel, entity, (float) (7 + 1.5 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0, (byte) 1);
                            _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                            _entityToSpawn.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, (float) (3 + 0.2 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0);
                            projectileLevel.addFreshEntity(_entityToSpawn);
                        }
                    } else {
                        Level projectileLevel = entity.level();
                        if (!projectileLevel.isClientSide()) {
                            Projectile _entityToSpawn = new Object() {
                                public Projectile getArrow(Level level, Entity shooter, float damage, int knockback, byte piercing) {
                                    AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
                                    entityToSpawn.setOwner(shooter);
                                    entityToSpawn.setBaseDamage(damage);
                                    entityToSpawn.setKnockback(knockback);
                                    entityToSpawn.setPierceLevel(piercing);
                                    entityToSpawn.setCritArrow(true);
                                    entityToSpawn.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                                    return entityToSpawn;
                                }
                            }.getArrow(projectileLevel, entity, (float) (7 + 1.5 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0, (byte) 1);
                            _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                            _entityToSpawn.shoot(entity.getLookAngle().x, entity.getLookAngle().y, entity.getLookAngle().z, (float) (3 + 0.2 * itemstack.getEnchantmentLevel(ModEnchantments.REFLECTION.get())), 0);
                            projectileLevel.addFreshEntity(_entityToSpawn);
                        }
                    }
                }
            }
        });
    }
}
