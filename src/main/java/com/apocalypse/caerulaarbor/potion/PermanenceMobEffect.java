
package com.apocalypse.caerulaarbor.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;

public class PermanenceMobEffect extends MobEffect {
	public PermanenceMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -10092442);
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {

	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
