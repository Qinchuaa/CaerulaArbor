
package com.apocalypse.caerulaarbor.client.screens;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import com.apocalypse.caerulaarbor.procedures.GetKingiconProcedure;
import com.apocalypse.caerulaarbor.procedures.GetDemonIconProcedure;
import com.apocalypse.caerulaarbor.procedures.GetChitinProcedure;
import com.apocalypse.caerulaarbor.procedures.AntiShowPtcProcedure;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class RelicFuncShowOverlay {
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
		if (AntiShowPtcProcedure.execute(entity)) {

			event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/icon_king.png"), 6, 8, Mth.clamp((int) GetKingiconProcedure.execute(entity) * 16, 0, 32), 0, 16, 16, 48, 16);

			event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/icon_artifi.png"), 22, 8, Mth.clamp((int) GetDemonIconProcedure.execute(entity) * 16, 0, 32), 0, 16, 16, 48, 16);

			if (GetChitinProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/icon_chitin.png"), 38, 8, 0, 0, 16, 16, 16, 16);
			}
		}
	}
}
