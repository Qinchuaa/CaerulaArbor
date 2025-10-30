package com.apocalypse.caerulaarbor.capability.sanity;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.api.event.SanityEvent;
import com.apocalypse.caerulaarbor.config.server.SanityConfig;
import com.apocalypse.caerulaarbor.init.ModAttributes;
import com.apocalypse.caerulaarbor.init.ModDamageTypes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;

import java.util.Optional;

public class SanityInjuryCapability implements ISanityInjuryCapability {
    public static final ResourceLocation ID = CaerulaArborMod.loc("sanity_injury");

    private final LivingEntity owner;
    private double value;
    private boolean recovering;

    public SanityInjuryCapability(LivingEntity owner) {
        this(owner, 1000);
    }

    public SanityInjuryCapability(LivingEntity owner, double value) {
        this.owner = owner;
        this.value = Mth.clamp(value, 0, 1000);
        this.recovering = false;
    }

    @Override
    public boolean hurt(double damage) {
        if (recovering) return false;
        var sanityResistanceAttr = Optional.ofNullable(owner.getAttribute(ModAttributes.SANITY_INJURY_RESISTANCE.get()));
        double sanityResistance = sanityResistanceAttr.map(AttributeInstance::getValue).orElse(0D);
        damage *= 1 - sanityResistance / 100;
        if (damage <= 0) return false;
        this.value -= damage;
        if (this.value <= 0) {
            sanityBreak();
            this.value = 0;
            this.recovering = true;
        }
        return true;
    }

    private void sanityBreak() {
        SanityEvent.Break event = new SanityEvent.Break(owner);
        if (MinecraftForge.EVENT_BUS.post(event)) return;
        if (owner.level().isClientSide) {
            owner.level().playLocalSound(owner.getX(), owner.getY(), owner.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE,
                    owner.getSoundSource(), 2.2f, 1, false);
        } else {
            if (owner instanceof Player player) {
                boolean creativeAffected = SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY.get();
                if (!player.isCreative() || creativeAffected) {
                    // 配置：选择麻痹或眩晕
                    if (SanityConfig.PLAYER_BREAK_USES_PALSY.get()) {
                        player.addEffect(new MobEffectInstance(ModMobEffects.PALSY.get(), -1, 2, false, false, true));
                    } else {
                        int dizzyDuration = 200;
                        if (player.hasEffect(ModMobEffects.ESSENCE_RESISTANCE.get())) {
                            dizzyDuration = Math.max(1, dizzyDuration / 2);
                        }
                        player.addEffect(new MobEffectInstance(ModMobEffects.DIZZY.get(), dizzyDuration, 0, false, false));
                        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0, false, true));
                    }
                    player.hurt(ModDamageTypes.causeNervousImpairmentDamage(player.level().registryAccess(), null), 12);
                }
            } else {
                // 非玩家分支：麻痹效果（已实现，具体逻辑由事件处理器承担）
                /*
                状态效果：麻痹，麻痹震颤
                爆条时给3级无限持续时间麻痹
                生物造成伤害时：
                    如果有麻痹震颤：
                        取消伤害
                    否则如果有麻痹：
                        取消伤害
                        给半秒麻痹震颤
                        减少1级（0级时消除）
                 */
                owner.addEffect(new MobEffectInstance(ModMobEffects.PALSY.get(), -1, 2, false, false, true));
                owner.hurt(ModDamageTypes.causeNervousImpairmentDamage(owner.level().registryAccess(), null),
                        Mth.clamp(owner.getMaxHealth() * 0.8f, 12, 72));
            }
            owner.level().playSound(owner instanceof Player player ? player : null,
                    owner.getX(), owner.getY(), owner.getZ(),
                    SoundEvents.ELDER_GUARDIAN_CURSE, owner.getSoundSource(), 2.2f, 1);
        }
    }

    @Override
    public void tick() {
        if (recovering) {
            boolean fast = owner.hasEffect(ModMobEffects.ESSENCE_RESISTANCE.get());
            double step = 1000.0 / (fast ? 100.0 : 200.0);
            this.value = Math.min(1000.0, this.value + step);
            if (this.value >= 1000.0) {
                this.value = 1000.0;
                this.recovering = false;
            }
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public void heal(double value) {
        if (recovering) return;
        SanityEvent event = new SanityEvent.Heal(this.owner, value);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            this.value = Math.min(this.value + event.getAmount(), 1000);
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("SanityInjury", this.value);
        tag.putBoolean("SanityRecovering", this.recovering);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.value = nbt.getDouble("SanityInjury");
        this.recovering = nbt.getBoolean("SanityRecovering");
    }
}
