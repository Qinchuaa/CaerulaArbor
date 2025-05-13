
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
import net.minecraft.client.Minecraft;

import net.mcreator.caerulaarbor.procedures.ShowSanityProcedure;
import net.mcreator.caerulaarbor.procedures.GetSanityIndexProcedure;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class SanityShowOverlay {
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
		if (true) {
			if (ShowSanityProcedure.execute(entity)) {
				event.getGuiGraphics().blit(new ResourceLocation("caerula_arbor:textures/screens/sanity.png"), w / 2 + 92, h - 19, Mth.clamp((int) GetSanityIndexProcedure.execute(entity) * 16, 0, 304), 0, 16, 16, 320, 16);
			}
		}
	}
}
