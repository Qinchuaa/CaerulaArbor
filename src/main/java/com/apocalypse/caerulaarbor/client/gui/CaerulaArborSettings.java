package com.apocalypse.caerulaarbor.client.gui;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.config.server.SanityConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

// 写了一个简单的模组配置页 今后添加更多的配置项
public class CaerulaArborSettings extends Screen {
    private final Screen parent;
    private Button creativeSanityButton;
    private Button saveButton;
    private int panelW;
    private int panelH;
    private int panelX;
    private int panelY;
    private int toggleX;
    private int toggleY;
    private int toggleWidth;
    private final int btnHeight = 20;
    private Boolean pendingValue;
    private Boolean lastSavedValue;

    public CaerulaArborSettings(Screen parent) {
        super(Component.translatable("gui.caerula_arbor.settings.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int margin = 10;
        int spacing = 12;

        this.panelW = Mth.clamp((int) (this.width * 0.7), 240, Math.max(240, this.width - 2 * margin - 20));
        this.panelH = Mth.clamp((int) (this.height * 0.25), 60, Math.max(60, this.height - 2 * margin - 80));
        this.panelX = (this.width - this.panelW) / 2;
        this.panelY = (this.height - this.panelH) / 2 - 10; // 略偏上，使底部按钮区域更充裕

        // 加入远程服务器时只读
        boolean isRemoteServer = this.minecraft != null && this.minecraft.player != null && this.minecraft.getSingleplayerServer() == null;
        boolean editable = !isRemoteServer;
        if (this.lastSavedValue == null) {
            this.lastSavedValue = safeGetBoolean();
        }
        if (this.pendingValue == null) {
            this.pendingValue = this.lastSavedValue;
        }
        Component toggleText = buildToggleText(this.pendingValue);

        // 在子布局内右对齐可更改部分（开关按钮）
        this.toggleWidth = Mth.clamp(this.font.width(toggleText) + 24, 90, Math.max(90, this.panelW - 20));
        this.toggleX = Mth.clamp(this.panelX + this.panelW - margin - this.toggleWidth, margin, Math.max(margin, this.width - this.toggleWidth - margin));
        this.toggleY = Mth.clamp(this.panelY + (this.panelH - this.btnHeight) / 2, 30, Math.max(30, this.height - 80));

        creativeSanityButton = Button.builder(toggleText, e -> {
            if (!editable) return;
            try {
                boolean nv = !this.pendingValue;
                this.pendingValue = nv;
                creativeSanityButton.setMessage(buildToggleText(nv));
                updateSaveButtonState();
            } catch (Throwable ex) {
            }
        }).bounds(this.toggleX, this.toggleY, this.toggleWidth, this.btnHeight).build();
        creativeSanityButton.active = editable;
        this.addRenderableWidget(creativeSanityButton);

        int buttonWidth = 80;
        int groupW = buttonWidth * 2 + spacing;
        int groupX = (this.width - groupW) / 2;
        int bottomY = this.height - margin - this.btnHeight;

        this.saveButton = Button.builder(Component.translatable("gui.caerula_arbor.settings.save"), e -> {
            if (!editable) return;
            try {
                if (SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY != null && this.pendingValue != null) {
                    SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY.set(this.pendingValue);
                }
            } catch (Throwable ex) {
            }
            this.lastSavedValue = this.pendingValue;
            updateSaveButtonState();
        }).bounds(groupX, bottomY, buttonWidth, this.btnHeight).build();
        this.saveButton.active = editable;
        updateSaveButtonState();
        this.addRenderableWidget(this.saveButton);

        var exitButton = Button.builder(Component.translatable("gui.caerula_arbor.settings.exit"), e -> this.onClose())
                .bounds(groupX + buttonWidth + spacing, bottomY, buttonWidth, this.btnHeight)
                .build();
        this.addRenderableWidget(exitButton);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        //子布局背景
        int bgColor = 0xAA000000; 
        int borderColor = 0x55FFFFFF; 
        guiGraphics.fill(this.panelX, this.panelY, this.panelX + this.panelW, this.panelY + this.panelH, bgColor);
        guiGraphics.renderOutline(this.panelX, this.panelY, this.panelW, this.panelH, borderColor);

        
        Component desc = Component.translatable("gui.caerula_arbor.settings.creative_sanity_injury");
        int margin = 10;
        int labelX = this.panelX + margin;
        int labelY = this.panelY + (this.panelH - this.font.lineHeight) / 2;
        guiGraphics.drawString(this.font, desc, labelX, labelY, 0xFFFFFF, false);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void resize(@NotNull Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);
        this.init();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.renderables.isEmpty()) {
            this.init();
        }
    }

    private void updateSaveButtonState() {
        boolean isRemoteServer = this.minecraft != null && this.minecraft.player != null && this.minecraft.getSingleplayerServer() == null;
        boolean editable = !isRemoteServer;
        boolean changed = this.pendingValue != null && this.lastSavedValue != null && this.pendingValue != this.lastSavedValue;
        if (this.saveButton != null) {
            this.saveButton.active = editable && changed;
        }
    }

    private boolean safeGetBoolean() {
        try {
            if (SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY != null) {
                return SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY.get();
            }
        } catch (Throwable ex) {
            CaerulaArborMod.LOGGER.error("Read creative sanity injury failed", ex);
        }
        return false;
    }

    private Component buildToggleText(boolean value) {
        return Component.translatable(value ? "options.on" : "options.off");
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(parent);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}