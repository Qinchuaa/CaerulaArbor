package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerAttackFuncProcedure {
    @SubscribeEvent
    public static void onPlayerCriticalHit(CriticalHitEvent event) {
        execute(event, event.getEntity(), event.isVanillaCritical());
    }

    public static void execute(Entity sourceentity, boolean isvanillacritical) {
        execute(null, sourceentity, isvanillacritical);
    }

    private static void execute(@Nullable Event event, Entity sourceentity, boolean isvanillacritical) {
        if (sourceentity == null)
            return;
        ItemStack item_temp;
        if (isvanillacritical) {
            var cap = sourceentity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables());
            if (cap.disoclusion == 1) {
                if (Math.random() < 0.15) {
                    item_temp = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
                    if (sourceentity instanceof LivingEntity _entity) {
                        ItemStack _setstack = (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
                        _setstack.setCount((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
                        _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                        if (_entity instanceof Player _player)
                            _player.getInventory().setChanged();
                    }
                    if (sourceentity instanceof LivingEntity _entity) {
                        ItemStack _setstack = item_temp.copy();
                        _setstack.setCount(item_temp.getCount());
                        _entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
                        if (_entity instanceof Player _player)
                            _player.getInventory().setChanged();
                    }
                }
            }
            if (Relic.HAND_BARREN.gained(cap)
                    && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("minecraft:axes")))) {
                if (sourceentity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(ModMobEffects.BUTCHERS_POWER.get())) {
                    if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.BUTCHERS_POWER.get()) ? _livEnt.getEffect(ModMobEffects.BUTCHERS_POWER.get()).getAmplifier() : 0) < 7) {
                        if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.BUTCHERS_POWER.get(), 160,
                                    (sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModMobEffects.BUTCHERS_POWER.get()) ? _livEnt.getEffect(ModMobEffects.BUTCHERS_POWER.get()).getAmplifier() : 0)
                                            + 1,
                                    false, false));
                    } else {
                        if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModMobEffects.BUTCHERS_POWER.get(), 160, 7, false, false));
                    }
                } else {
                    if (sourceentity instanceof LivingEntity _entity && !_entity.level().isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModMobEffects.BUTCHERS_POWER.get(), 160, 0, false, false));
                }
            }
        }
    }
}
