
package net.mcreator.caerulaarbor.potion;

import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.ArrayList;

public class FrozenMobEffect extends MobEffect {
	public FrozenMobEffect() {
		super(MobEffectCategory.NEUTRAL, -3342337);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "8922b6c1-6d48-33e3-b722-38c69d2e5222", -1, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "ea07d8a3-7c54-31cf-a97b-b360a7c7bdfd", -1, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(Attributes.ATTACK_SPEED, "51edf12a-1385-3193-a3e9-4fc6f18b9bcd", -1, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(Attributes.JUMP_STRENGTH, "044bbf44-aa84-3132-8b2a-5674186ae8b9", -1, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "7867da7d-0b51-3f2f-8b33-476555413ccf", 0.5, AttributeModifier.Operation.ADDITION);
		this.addAttributeModifier(ForgeMod.STEP_HEIGHT_ADDITION.get(), "e8fabb9b-c39f-30f3-b4d4-73429435aea6", -1, AttributeModifier.Operation.MULTIPLY_BASE);
		this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "19abee04-cf6c-39cc-bf1c-ba6c38b014f4", -1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(Attributes.ARMOR, "5795c748-fd3e-3c4e-a4e8-041dd19beb60", -0.4, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
