package com.apocalypse.caerulaarbor.client.gui;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import com.apocalypse.caerulaarbor.network.RelicShowcaseButtonMessage;
import com.apocalypse.caerulaarbor.procedures.*;
import com.apocalypse.caerulaarbor.world.inventory.RelicShowcaseMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

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
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        var cap = entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables());

        if (cap.relic_king_CROWN)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.relic_crown").getString() + ": " + Component.translatable("item.caerula_arbor.relic_crown.description_0").getString()), mouseX, mouseY);
        if (cap.relic_king_SPEAR)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.kings_spear").getString() + ": " + Component.translatable("item.caerula_arbor.kings_spear.description_0").getString()), mouseX, mouseY);
        if (cap.relic_king_ARMOR)
            if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.kings_armour").getString() + ": " + Component.translatable("item.caerula_arbor.kings_armour.description_0").getString()), mouseX, mouseY);
        if (cap.relic_king_EXTENSION)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.kings_extension").getString() + ": " + Component.translatable("item.caerula_arbor.kings_extension.description_0").getString()), mouseX, mouseY);
        if (cap.relic_king_CRYSTAL)
            if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.kings_crystal").getString() + ": " + Component.translatable("item.caerula_arbor.kings_crystal.description_0").getString()), mouseX, mouseY);
        if (cap.relic_archfi_ARTIFACT)
            if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.archfiends_artifact").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_artifact.description_0").getString()), mouseX, mouseY);
        if (cap.relic_archfi_FLAG)
            if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.archfiends_flag").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_flag.description_0").getString()), mouseX, mouseY);
        if (cap.relic_archfi_BED)
            if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.archfiends_bed").getString() + ": " + Component.translatable("item.caerula_arbor.archfiends_bed.description_0").getString()), mouseX, mouseY);
        if (cap.relic_archifi_RYLFATE)
            if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 4 && mouseY < topPos + 20)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.royal_fate").getString() + ": " + Component.translatable("item.caerula_arbor.royal_fate.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_THORNS)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_thorns").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_thorns.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_STRANGLE)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_strangle").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_strangle.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_FERTILITY)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_fertiliy").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_fertiliy.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_SPEED)
            if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_speed").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_speed.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_BARREN)
            if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_barren").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_barren.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_SWIPE)
            if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_spotless").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_spotless.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_ENGRAVE >= 0)
            if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_engrave").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_engrave.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_FIREWORK)
            if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_of_firework").getString() + ": " + Component.translatable("item.caerula_arbor.hand_of_firework.description_0").getString()), mouseX, mouseY);
        if (cap.relic_TREATY)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 52 && mouseY < topPos + 68)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.crimson_treaty").getString() + ": " + Component.translatable("item.caerula_arbor.crimson_treaty.description_0").getString()), mouseX, mouseY);
        if (cap.relic_SURVIVOR >= 0)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 52 && mouseY < topPos + 68)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.survivor_contract").getString() + ": " + Component.translatable("item.caerula_arbor.survivor_contract.description_0").getString()), mouseX, mouseY);
        if (cap.relic_cursed_EMELIGHT)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 196 && mouseY < topPos + 212)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.relic_curse_emelight").getString() + ": " + Component.translatable("item.caerula_arbor.relic_curse_emelight.description_0").getString()), mouseX, mouseY);
        if (cap.relic_cursed_GLOWBODY)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 196 && mouseY < topPos + 212)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.relic_cursed_glowbody").getString() + ": " + Component.translatable("item.caerula_arbor.relic_cursed_glowbody.description_0").getString()), mouseX, mouseY);
        if (cap.relic_cursed_RESEARCH)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 196 && mouseY < topPos + 212)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.relic_cursed_research").getString() + ": " + Component.translatable("item.caerula_arbor.relic_cursed_research.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_MEATCAN)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.meat_can").getString() + ": " + Component.translatable("item.caerula_arbor.meat_can.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_SEAGRASS)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.bowl_seagrass").getString() + ": " + Component.translatable("item.caerula_arbor.bowl_seagrass.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_ORANGE)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.golden_storm").getString() + ": " + Component.translatable("item.caerula_arbor.golden_storm.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_COFFEE)
            if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.coffee_candy").getString() + ": " + Component.translatable("item.caerula_arbor.coffee_candy.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_BERRIES)
            if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.canned_cherry").getString() + ": " + Component.translatable("item.caerula_arbor.canned_cherry.description_0").getString()), mouseX, mouseY);
        if (cap.player_util_RAINBOW)
            if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.rainbow_candy").getString() + ": " + Component.translatable("item.caerula_arbor.rainbow_candy.description_0").getString()), mouseX, mouseY);
        if (cap.player_util_AROMATIC)
            if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.aromatic_coffee").getString() + ": " + Component.translatable("item.caerula_arbor.aromatic_coffee.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_MUSICBOX)
            if (mouseX > leftPos + 172 && mouseX < leftPos + 188 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.solo_music_box").getString() + ": " + Component.translatable("item.caerula_arbor.solo_music_box.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_IRIS)
            if (mouseX > leftPos + 220 && mouseX < leftPos + 236 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.redstone_iris_flower").getString() + ": " + Component.translatable("item.caerula_arbor.redstone_iris_flower.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_FLUTE)
            if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.odd_flute").getString() + ": " + Component.translatable("item.caerula_arbor.odd_flute.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_VOYGOLD)
            if (mouseX > leftPos + 244 && mouseX < leftPos + 260 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.pure_gold_expedition").getString() + ": " + Component.translatable("item.caerula_arbor.voyage_of_gold.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_DURIN)
            if (mouseX > leftPos + 268 && mouseX < leftPos + 284 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.piglin_diary").getString() + ": " + Component.translatable("item.caerula_arbor.piglin_diary.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_TOPONYM)
            if (mouseX > leftPos + 292 && mouseX < leftPos + 308 && mouseY > topPos + 76 && mouseY < topPos + 92)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.toponym_textology").getString() + ": " + Component.translatable("item.caerula_arbor.toponym_textology.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_KETTLE)
            if (mouseX > leftPos + 4 && mouseX < leftPos + 20 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.kettle").getString() + ": " + Component.translatable("item.caerula_arbor.kettle.description_0").getString()), mouseX, mouseY);
        if (cap.relic_legend_CHITIN)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 52 && mouseY < topPos + 68)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.chitin_knife").getString() + ": " + Component.translatable("item.caerula_arbor.chitin_knife.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_ALLEY)
            if (mouseX > leftPos + 28 && mouseX < leftPos + 44 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.allay_sculpture").getString() + ": " + Component.translatable("item.caerula_arbor.allay_sculpture.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_BATBED)
            if (mouseX > leftPos + 52 && mouseX < leftPos + 68 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.bat_bed").getString() + ": " + Component.translatable("item.caerula_arbor.bat_bed.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_OMNIKEY)
            if (mouseX > leftPos + 124 && mouseX < leftPos + 140 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.omni_key").getString() + ": " + Component.translatable("item.caerula_arbor.omni_key.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_score)
            if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.score").getString() + ": " + Component.translatable("item.caerula_arbor.score.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_RESCISSION)
            if (mouseX > leftPos + 100 && mouseX < leftPos + 116 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.rescission").getString() + ": " + Component.translatable("item.caerula_arbor.rescission.description_0").getString()), mouseX, mouseY);
        if (cap.relic_util_STARE)
            if (mouseX > leftPos + 148 && mouseX < leftPos + 164 && mouseY > topPos + 100 && mouseY < topPos + 116)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.guardian_stare").getString() + ": " + Component.translatable("item.caerula_arbor.guardian_stare.description_0").getString()), mouseX, mouseY);
        if (cap.relic_hand_SWORD)
            if (mouseX > leftPos + 196 && mouseX < leftPos + 212 && mouseY > topPos + 28 && mouseY < topPos + 44)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.hand_sword").getString() + ": " + Component.translatable("item.caerula_arbor.hand_sword.description_0").getString()), mouseX, mouseY);
        if (cap.relic_cursed_HEART)
            if (mouseX > leftPos + 76 && mouseX < leftPos + 92 && mouseY > topPos + 196 && mouseY < topPos + 212)
                guiGraphics.renderTooltip(font, Component.literal(Component.translatable("item.caerula_arbor.caerula_heart").getString() + ": " + Component.translatable("item.caerula_arbor.caerula_heart.description_0").getString()), mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        var cap = entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables());

        guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/relic_bg.png"), this.leftPos, this.topPos, 0, 0, 328, 216, 328, 216);

        if (cap.relic_util_DURIN) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/durin_diary.png"), this.leftPos + 268, this.topPos + 76, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_ALLEY) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/stonealley.png"), this.leftPos + 28, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_BATBED) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/vampires_bed.png"), this.leftPos + 52, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_OMNIKEY) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/omnikey.png"), this.leftPos + 124, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_score) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/score.png"), this.leftPos + 76, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_RESCISSION) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/rescission.png"), this.leftPos + 100, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_util_STARE) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/guardianstare.png"), this.leftPos + 148, this.topPos + 100, 0, 0, 16, 16, 16, 16);
        }
        if (cap.relic_cursed_HEART) {
            guiGraphics.blit(new ResourceLocation("caerula_arbor:textures/screens/caerulaheart.png"), this.leftPos + 76, this.topPos + 196, 0, 0, 16, 16, 16, 16);
        }
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256 && this.minecraft != null) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable("gui.caerula_arbor.relic_showcase.label_relic_showcase"), 4, -12, -1, false);

        var cap = entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables());

        if (cap.relic_hand_ENGRAVE >= 0) {
            String msg = "" + Math.round((((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_ENGRAVE);

            guiGraphics.drawString(this.font, msg, 157, 36, -16777165, false);
            guiGraphics.drawString(this.font, msg, 156, 36, -1, false);
        }
        if (cap.relic_SURVIVOR >= 0) {
            String msg = "" + Math.round((((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR);
            guiGraphics.drawString(this.font, msg, 37, 60, -12829636, false);
            guiGraphics.drawString(this.font, msg, 36, 60, -1, false);
        }
    }

    @Override
    public void init() {
        super.init();
        button_return = new PlainTextButton(this.leftPos + 292, this.topPos + 204, 24, 20, Component.translatable("gui.caerula_arbor.relic_showcase.button_return"), e -> {
            CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(0, x, y, z));
            RelicShowcaseButtonMessage.handleButtonAction(entity, 0, x, y, z);
        }, this.font);
        guistate.put("button:button_return", button_return);
        this.addRenderableWidget(button_return);
        imagebutton_relic_crown = new ImageButton(this.leftPos + 4, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_relic_crown.png"), 16, 32, e -> {
            boolean result = false;
            if (entity != null) {
                result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_CROWN;
            }
            if (result) {
                CaerulaArborMod.PACKET_HANDLER.sendToServer(new RelicShowcaseButtonMessage(1, x, y, z));
                RelicShowcaseButtonMessage.handleButtonAction(entity, 1, x, y, z);
            }
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_king_CROWN;
                }
                this.visible = result;
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasXrystalProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_kingcrystal", imagebutton_kingcrystal);
        this.addRenderableWidget(imagebutton_kingcrystal);
        imagebutton_archfiend_articraft = new ImageButton(this.leftPos + 124, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archfiend_articraft.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasArtifactProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_archfiend_articraft", imagebutton_archfiend_articraft);
        this.addRenderableWidget(imagebutton_archfiend_articraft);
        imagebutton_archfi_flag = new ImageButton(this.leftPos + 148, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archfi_flag.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasFlagProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_archfi_flag", imagebutton_archfi_flag);
        this.addRenderableWidget(imagebutton_archfi_flag);
        imagebutton_archifi_bed = new ImageButton(this.leftPos + 172, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_archifi_bed.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasBedProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_archifi_bed", imagebutton_archifi_bed);
        this.addRenderableWidget(imagebutton_archifi_bed);
        imagebutton_royalfate = new ImageButton(this.leftPos + 196, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_royalfate.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasAhndSwipeProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_hand_swipe", imagebutton_hand_swipe);
        this.addRenderableWidget(imagebutton_hand_swipe);
        imagebutton_hand_curve = new ImageButton(this.leftPos + 148, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_curve.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_ENGRAVE >= 0;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_hand_curve", imagebutton_hand_curve);
        this.addRenderableWidget(imagebutton_hand_curve);
        imagebutton_hand_firework = new ImageButton(this.leftPos + 172, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_firework.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasHandFirewpedProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_hand_firework", imagebutton_hand_firework);
        this.addRenderableWidget(imagebutton_hand_firework);
        imagebutton_crimson_contarct_0 = new ImageButton(this.leftPos + 4, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_crimson_contarct_0.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasCrimcontraProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_crimson_contarct_0", imagebutton_crimson_contarct_0);
        this.addRenderableWidget(imagebutton_crimson_contarct_0);
        imagebutton_survivor_contarct = new ImageButton(this.leftPos + 28, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_survivor_contarct.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_SURVIVOR >= 0;
                }
                this.visible = result;
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasResearvhProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_cursed_research_0", imagebutton_cursed_research_0);
        this.addRenderableWidget(imagebutton_cursed_research_0);
        imagebutton_beef_can = new ImageButton(this.leftPos + 4, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_beef_can.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HasMeatcanProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_beef_can", imagebutton_beef_can);
        this.addRenderableWidget(imagebutton_beef_can);
        imagebutton_bowl_seagrass = new ImageButton(this.leftPos + 28, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_bowl_seagrass.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_SEAGRASS;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_bowl_seagrass", imagebutton_bowl_seagrass);
        this.addRenderableWidget(imagebutton_bowl_seagrass);
        imagebutton_orangestorm = new ImageButton(this.leftPos + 52, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_orangestorm.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_ORANGE;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_orangestorm", imagebutton_orangestorm);
        this.addRenderableWidget(imagebutton_orangestorm);
        imagebutton_coffee_candy = new ImageButton(this.leftPos + 76, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_coffee_candy.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_COFFEE;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_coffee_candy", imagebutton_coffee_candy);
        this.addRenderableWidget(imagebutton_coffee_candy);
        imagebutton_cherrycan = new ImageButton(this.leftPos + 124, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_cherrycan.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_BERRIES;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_cherrycan", imagebutton_cherrycan);
        this.addRenderableWidget(imagebutton_cherrycan);
        imagebutton_rainbow_candy = new ImageButton(this.leftPos + 100, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_rainbow_candy.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_util_RAINBOW;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_rainbow_candy", imagebutton_rainbow_candy);
        this.addRenderableWidget(imagebutton_rainbow_candy);
        imagebutton_boxcoffee = new ImageButton(this.leftPos + 148, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_boxcoffee.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).player_util_AROMATIC;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_boxcoffee", imagebutton_boxcoffee);
        this.addRenderableWidget(imagebutton_boxcoffee);
        imagebutton_musicboxsmall = new ImageButton(this.leftPos + 172, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_musicboxsmall.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_MUSICBOX;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_musicboxsmall", imagebutton_musicboxsmall);
        this.addRenderableWidget(imagebutton_musicboxsmall);
        imagebutton_originium_iris = new ImageButton(this.leftPos + 220, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_originium_iris.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_IRIS;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_originium_iris", imagebutton_originium_iris);
        this.addRenderableWidget(imagebutton_originium_iris);
        imagebutton_flute = new ImageButton(this.leftPos + 196, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_flute.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_FLUTE;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_flute", imagebutton_flute);
        this.addRenderableWidget(imagebutton_flute);
        imagebutton_voyageofsmall = new ImageButton(this.leftPos + 244, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_voyageofsmall.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_VOYGOLD;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_voyageofsmall", imagebutton_voyageofsmall);
        this.addRenderableWidget(imagebutton_voyageofsmall);
        imagebutton_location_name = new ImageButton(this.leftPos + 292, this.topPos + 76, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_location_name.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_TOPONYM;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_location_name", imagebutton_location_name);
        this.addRenderableWidget(imagebutton_location_name);
        imagebutton_kettle = new ImageButton(this.leftPos + 4, this.topPos + 100, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_kettle.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_util_KETTLE;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_kettle", imagebutton_kettle);
        this.addRenderableWidget(imagebutton_kettle);
        imagebutton_hand_sword = new ImageButton(this.leftPos + 196, this.topPos + 28, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_hand_sword.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_hand_SWORD;
                }
                this.visible = result;
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_hand_sword", imagebutton_hand_sword);
        this.addRenderableWidget(imagebutton_hand_sword);
        imagebutton_chitinknife = new ImageButton(this.leftPos + 52, this.topPos + 52, 16, 16, 0, 0, 16, new ResourceLocation("caerula_arbor:textures/screens/atlas/imagebutton_chitinknife.png"), 16, 32, e -> {
        }) {
            @Override
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                boolean result = false;
                if (entity != null) {
                    result = (((Entity) entity).getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).relic_legend_CHITIN;
                }
                this.visible = result;
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
            public void renderWidget(@NotNull GuiGraphics guiGraphics, int gx, int gy, float ticks) {
                this.visible = HansHandSpeedProcedure.execute(entity);
                super.renderWidget(guiGraphics, gx, gy, ticks);
            }
        };
        guistate.put("button:imagebutton_hand_speed", imagebutton_hand_speed);
        this.addRenderableWidget(imagebutton_hand_speed);
    }
}
