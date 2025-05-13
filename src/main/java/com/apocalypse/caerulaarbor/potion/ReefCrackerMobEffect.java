
package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.procedures.DeductBufflvlProcedure;
import com.apocalypse.caerulaarbor.procedures.ParticleCrackerProcedure;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.ArrayList;

public class ReefCrackerMobEffect extends MobEffect {
	public ReefCrackerMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -10066177);
		this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "4bf1ea4a-c7f0-37b3-8940-7ab10737ff32", 0.15, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		ParticleCrackerProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, amplifier);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		DeductBufflvlProcedure.execute(entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
