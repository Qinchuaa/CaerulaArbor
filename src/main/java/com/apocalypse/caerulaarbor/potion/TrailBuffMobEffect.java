
package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.procedures.FuncByaSecondProcedure;
import com.apocalypse.caerulaarbor.procedures.TrailTrapProcedure;
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

public class TrailBuffMobEffect extends MobEffect {
	public TrailBuffMobEffect() {
		super(MobEffectCategory.NEUTRAL, -10053121);
		this.addAttributeModifier(CaerulaArborModAttributes.SANITY_RATE.get(), "204fd8a1-28dc-3106-bb46-8c9977e6079d", 1, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> cures = new ArrayList<ItemStack>();
		return cures;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		TrailTrapProcedure.execute(entity.level(), entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return FuncByaSecondProcedure.execute(duration);
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
