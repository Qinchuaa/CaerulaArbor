package com.apocalypse.caerulaarbor.event;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.init.ModDamageTypes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEventHandler {

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        var living = event.getEntity();

        living.getCapability(ModCapabilities.SANITY_INJURY).ifPresent(
                cap -> {
                    if (cap.getValue() < 0) {
                        if (living.hasEffect(ModMobEffects.SANITY_IMMUNE.get())) return;

                        if (living.level().isClientSide) {
                            living.level().playLocalSound(living.getX(), living.getY(), living.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE,
                                    SoundSource.NEUTRAL, 2.2f, 1, false);
                        } else {
                            if (living instanceof Player) {
                                living.addEffect(new MobEffectInstance(ModMobEffects.SANITY_IMMUNE.get(), 200, 0, false, false));
                                living.addEffect(new MobEffectInstance(ModMobEffects.DIZZY.get(), 200, 0, false, false));
                                living.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0, false, true));
                                living.hurt(ModDamageTypes.causeNervousImpairmentDamage(living.level().registryAccess(), null), 12);
                            } else {
                                living.addEffect(new MobEffectInstance(ModMobEffects.SANITY_IMMUNE.get(), 200, 0, false, false));
                                living.addEffect(new MobEffectInstance(ModMobEffects.DIZZY.get(), 60, 0, false, false));
                                living.hurt(ModDamageTypes.causeNervousImpairmentDamage(living.level().registryAccess(), null),
                                        Math.min(72, living.getMaxHealth() * 0.8f));
                            }

                            living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE,
                                    SoundSource.NEUTRAL, 2.2f, 1);
                        }
                    }
                }
        );
    }
}
