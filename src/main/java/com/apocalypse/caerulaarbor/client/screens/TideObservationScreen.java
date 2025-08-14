package com.apocalypse.caerulaarbor.client.screens;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TideObservationScreen extends Screen {

    private static final ResourceLocation TEXTURE = CaerulaArborMod.loc("textures/gui/evolution.png");
    private static final ResourceLocation HOVER = CaerulaArborMod.loc("textures/gui/hover.png");
    private static final ResourceLocation BACKGROUND = CaerulaArborMod.loc("textures/gui/background.png");

    protected int imageWidth = 200;
    protected int imageHeight = 120;

    public TideObservationScreen() {
        super(Component.empty());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        this.renderScreen(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    protected void renderScreen(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        Vec2 offset = offset(pMouseX,pMouseY);

        //pGuiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        pGuiGraphics.blit(BACKGROUND,i,j,0,0,this.imageWidth,this.imageHeight,256,256);
        //按主美大人的意见区分了明暗↓
        pGuiGraphics.blit(HOVER, (int) (i+0.75 * offset.x), (int) (j+0.75 * offset.y),
                0,112,this.imageWidth,this.imageHeight,256,256);
        pGuiGraphics.blit(HOVER, (int) (i+offset.x), (int) (j+offset.y),
                0,0,this.imageWidth,this.imageHeight,256,256);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == Minecraft.getInstance().options.keyInventory.getKey().getValue()) {
            this.onClose();
            return true;
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    private Vec2 offset(int mX,int mY){
        int maxDist = 12;
        int centerX = this.width / 2,centerY = this.height / 2;
        Vec2 ofst = new Vec2((float) (mX - centerX) / 8, (float) (mY - centerY) / 8);
        if (ofst.length() > maxDist)return ofst.normalized().scale(maxDist);
        return ofst;
    }
}
