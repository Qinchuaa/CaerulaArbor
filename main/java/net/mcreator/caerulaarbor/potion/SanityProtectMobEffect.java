
package net.mcreator.caerulaarbor.potion;

import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.caerulaarbor.init.CaerulaArborModAttributes;

import java.util.List;
import java.util.ArrayList;

public class SanityProtectMobEffect extends MobEffect {
	public SanityProtectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -6684724);
		this.addAttributeModifier(CaerulaArborModAttributes.SANITY_MODIFIER.get(), "92276964-0e2a-345f-b7fb-492831284e84", -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL);
		this.addAttributeModifier(CaerulaArborModAttributes.SANITY_RATE.get(), "5c2e2c42-635d-3d77-b67c-a3b051eef465", 1, AttributeModifier.Operation.ADDITION);
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
