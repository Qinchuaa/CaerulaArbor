package com.apocalypse.caerulaarbor.client.gui;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import com.apocalypse.caerulaarbor.world.inventory.RelicShowcaseMenu;
import com.apocalypse.caerulaarbor.procedures.HasXrystalProcedure;
import com.apocalypse.caerulaarbor.procedures.HasVoygoldProcedure;
import com.apocalypse.caerulaarbor.procedures.HasToponymProcedure;
import com.apocalypse.caerulaarbor.procedures.HasSwordProcedure;
import com.apocalypse.caerulaarbor.procedures.HasSurvContProcedure;
import com.apocalypse.caerulaarbor.procedures.HasSpearProcedure;
import com.apocalypse.caerulaarbor.procedures.HasSeagrassProcedure;
import com.apocalypse.caerulaarbor.procedures.HasScoreProcedure;
import com.apocalypse.caerulaarbor.procedures.HasSTAREProcedure;
import com.apocalypse.caerulaarbor.procedures.HasRoyalfateProcedure;
import com.apocalypse.caerulaarbor.procedures.HasResearvhProcedure;
import com.apocalypse.caerulaarbor.procedures.HasRescissionProcedure;
import com.apocalypse.caerulaarbor.procedures.HasRainbowProcedure;
import com.apocalypse.caerulaarbor.procedures.HasOrangeProcedure;
import com.apocalypse.caerulaarbor.procedures.HasOmnikeyProcedure;
import com.apocalypse.caerulaarbor.procedures.HasMusicboxProcedure;
import com.apocalypse.caerulaarbor.procedures.HasMeatcanProcedure;
import com.apocalypse.caerulaarbor.procedures.HasKettleProcedure;
import com.apocalypse.caerulaarbor.procedures.HasIrisProcedure;
import com.apocalypse.caerulaarbor.procedures.HasHeartProcedure;
import com.apocalypse.caerulaarbor.procedures.HasHandStrangleProcedure;
import com.apocalypse.caerulaarbor.procedures.HasHandFirewpedProcedure;
import com.apocalypse.caerulaarbor.procedures.HasHAndbarrenProcedure;
import com.apocalypse.caerulaarbor.procedures.HasGlowbodyProcedure;
import com.apocalypse.caerulaarbor.procedures.HasFluteProcedure;
import com.apocalypse.caerulaarbor.procedures.HasFlagProcedure;
import com.apocalypse.caerulaarbor.procedures.HasExtensionProcedure;
import com.apocalypse.caerulaarbor.procedures.HasEmelightProcedure;
import com.apocalypse.caerulaarbor.procedures.HasDurinProcedure;
import com.apocalypse.caerulaarbor.procedures.HasCrownProcedure;
import com.apocalypse.caerulaarbor.procedures.HasCrimcontraProcedure;
import com.apocalypse.caerulaarbor.procedures.HasCoffeeProcedure;
import com.apocalypse.caerulaarbor.procedures.HasChitinProcedure;
import com.apocalypse.caerulaarbor.procedures.HasBerryProcedure;
import com.apocalypse.caerulaarbor.procedures.HasBedProcedure;
import com.apocalypse.caerulaarbor.procedures.HasBatbedProcedure;
import com.apocalypse.caerulaarbor.procedures.HasArtifactProcedure;
import com.apocalypse.caerulaarbor.procedures.HasAromaticProcedure;
import com.apocalypse.caerulaarbor.procedures.HasArmorProcedure;
import com.apocalypse.caerulaarbor.procedures.HasAllayProcedure;
import com.apocalypse.caerulaarbor.procedures.HasAhndSwipeProcedure;
import com.apocalypse.caerulaarbor.procedures.HanshandSpikeProcedure;
import com.apocalypse.caerulaarbor.procedures.HansHandSpeedProcedure;
import com.apocalypse.caerulaarbor.procedures.HansHandEngraveProcedure;
import com.apocalypse.caerulaarbor.procedures.HandhandFertilityProcedure;
import com.apocalypse.caerulaarbor.procedures.GetlayerSurvcontaProcedure;
import com.apocalypse.caerulaarbor.procedures.GetlayerEnraveProcedure;
import com.apocalypse.caerulaarbor.procedures.DexrhandEngraveProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrhandSpikeProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrVoygoldProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrToponymProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrSwordProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrSurvcontraProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrStareProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrSeagrassProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrScoreProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrRoyalfateProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrResearchProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrRecissionProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrRainbowProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrOrangeProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrOmnikeyProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrMusicboxProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrMeatcanProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrKettleProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrIrisProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHeartProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandsSwipeProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandfertilotyProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandStrangelProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandSpeedProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandFirewprkProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrHandBarrenProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrGlowbodyProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrFluteProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrFlagProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrExtensProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrEmelightProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrDurinProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrCrownProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrCromcontraProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrCoffeeProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrChitinProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrBerriesProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrBedProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrBatbedProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrArtifactProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrAromaticProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrArmorProcedure;
import com.apocalypse.caerulaarbor.procedures.DescrAllayProcedure;
import com.apocalypse.caerulaarbor.procedures.DecrSpearProcedure;
import com.apocalypse.caerulaarbor.procedures.DecrCrystalProcedure;
import com.apocalypse.caerulaarbor.network.RelicShowcaseButtonMessage;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class RelicShowcaseScreen extends AbstractContainerScreen<RelicShowcaseMenu> {
	private final static HashMap<String, Object> guistate = RelicShowcaseMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_return;
	ImageButton imagebutton_relic_crown;
	ImageButton imagebutton_relic_spear;
	ImageButton imagebutton_kingsarmor;
	ImageButton imagebutton_extension;
	ImageButton imagebutton_kingcrystal;
	ImageButton imagebutton_archfiend_articraft;
	ImageButton imagebutton_archfi_flag;
	ImageButton imagebutton_archifi_bed;
	ImageButton imagebutton_royalfate;
	ImageButton imagebutton_hand_spike;
	ImageButton imagebutton_hand_reap;
	ImageButton imagebutton_hand_reap1;
	ImageButton imagebutton_hand_smash;
	ImageButton imagebutton_hand_swipe;
	ImageButton imagebutton_hand_curve;
	ImageButton imagebutton_hand_firework;
	ImageButton imagebutton_crimson_contarct_0;
	ImageButton imagebutton_survivor_contarct;
	ImageButton imagebutton_cursed_emelight_0;
	ImageButton imagebutton_cursed_glowbody_0;
	ImageButton imagebutton_cursed_research_0;
	ImageButton imagebutton_beef_can;
	ImageButton imagebutton_bowl_seagrass;
	ImageButton imagebutton_orangestorm;
	ImageButton imagebutton_coffee_candy;
	ImageButton imagebutton_cherrycan;
	ImageButton imagebutton_rainbow_candy;
	ImageButton imagebutton_boxcoffee;
	ImageButton imagebutton_musicboxsmall;
	ImageButton imagebutton_originium_iris;
	ImageButton imagebutton_flute;
	ImageButton imagebutton_voyageofsmall;
	ImageButton imagebutton_location_name;
	ImageButton imagebutton_kettle;
	ImageButton imagebutton_hand_sword;
	ImageButton imagebutton_chitinknife;
	ImageButton imagebutton_hand_speed;

	public RelicShowcaseScreen(RelicShowcaseMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 328;
		this.imageHeight = 216;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (HasCrownProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrCrownProcedure.execute()), mouseX, mouseY);
		if (HasSpearProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DecrSpearProcedure.execute()), mouseX, mouseY);
		if (HasArmorProcedure.execute(entity))
			if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrArmorProcedure.execute()), mouseX, mouseY);
		if (HasExtensionProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrExtensProcedure.execute()), mouseX, mouseY);
		if (HasXrystalProcedure.execute(entity))
			if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DecrCrystalProcedure.execute()), mouseX, mouseY);
		if (HasArtifactProcedure.execute(entity))
			if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrArtifactProcedure.execute()), mouseX, mouseY);
		if (HasFlagProcedure.execute(entity))
			if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrFlagProcedure.execute()), mouseX, mouseY);
		if (HasBedProcedure.execute(entity))
			if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrBedProcedure.execute()), mouseX, mouseY);
		if (HasRoyalfateProcedure.execute(entity))
			if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 4 && mouseY < topPos + 20)
				guiGraphics.renderTooltip(font, Component.literal(DescrRoyalfateProcedure.execute()), mouseX, mouseY);
		if (HanshandSpikeProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrhandSpikeProcedure.execute()), mouseX, mouseY);
		if (HasHandStrangleProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandStrangelProcedure.execute()), mouseX, mouseY);
		if (HandhandFertilityProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandfertilotyProcedure.execute()), mouseX, mouseY);
		if (HansHandSpeedProcedure.execute(entity))
			if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandSpeedProcedure.execute()), mouseX, mouseY);
		if (HasHAndbarrenProcedure.execute(entity))
			if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandBarrenProcedure.execute()), mouseX, mouseY);
		if (HasAhndSwipeProcedure.execute(entity))
			if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandsSwipeProcedure.execute()), mouseX, mouseY);
		if (HansHandEngraveProcedure.execute(entity))
			if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DexrhandEngraveProcedure.execute()), mouseX, mouseY);
		if (HasHandFirewpedProcedure.execute(entity))
			if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrHandFirewprkProcedure.execute()), mouseX, mouseY);
		if (HasCrimcontraProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 52 && mouseY < topPos + 68)
				guiGraphics.renderTooltip(font, Component.literal(DescrCromcontraProcedure.execute()), mouseX, mouseY);
		if (HasSurvContProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 52 && mouseY < topPos + 68)
				guiGraphics.renderTooltip(font, Component.literal(DescrSurvcontraProcedure.execute()), mouseX, mouseY);
		if (HasEmelightProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 196 && mouseY < topPos + 212)
				guiGraphics.renderTooltip(font, Component.literal(DescrEmelightProcedure.execute()), mouseX, mouseY);
		if (HasGlowbodyProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 196 && mouseY < topPos + 212)
				guiGraphics.renderTooltip(font, Component.literal(DescrGlowbodyProcedure.execute()), mouseX, mouseY);
		if (HasResearvhProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 196 && mouseY < topPos + 212)
				guiGraphics.renderTooltip(font, Component.literal(DescrResearchProcedure.execute()), mouseX, mouseY);
		if (HasMeatcanProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrMeatcanProcedure.execute()), mouseX, mouseY);
		if (HasSeagrassProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrSeagrassProcedure.execute()), mouseX, mouseY);
		if (HasOrangeProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrOrangeProcedure.execute()), mouseX, mouseY);
		if (HasCoffeeProcedure.execute(entity))
			if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrCoffeeProcedure.execute()), mouseX, mouseY);
		if (HasBerryProcedure.execute(entity))
			if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrBerriesProcedure.execute()), mouseX, mouseY);
		if (HasRainbowProcedure.execute(entity))
			if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrRainbowProcedure.execute()), mouseX, mouseY);
		if (HasAromaticProcedure.execute(entity))
			if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrAromaticProcedure.execute()), mouseX, mouseY);
		if (HasMusicboxProcedure.execute(entity))
			if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrMusicboxProcedure.execute()), mouseX, mouseY);
		if (HasIrisProcedure.execute(entity))
			if (mouseX > leftPos + 220 && mouseX < leftPos + 236 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrIrisProcedure.execute()), mouseX, mouseY);
		if (HasFluteProcedure.execute(entity))
			if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrFluteProcedure.execute()), mouseX, mouseY);
		if (HasVoygoldProcedure.execute(entity))
			if (mouseX > leftPos + 244 && mouseX < leftPos + 260 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrVoygoldProcedure.execute()), mouseX, mouseY);
		if (HasDurinProcedure.execute(entity))
			if (mouseX > leftPos + 268 && mouseX < leftPos + 284 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrDurinProcedure.execute()), mouseX, mouseY);
		if (HasToponymProcedure.execute(entity))
			if (mouseX > leftPos + 292 && mouseX < leftPos + 308 && mouseY > topPos + 76 && mouseY < topPos + 92)
				guiGraphics.renderTooltip(font, Component.literal(DescrToponymProcedure.execute()), mouseX, mouseY);
		if (HasKettleProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrKettleProcedure.execute()), mouseX, mouseY);
		if (HasChitinProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 52 && mouseY < topPos + 68)
				guiGraphics.renderTooltip(font, Component.literal(DescrChitinProcedure.execute()), mouseX, mouseY);
		if (HasAllayProcedure.execute(entity))
			if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrAllayProcedure.execute()), mouseX, mouseY);
		if (HasBatbedProcedure.execute(entity))
			if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrBatbedProcedure.execute()), mouseX, mouseY);
		if (HasOmnikeyProcedure.execute(entity))
			if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrOmnikeyProcedure.execute()), mouseX, mouseY);
		if (HasScoreProcedure.execute(entity))
			if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrScoreProcedure.execute()), mouseX, mouseY);
		if (HasRescissionProcedure.execute(entity))
			if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrRecissionProcedure.execute()), mouseX, mouseY);
		if (HasSTAREProcedure.execute(entity))
			if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 100 && mouseY < topPos + 116)
				guiGraphics.renderTooltip(font, Component.literal(DescrStareProcedure.execute()), mouseX, mouseY);
		if (HasSwordProcedure.execute(entity))
			if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 28 && mouseY < topPos + 44)
				guiGraphics.renderTooltip(font, Component.literal(DescrSwordProcedure.execute()), mouseX, mouseY);
		if (HasHeartProcedure.execute(entity))
			if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 196 && mouseY < topPos + 212)
				guiGraphics.renderTooltip(font, Component.literal(DescrHeartProcedure.execute()), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/relic_bg.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 328, 216, 328, 216);

		if (HasDurinProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/durin_diary.png"), this.leftPos + 268, this.topPos + 76, 0, 0, 16, 16, 16, 16);
		}
		if (HasAllayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/stonealley.png"), this.leftPos + 28, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasBatbedProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/itembatbed.png"), this.leftPos + 52, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasOmnikeyProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/omnikey.png"), this.leftPos + 124, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasScoreProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/score.png"), this.leftPos + 76, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasRescissionProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/rescission.png"), this.leftPos + 100, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasSTAREProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/guardianstare.png"), this.leftPos + 148, this.topPos + 100, 0, 0, 16, 16, 16, 16);
		}
		if (HasHeartProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/caerulaheart.png"), this.leftPos + 76, this.topPos + 196, 0, 0, 16, 16, 16, 16);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.caerula_arbor.relic_showcase.label_relic_showcase"), 4, -12, -1, false);
		if (HansHandEngraveProcedure.execute(entity))
			guiGraphics.drawString(this.font,

					GetlayerEnraveProcedure.execute(entity), 157, 36, -16777165, false);
		if (HansHandEngraveProcedure.execute(entity))
			guiGraphics.drawString(this.font,

					GetlayerEnraveProcedure.execute(entity), 156, 36, -1, false);
		if (HasSurvContProcedure.execute(entity))
			guiGraphics.drawString(this.font,

					GetlayerSurvcontaProcedure.execute(entity), 37, 60, -12829636, false);
		if (HasSurvContProcedure.execute(entity))
			guiGraphics.drawString(this.font,

					GetlayerSurvcontaProcedure.execute(entity), 36, 60, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_return = new PlainTextButton(this.leftPos + 292, this.topPos + 204, 24, 20, Component.translatable("gui.caerula_arbor.relic_showcase.button_return"), e -> {
			if (true) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(0, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}, this.font);
		guistate.put("button:button_return", button_return);
		this.addRenderableWidget(button_return);
		imagebutton_relic_crown = new ImageButton(this.leftPos + 4, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_relic_crown.png"), 16, 32, e -> {
			if (HasCrownProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(1, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasCrownProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_relic_crown", imagebutton_relic_crown);
		this.addRenderableWidget(imagebutton_relic_crown);
		imagebutton_relic_spear = new ImageButton(this.leftPos + 28, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_relic_spear.png"), 16, 32, e -> {
			if (HasSpearProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(2, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasSpearProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_relic_spear", imagebutton_relic_spear);
		this.addRenderableWidget(imagebutton_relic_spear);
		imagebutton_kingsarmor = new ImageButton(this.leftPos + 100, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_kingsarmor.png"), 16, 32, e -> {
			if (HasArmorProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(3, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasArmorProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_kingsarmor", imagebutton_kingsarmor);
		this.addRenderableWidget(imagebutton_kingsarmor);
		imagebutton_extension = new ImageButton(this.leftPos + 52, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_extension.png"), 16, 32, e -> {
			if (HasExtensionProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(4, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasExtensionProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_extension", imagebutton_extension);
		this.addRenderableWidget(imagebutton_extension);
		imagebutton_kingcrystal = new ImageButton(this.leftPos + 76, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_kingcrystal.png"), 16, 32, e -> {
			if (HasXrystalProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(5, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasXrystalProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_kingcrystal", imagebutton_kingcrystal);
		this.addRenderableWidget(imagebutton_kingcrystal);
		imagebutton_archfiend_articraft = new ImageButton(this.leftPos + 124, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archfiend_articraft.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasArtifactProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_archfiend_articraft", imagebutton_archfiend_articraft);
		this.addRenderableWidget(imagebutton_archfiend_articraft);
		imagebutton_archfi_flag = new ImageButton(this.leftPos + 148, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archfi_flag.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasFlagProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_archfi_flag", imagebutton_archfi_flag);
		this.addRenderableWidget(imagebutton_archfi_flag);
		imagebutton_archifi_bed = new ImageButton(this.leftPos + 172, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archifi_bed.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasBedProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_archifi_bed", imagebutton_archifi_bed);
		this.addRenderableWidget(imagebutton_archifi_bed);
		imagebutton_royalfate = new ImageButton(this.leftPos + 196, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_royalfate.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasRoyalfateProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_royalfate", imagebutton_royalfate);
		this.addRenderableWidget(imagebutton_royalfate);
		imagebutton_hand_spike = new ImageButton(this.leftPos + 4, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_spike.png"), 16, 32, e -> {
			if (HanshandSpikeProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(10, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 10, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HanshandSpikeProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_spike", imagebutton_hand_spike);
		this.addRenderableWidget(imagebutton_hand_spike);
		imagebutton_hand_reap = new ImageButton(this.leftPos + 28, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_reap.png"), 16, 32, e -> {
			if (HasHandStrangleProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(11, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 11, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasHandStrangleProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_reap", imagebutton_hand_reap);
		this.addRenderableWidget(imagebutton_hand_reap);
		imagebutton_hand_reap1 = new ImageButton(this.leftPos + 52, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_reap1.png"), 16, 32, e -> {
			if (HandhandFertilityProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(12, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 12, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HandhandFertilityProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_reap1", imagebutton_hand_reap1);
		this.addRenderableWidget(imagebutton_hand_reap1);
		imagebutton_hand_smash = new ImageButton(this.leftPos + 100, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_smash.png"), 16, 32, e -> {
			if (HasHAndbarrenProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(13, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 13, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasHAndbarrenProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_smash", imagebutton_hand_smash);
		this.addRenderableWidget(imagebutton_hand_smash);
		imagebutton_hand_swipe = new ImageButton(this.leftPos + 124, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_swipe.png"), 16, 32, e -> {
			if (HasAhndSwipeProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(14, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 14, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasAhndSwipeProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_swipe", imagebutton_hand_swipe);
		this.addRenderableWidget(imagebutton_hand_swipe);
		imagebutton_hand_curve = new ImageButton(this.leftPos + 148, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_curve.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HansHandEngraveProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_curve", imagebutton_hand_curve);
		this.addRenderableWidget(imagebutton_hand_curve);
		imagebutton_hand_firework = new ImageButton(this.leftPos + 172, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_firework.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasHandFirewpedProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_firework", imagebutton_hand_firework);
		this.addRenderableWidget(imagebutton_hand_firework);
		imagebutton_crimson_contarct_0 = new ImageButton(this.leftPos + 4, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_crimson_contarct_0.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasCrimcontraProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_crimson_contarct_0", imagebutton_crimson_contarct_0);
		this.addRenderableWidget(imagebutton_crimson_contarct_0);
		imagebutton_survivor_contarct = new ImageButton(this.leftPos + 28, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_survivor_contarct.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasSurvContProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_survivor_contarct", imagebutton_survivor_contarct);
		this.addRenderableWidget(imagebutton_survivor_contarct);
		imagebutton_cursed_emelight_0 = new ImageButton(this.leftPos + 4, this.topPos + 196, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_cursed_emelight_0.png"), 16, 32, e -> {
			if (HasEmelightProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(19, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 19, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasEmelightProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_cursed_emelight_0", imagebutton_cursed_emelight_0);
		this.addRenderableWidget(imagebutton_cursed_emelight_0);
		imagebutton_cursed_glowbody_0 = new ImageButton(this.leftPos + 28, this.topPos + 196, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_cursed_glowbody_0.png"), 16, 32, e -> {
			if (HasGlowbodyProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(20, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 20, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasGlowbodyProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_cursed_glowbody_0", imagebutton_cursed_glowbody_0);
		this.addRenderableWidget(imagebutton_cursed_glowbody_0);
		imagebutton_cursed_research_0 = new ImageButton(this.leftPos + 52, this.topPos + 196, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_cursed_research_0.png"), 16, 32, e -> {
			if (HasResearvhProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(21, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 21, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasResearvhProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_cursed_research_0", imagebutton_cursed_research_0);
		this.addRenderableWidget(imagebutton_cursed_research_0);
		imagebutton_beef_can = new ImageButton(this.leftPos + 4, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_beef_can.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasMeatcanProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_beef_can", imagebutton_beef_can);
		this.addRenderableWidget(imagebutton_beef_can);
		imagebutton_bowl_seagrass = new ImageButton(this.leftPos + 28, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_bowl_seagrass.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasSeagrassProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_bowl_seagrass", imagebutton_bowl_seagrass);
		this.addRenderableWidget(imagebutton_bowl_seagrass);
		imagebutton_orangestorm = new ImageButton(this.leftPos + 52, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_orangestorm.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasOrangeProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_orangestorm", imagebutton_orangestorm);
		this.addRenderableWidget(imagebutton_orangestorm);
		imagebutton_coffee_candy = new ImageButton(this.leftPos + 76, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_coffee_candy.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasCoffeeProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_coffee_candy", imagebutton_coffee_candy);
		this.addRenderableWidget(imagebutton_coffee_candy);
		imagebutton_cherrycan = new ImageButton(this.leftPos + 124, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_cherrycan.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasBerryProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_cherrycan", imagebutton_cherrycan);
		this.addRenderableWidget(imagebutton_cherrycan);
		imagebutton_rainbow_candy = new ImageButton(this.leftPos + 100, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_rainbow_candy.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasRainbowProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_rainbow_candy", imagebutton_rainbow_candy);
		this.addRenderableWidget(imagebutton_rainbow_candy);
		imagebutton_boxcoffee = new ImageButton(this.leftPos + 148, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_boxcoffee.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasAromaticProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_boxcoffee", imagebutton_boxcoffee);
		this.addRenderableWidget(imagebutton_boxcoffee);
		imagebutton_musicboxsmall = new ImageButton(this.leftPos + 172, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_musicboxsmall.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasMusicboxProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_musicboxsmall", imagebutton_musicboxsmall);
		this.addRenderableWidget(imagebutton_musicboxsmall);
		imagebutton_originium_iris = new ImageButton(this.leftPos + 220, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_originium_iris.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasIrisProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_originium_iris", imagebutton_originium_iris);
		this.addRenderableWidget(imagebutton_originium_iris);
		imagebutton_flute = new ImageButton(this.leftPos + 196, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_flute.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasFluteProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_flute", imagebutton_flute);
		this.addRenderableWidget(imagebutton_flute);
		imagebutton_voyageofsmall = new ImageButton(this.leftPos + 244, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_voyageofsmall.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasVoygoldProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_voyageofsmall", imagebutton_voyageofsmall);
		this.addRenderableWidget(imagebutton_voyageofsmall);
		imagebutton_location_name = new ImageButton(this.leftPos + 292, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_location_name.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasToponymProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_location_name", imagebutton_location_name);
		this.addRenderableWidget(imagebutton_location_name);
		imagebutton_kettle = new ImageButton(this.leftPos + 4, this.topPos + 100, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_kettle.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasKettleProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_kettle", imagebutton_kettle);
		this.addRenderableWidget(imagebutton_kettle);
		imagebutton_hand_sword = new ImageButton(this.leftPos + 196, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_sword.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasSwordProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_sword", imagebutton_hand_sword);
		this.addRenderableWidget(imagebutton_hand_sword);
		imagebutton_chitinknife = new ImageButton(this.leftPos + 52, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_chitinknife.png"), 16, 32, e -> {
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HasChitinProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_chitinknife", imagebutton_chitinknife);
		this.addRenderableWidget(imagebutton_chitinknife);
		imagebutton_hand_speed = new ImageButton(this.leftPos + 76, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_speed.png"), 16, 32, e -> {
			if (HansHandSpeedProcedure.execute(entity)) {
				CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(37, x, y, z));
				RelicShowcaseButtonMessage.handleButtonAction(entity, 37, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				this.visible = HansHandSpeedProcedure.execute(entity);
				super.renderWidget(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_hand_speed", imagebutton_hand_speed);
		this.addRenderableWidget(imagebutton_hand_speed);
	}
}
