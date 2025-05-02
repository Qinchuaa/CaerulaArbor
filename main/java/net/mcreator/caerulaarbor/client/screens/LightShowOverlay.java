
package net.mcreator.caerulaarbor.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.caerulaarbor.procedures.ShowSanityProcedure;
import net.mcreator.caerulaarbor.procedures.LightIsWavingProcedure;
import net.mcreator.caerulaarbor.procedures.LightIsDimProcedure;
import net.mcreator.caerulaarbor.procedures.LightIsCeasedProcedure;
import net.mcreator.caerulaarbor.procedures.LightIsBrightProcedure;
import net.mcreator.caerulaarbor.procedures.IfShowProcedure;
import net.mcreator.caerulaarbor.procedures.HaveShieldProcedure;
import net.mcreator.caerulaarbor.procedures.GetShieldProcedure;
import net.mcreator.caerulaarbor.procedures.GetSanityIndexProcedure;
import net.mcreator.caerulaarbor.procedures.GetLivesProcedure;
import net.mcreator.caerulaarbor.procedures.GetLiveMaxShownProcedure;
import net.mcreator.caerulaarbor.procedures.GetLightProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class LightShowOverlay {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
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
		if (IfShowProcedure.execute(entity)) {
			if (LightIsCeasedProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_extinguish.png"), w / 2 + -32, 0, 0, 0, 64, 32, 64, 32);
			}
			if (LightIsDimProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_dim.png"), w / 2 + -32, 0, 0, 0, 64, 32, 64, 32);
			}
			if (LightIsWavingProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_waving.png"), w / 2 + -32, 0, 0, 0, 64, 32, 64, 32);
			}
			if (LightIsBrightProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light.png"), w / 2 + -32, 0, 0, 0, 64, 32, 64, 32);
			}

			event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/target_health.png"), 6, h - 24, 0, 0, 24, 16, 24, 16);

			if (HaveShieldProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/target_shield.png"), 6, h - 40, 0, 0, 24, 16, 24, 16);
			}
			if (ShowSanityProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/sanity.png"), w - 117, h - 24, Mth.clamp((int) GetSanityIndexProcedure.execute(entity) * 16, 0, 304), 0, 16, 16, 320, 16);
			}
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLightProcedure.execute(entity), w / 2 + -5, 26, -13421773, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLightProcedure.execute(entity), w / 2 + -6, 26, -1, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLiveMaxShownProcedure.execute(entity), 37, h - 21, -16764109, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLiveMaxShownProcedure.execute(entity), 36, h - 21, -10040065, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLivesProcedure.execute(entity), 21, h - 21, -16764109, false);
			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					GetLivesProcedure.execute(entity), 20, h - 21, -1, false);
			if (HaveShieldProcedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						GetShieldProcedure.execute(entity), 21, h - 36, -16777216, false);
			if (HaveShieldProcedure.execute(entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font,

						GetShieldProcedure.execute(entity), 20, h - 36, -1, false);
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
