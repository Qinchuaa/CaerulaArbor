package net.mcreator.caerulaarbor.client.gui;

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

import net.mcreator.caerulaarbor.world.inventory.RelicShowcaseMenu;
import net.mcreator.caerulaarbor.procedures.HasXrystalProcedure;
import net.mcreator.caerulaarbor.procedures.HasVoygoldProcedure;
import net.mcreator.caerulaarbor.procedures.HasToponymProcedure;
import net.mcreator.caerulaarbor.procedures.HasSwordProcedure;
import net.mcreator.caerulaarbor.procedures.HasSurvContProcedure;
import net.mcreator.caerulaarbor.procedures.HasSpearProcedure;
import net.mcreator.caerulaarbor.procedures.HasSeagrassProcedure;
import net.mcreator.caerulaarbor.procedures.HasScoreProcedure;
import net.mcreator.caerulaarbor.procedures.HasSTAREProcedure;
import net.mcreator.caerulaarbor.procedures.HasRoyalfateProcedure;
import net.mcreator.caerulaarbor.procedures.HasResearvhProcedure;
import net.mcreator.caerulaarbor.procedures.HasRescissionProcedure;
import net.mcreator.caerulaarbor.procedures.HasRainbowProcedure;
import net.mcreator.caerulaarbor.procedures.HasOrangeProcedure;
import net.mcreator.caerulaarbor.procedures.HasOmnikeyProcedure;
import net.mcreator.caerulaarbor.procedures.HasMusicboxProcedure;
import net.mcreator.caerulaarbor.procedures.HasMeatcanProcedure;
import net.mcreator.caerulaarbor.procedures.HasKettleProcedure;
import net.mcreator.caerulaarbor.procedures.HasIrisProcedure;
import net.mcreator.caerulaarbor.procedures.HasHandStrangleProcedure;
import net.mcreator.caerulaarbor.procedures.HasHandFirewpedProcedure;
import net.mcreator.caerulaarbor.procedures.HasHAndbarrenProcedure;
import net.mcreator.caerulaarbor.procedures.HasGlowbodyProcedure;
import net.mcreator.caerulaarbor.procedures.HasFluteProcedure;
import net.mcreator.caerulaarbor.procedures.HasFlagProcedure;
import net.mcreator.caerulaarbor.procedures.HasExtensionProcedure;
import net.mcreator.caerulaarbor.procedures.HasEmelightProcedure;
import net.mcreator.caerulaarbor.procedures.HasDurinProcedure;
import net.mcreator.caerulaarbor.procedures.HasCrownProcedure;
import net.mcreator.caerulaarbor.procedures.HasCrimcontraProcedure;
import net.mcreator.caerulaarbor.procedures.HasCoffeeProcedure;
import net.mcreator.caerulaarbor.procedures.HasChitinProcedure;
import net.mcreator.caerulaarbor.procedures.HasBerryProcedure;
import net.mcreator.caerulaarbor.procedures.HasBedProcedure;
import net.mcreator.caerulaarbor.procedures.HasBatbedProcedure;
import net.mcreator.caerulaarbor.procedures.HasArtifactProcedure;
import net.mcreator.caerulaarbor.procedures.HasAromaticProcedure;
import net.mcreator.caerulaarbor.procedures.HasArmorProcedure;
import net.mcreator.caerulaarbor.procedures.HasAllayProcedure;
import net.mcreator.caerulaarbor.procedures.HasAhndSwipeProcedure;
import net.mcreator.caerulaarbor.procedures.HanshandSpikeProcedure;
import net.mcreator.caerulaarbor.procedures.HansHandSpeedProcedure;
import net.mcreator.caerulaarbor.procedures.HansHandEngraveProcedure;
import net.mcreator.caerulaarbor.procedures.HandhandFertilityProcedure;
import net.mcreator.caerulaarbor.procedures.GetlayerSurvcontaProcedure;
import net.mcreator.caerulaarbor.procedures.GetlayerEnraveProcedure;
import net.mcreator.caerulaarbor.procedures.DexrhandEngraveProcedure;
import net.mcreator.caerulaarbor.procedures.DescrhandSpikeProcedure;
import net.mcreator.caerulaarbor.procedures.DescrVoygoldProcedure;
import net.mcreator.caerulaarbor.procedures.DescrToponymProcedure;
import net.mcreator.caerulaarbor.procedures.DescrSwordProcedure;
import net.mcreator.caerulaarbor.procedures.DescrSurvcontraProcedure;
import net.mcreator.caerulaarbor.procedures.DescrStareProcedure;
import net.mcreator.caerulaarbor.procedures.DescrSeagrassProcedure;
import net.mcreator.caerulaarbor.procedures.DescrScoreProcedure;
import net.mcreator.caerulaarbor.procedures.DescrRoyalfateProcedure;
import net.mcreator.caerulaarbor.procedures.DescrResearchProcedure;
import net.mcreator.caerulaarbor.procedures.DescrRecissionProcedure;
import net.mcreator.caerulaarbor.procedures.DescrRainbowProcedure;
import net.mcreator.caerulaarbor.procedures.DescrOrangeProcedure;
import net.mcreator.caerulaarbor.procedures.DescrOmnikeyProcedure;
import net.mcreator.caerulaarbor.procedures.DescrMusicboxProcedure;
import net.mcreator.caerulaarbor.procedures.DescrMeatcanProcedure;
import net.mcreator.caerulaarbor.procedures.DescrKettleProcedure;
import net.mcreator.caerulaarbor.procedures.DescrIrisProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandsSwipeProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandfertilotyProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandStrangelProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandSpeedProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandFirewprkProcedure;
import net.mcreator.caerulaarbor.procedures.DescrHandBarrenProcedure;
import net.mcreator.caerulaarbor.procedures.DescrGlowbodyProcedure;
import net.mcreator.caerulaarbor.procedures.DescrFluteProcedure;
import net.mcreator.caerulaarbor.procedures.DescrFlagProcedure;
import net.mcreator.caerulaarbor.procedures.DescrExtensProcedure;
import net.mcreator.caerulaarbor.procedures.DescrEmelightProcedure;
import net.mcreator.caerulaarbor.procedures.DescrDurinProcedure;
import net.mcreator.caerulaarbor.procedures.DescrCrownProcedure;
import net.mcreator.caerulaarbor.procedures.DescrCromcontraProcedure;
import net.mcreator.caerulaarbor.procedures.DescrCoffeeProcedure;
import net.mcreator.caerulaarbor.procedures.DescrChitinProcedure;
import net.mcreator.caerulaarbor.procedures.DescrBerriesProcedure;
import net.mcreator.caerulaarbor.procedures.DescrBedProcedure;
import net.mcreator.caerulaarbor.procedures.DescrBatbedProcedure;
import net.mcreator.caerulaarbor.procedures.DescrArtifactProcedure;
import net.mcreator.caerulaarbor.procedures.DescrAromaticProcedure;
import net.mcreator.caerulaarbor.procedures.DescrArmorProcedure;
import net.mcreator.caerulaarbor.procedures.DescrAllayProcedure;
import net.mcreator.caerulaarbor.procedures.DecrSpearProcedure;
import net.mcreator.caerulaarbor.procedures.DecrCrystalProcedure;
import net.mcreator.caerulaarbor.network.RelicShowcaseButtonMessage;
import net.mcreator.caerulaarbor.CaerulaArborMod;

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
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/relic_bg.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 328, 216, 328, 216);

		if (HansHandSpeedProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/hand_speed.png"), this.leftPos + 76, this.topPos + 28, 0, 0, 16, 16, 16, 16);
		}
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
	}
}
