
package com.apocalypse.caerulaarbor.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class InvulnerableMobEffect extends MobEffect {
	public InvulnerableMobEffect() {
		super(MobEffectCategory.NEUTRAL, -10092442);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "3b009f60-e661-3e22-bb74-48e815ae0e8b", -1, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
