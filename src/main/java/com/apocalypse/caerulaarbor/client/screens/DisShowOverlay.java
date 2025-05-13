
package com.apocalypse.caerulaarbor.client.screens;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.apocalypse.caerulaarbor.procedures.HasDisoProcedure;
import com.apocalypse.caerulaarbor.procedures.HasDisoNeuroProcedure;
import com.apocalypse.caerulaarbor.procedures.HasDisoFleshProcedure;
import com.apocalypse.caerulaarbor.procedures.HasDisoBloodProcedure;
import com.apocalypse.caerulaarbor.procedures.HasDisoAttentionProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class DisShowOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		if (HasDisoProcedure.execute(entity)) {
			if (HasDisoProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/disoclution_bg.png"), w - 64, h - 128, 0, 0, 64, 128, 64, 128);
			}
			if (HasDisoAttentionProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/disoclution_attention.png"), w - 69, h - 72, 0, 0, 64, 64, 64, 64);
			}
			if (HasDisoBloodProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/disoclution_blood.png"), w - 69, h - 72, 0, 0, 64, 64, 64, 64);
			}
			if (HasDisoNeuroProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/disoclution_neuro.png"), w - 69, h - 72, 0, 0, 64, 64, 64, 64);
			}
			if (HasDisoFleshProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/disoclution_flesh.png"), w - 69, h - 72, 0, 0, 64, 64, 64, 64);
			}
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
