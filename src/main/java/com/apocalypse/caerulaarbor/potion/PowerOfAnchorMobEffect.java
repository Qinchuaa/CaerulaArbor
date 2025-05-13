
package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.procedures.FuncBySecondsProcedure;
import com.apocalypse.caerulaarbor.procedures.ReviveSanityLightProcedure;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.GuiGraphics;

import com.apocalypse.caerulaarbor.init.CaerulaArborModAttributes;

import java.util.List;
import java.util.ArrayList;

public class PowerOfAnchorMobEffect extends MobEffect {
	public PowerOfAnchorMobEffect() {
		super(MobEffectCategory.NEUTRAL, -6684724);
		this.addAttributeModifier(CaerulaArborModAttributes.SANITY_MODIFIER.get(), "fca8c1c6-9152-3107-9573-bd52fa24d2f9", -0.4, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		ReviveSanityLightProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return FuncBySecondsProcedure.execute(duration);
	}

	@Override
	public void initializeClient(java.util.function.Consumer<IClientMobEffectExtensions> consumer) {
		consumer.accept(new IClientMobEffectExtensions() {
			@Override
			public boolean isVisibleInInventory(MobEffectInstance effect) {
				return false;
			}

			@Override
			public boolean renderInventoryText(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, GuiGraphics guiGraphics, int x, int y, int blitOffset) {
				return false;
			}

			@Override
			public boolean isVisibleInGui(MobEffectInstance effect) {
				return false;
			}
		});
	}
}
