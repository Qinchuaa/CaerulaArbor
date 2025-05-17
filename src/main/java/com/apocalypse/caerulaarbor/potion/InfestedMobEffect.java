
package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.procedures.DamageByInfestedProcedure;
import com.apocalypse.caerulaarbor.procedures.OceanizePlayerProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class InfestedMobEffect extends MobEffect {
	public InfestedMobEffect() {
		super(MobEffectCategory.NEUTRAL, -3407668);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return new ArrayList<>();
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
		DamageByInfestedProcedure.execute(entity.level(), entity, amplifier);
	}

	@Override
	@ParametersAreNonnullByDefault
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		OceanizePlayerProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 40 == 0;
	}
}
