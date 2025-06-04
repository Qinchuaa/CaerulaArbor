
package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.init.ModParticleTypes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class ReefCrackerMobEffect extends MobEffect {
    public ReefCrackerMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -10066177);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "4bf1ea4a-c7f0-37b3-8940-7ab10737ff32", 0.15, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.level() instanceof ServerLevel server)) return;

        server.sendParticles(ModParticleTypes.CRACKER_BUFF_0.get(),
                entity.getX(), entity.getY() + entity.getBbHeight() * 0.5, entity.getZ(),
                amplifier + 1, 0.8, 1.5, 0.8, 0.3
        );

        if (amplifier > 6) {
            server.sendParticles(ModParticleTypes.CRACKER_BUFF_1.get(),
                    entity.getX(), entity.getY(), entity.getZ(),
                    3, 1, 0.5, 1, 0.3
            );
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (amplifier >= 2 && !entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(ModMobEffects.REEF_CRACKER.get(), 60, amplifier - 2, false, false));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
