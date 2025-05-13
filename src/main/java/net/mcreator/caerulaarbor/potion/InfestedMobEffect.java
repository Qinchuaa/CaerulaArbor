
package net.mcreator.caerulaarbor.potion;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.caerulaarbor.procedures.OceanizePlayerProcedure;
import net.mcreator.caerulaarbor.procedures.FuncBySecondsProcedure;
import net.mcreator.caerulaarbor.procedures.DamageByInfestedProcedure;

import java.util.List;
import java.util.ArrayList;

public class InfestedMobEffect extends MobEffect {
	public InfestedMobEffect() {
		super(MobEffectCategory.NEUTRAL, -3407668);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		DamageByInfestedProcedure.execute(entity.level(), entity, amplifier);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		OceanizePlayerProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return FuncBySecondsProcedure.execute(duration);
	}
}
