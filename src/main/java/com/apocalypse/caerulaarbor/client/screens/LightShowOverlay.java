
package com.apocalypse.caerulaarbor.client.screens;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.apocalypse.caerulaarbor.capability.player.PlayerVariable;
import com.apocalypse.caerulaarbor.procedures.*;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class LightShowOverlay {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        Player entity = Minecraft.getInstance().player;

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        boolean result = false;
        if (entity != null) {
            result = (((Entity) entity).getCapability(ModCapabilities.PLAYER_VARIABLE).orElse(new PlayerVariable())).show_stats;
        }
        if (result) {
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c2 -> c2.light < 1)
                    .orElse(false)) {
                event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_extinguish.png"), w / 2 - 32, 0, 0, 0, 64, 32, 64, 32);
            }
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c1 -> c1.light >= 1 && c1.light < 50)
                    .orElse(false)) {
                event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_dim.png"), w / 2 - 32, 0, 0, 0, 64, 32, 64, 32);
            }
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c -> c.light < 85 && c.light >= 50)
                    .orElse(false)) {
                event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light_waving.png"), w / 2 - 32, 0, 0, 0, 64, 32, 64, 32);
            }
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(cap -> cap.light >= 85)
                    .orElse(false)) {
                event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/light.png"), w / 2 - 32, 0, 0, 0, 64, 32, 64, 32);
            }

            event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/target_health.png"), 6, h - 24, 0, 0, 24, 16, 24, 16);

            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c2 -> c2.shield > 0)
                    .orElse(false)) {
                event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/target_shield.png"), 6, h - 40, 0, 0, 24, 16, 24, 16);
            }
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLightProcedure.execute(entity), w / 2 - 5, 26, -13421773, false);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLightProcedure.execute(entity), w / 2 - 6, 26, -1, false);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLiveMaxShownProcedure.execute(entity), 37, h - 21, -16764109, false);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLiveMaxShownProcedure.execute(entity), 36, h - 21, -10040065, false);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLivesProcedure.execute(entity), 21, h - 21, -16764109, false);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                    GetLivesProcedure.execute(entity), 20, h - 21, -1, false);
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c1 -> c1.shield > 0)
                    .orElse(false))
                event.getGuiGraphics().drawString(Minecraft.getInstance().font,

                        GetShieldProcedure.execute(entity), 21, h - 36, -16777216, false);
            if (entity.getCapability(ModCapabilities.PLAYER_VARIABLE)
                    .map(c -> c.shield > 0)
                    .orElse(false))
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
