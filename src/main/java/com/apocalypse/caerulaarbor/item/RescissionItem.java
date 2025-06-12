
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Comparator;
import java.util.List;

public class RescissionItem extends Item {
    public RescissionItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.rescission.description_0"));
        list.add(Component.translatable("item.caerula_arbor.rescission.description_1"));
    }

    // TODO 什么玩意这是
    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        Entity owner;
        if (!itemstack.getOrCreateTag().getBoolean("Used")) {
            Relic.UTIL_RESCISSION.gainAndSync(entity);

            entity.giveExperienceLevels(2);
            if ((LevelAccessor) world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1);
                } else {
                    _level.playLocalSound(x, y, z, SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1, false);
                }
            }
            if ((LevelAccessor) world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.ASH, x, y, z, 72, 1, 1, 1, 1);
            itemstack.getOrCreateTag().putBoolean("Used", true);
        } else {
            {
                final Vec3 _center = new Vec3(x, y, z);
                for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
                    if (entityiterator == entity) {
                        continue;
                    }
                    owner = entityiterator;
                    if (entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.isTame() : false) {
                        owner = entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null;
                    }
                    if (owner == entity) {
                        if (entityiterator instanceof LivingEntity _livEnt11 && _livEnt11.hasEffect(ModMobEffects.UNTAME_CONFIRM.get())) {
                            if (entityiterator instanceof TamableAnimal _ent) {
                                _ent.setTame(false);
                            }
                            if ((Entity) entity instanceof Player _player && !_player.level().isClientSide())
                                _player.displayClientMessage(Component.literal((entityiterator.getDisplayName().getString() + "" + Component.translatable("item.caerula_arbor.language_key.description_2").getString())), false);
                            if (entityiterator instanceof LivingEntity _entity)
                                _entity.removeEffect(ModMobEffects.UNTAME_CONFIRM.get());
                            if ((LevelAccessor) world instanceof ServerLevel _level)
                                _level.sendParticles(ParticleTypes.ASH, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 72, 1, 1, 1, 0.5);
                            itemstack.shrink(1);
                            if (entityiterator instanceof Wolf) {
                                CaerulaArborMod.queueServerWork(Mth.nextInt(RandomSource.create(), 40, 80), () -> {
                                    if ((Entity) entity instanceof Player _player && !_player.level().isClientSide())
                                        _player.displayClientMessage(Component.literal(("§o" + Component.translatable("item.caerula_arbor.language_key.description_3").getString())), false);
                                });
                            }
                        } else {
                            if ((Entity) entity instanceof Player _player && !_player.level().isClientSide())
                                _player.displayClientMessage(Component.literal(("§c" + Component.translatable("item.caerula_arbor.language_key.description_0").getString() + entityiterator.getDisplayName().getString()
                                        + Component.translatable("item.caerula_arbor.language_key.description_1").getString())), false);
                            if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
                                _entity.addEffect(new MobEffectInstance(ModMobEffects.UNTAME_CONFIRM.get(), 300, 0, false, false));
                        }
                        break;
                    }
                }
            }
        }
        return ar;
    }
}
