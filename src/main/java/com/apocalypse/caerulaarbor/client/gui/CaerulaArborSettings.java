package com.apocalypse.caerulaarbor.client.gui;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.config.server.SanityConfig;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
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
    private Button playerPalsyButton;
    private Button saveButton;
    private int panelW;
    private int panelH;
    private int panelX;
    private int panelY;
    private int toggleX;
    private int toggleY;
    private int toggle2Y;
    private int toggleWidth;
    private final int btnHeight = 20;
    private Boolean pendingValue;
    private Boolean pendingPalsyValue;
    private Boolean lastSavedValue;
    private Boolean lastSavedPalsyValue;

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
            this.lastSavedValue = safeGetCreative();
        }
        if (this.pendingValue == null) {
            this.pendingValue = this.lastSavedValue;
        }
        if (this.lastSavedPalsyValue == null) {
            this.lastSavedPalsyValue = safeGetPalsy();
        }
        if (this.pendingPalsyValue == null) {
            this.pendingPalsyValue = this.lastSavedPalsyValue;
        }

        Component toggleTextCreative = buildToggleText(this.pendingValue);
        Component toggleTextPalsy = buildToggleText(this.pendingPalsyValue);

        // 右对齐
        this.toggleWidth = Mth.clamp(Math.max(this.font.width(toggleTextCreative), this.font.width(toggleTextPalsy)) + 24, 90, Math.max(90, this.panelW - 20));
        this.toggleX = Mth.clamp(this.panelX + this.panelW - margin - this.toggleWidth, margin, Math.max(margin, this.width - this.toggleWidth - margin));
        this.toggleY = Mth.clamp(this.panelY + (this.panelH - this.btnHeight) / 2, 30, Math.max(30, this.height - 80));
        this.toggle2Y = Mth.clamp(this.toggleY + this.btnHeight + spacing, 30, Math.max(30, this.height - 80));

        creativeSanityButton = Button.builder(toggleTextCreative, e -> {
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

        playerPalsyButton = Button.builder(toggleTextPalsy, e -> {
            if (!editable) return;
            try {
                boolean nv = !this.pendingPalsyValue;
                this.pendingPalsyValue = nv;
                playerPalsyButton.setMessage(buildToggleText(nv));
                updateSaveButtonState();
            } catch (Throwable ex) {
            }
        }).bounds(this.toggleX, this.toggle2Y, this.toggleWidth, this.btnHeight).build();
        playerPalsyButton.active = editable;
        this.addRenderableWidget(playerPalsyButton);

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
                if (SanityConfig.PLAYER_BREAK_USES_PALSY != null && this.pendingPalsyValue != null) {
                    SanityConfig.PLAYER_BREAK_USES_PALSY.set(this.pendingPalsyValue);
                }
                // 显式写回到serverconfig文件
                persistServerConfig();
            } catch (Throwable ex) {
            }
            this.lastSavedValue = this.pendingValue;
            this.lastSavedPalsyValue = this.pendingPalsyValue;
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
        Component desc2 = Component.translatable("gui.caerula_arbor.settings.player_break_uses_palsy");
        int margin = 10;
        int labelX = this.panelX + margin;
        int labelY1 = this.toggleY + (this.btnHeight - this.font.lineHeight) / 2;
        int labelY2 = this.toggle2Y + (this.btnHeight - this.font.lineHeight) / 2;
        guiGraphics.drawString(this.font, desc, labelX, labelY1, 0xFFFFFF, false);
        guiGraphics.drawString(this.font, desc2, labelX, labelY2, 0xFFFFFF, false);
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
        boolean changed1 = this.pendingValue != null && this.lastSavedValue != null && this.pendingValue != this.lastSavedValue;
        boolean changed2 = this.pendingPalsyValue != null && this.lastSavedPalsyValue != null && this.pendingPalsyValue != this.lastSavedPalsyValue;
        boolean changed = changed1 || changed2;
        if (this.saveButton != null) {
            this.saveButton.active = editable && changed;
        }
    }

    private boolean safeGetCreative() {
        try {
            if (SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY != null) {
                return SanityConfig.CREATIVE_RECEIVE_SANITY_INJURY.get();
            }
        } catch (Throwable ex) {
            CaerulaArborMod.LOGGER.error("Read creative sanity injury failed", ex);
        }
        return false;
    }

    private boolean safeGetPalsy() {
        try {
            if (SanityConfig.PLAYER_BREAK_USES_PALSY != null) {
                return SanityConfig.PLAYER_BREAK_USES_PALSY.get();
            }
        } catch (Throwable ex) {
            CaerulaArborMod.LOGGER.error("Read player break uses palsy failed", ex);
        }
        return false;
    }

    private Component buildToggleText(boolean value) {
        return Component.translatable(value ? "options.on" : "options.off");
    }

    /**
     * 显式保存当前模组的 SERVER 配置到文件。
     * Forge 会维护所有 ModConfig 的集合，这里过滤出本模组的 SERVER 类型并调用 save。
     */
    private void persistServerConfig() {
        try {
            Object tracker = ConfigTracker.INSTANCE;
            java.lang.reflect.Field field = tracker.getClass().getDeclaredField("configsByMod");
            field.setAccessible(true);
            Object mapObj = field.get(tracker);
            if (mapObj instanceof java.util.Map<?, ?> map) {
                Object setObj = map.get(CaerulaArborMod.MODID);
                if (setObj instanceof Iterable<?> iterable) {
                    for (Object o : iterable) {
                        if (o instanceof ModConfig cfg && cfg.getType() == ModConfig.Type.SERVER) {
                            cfg.save();
                        }
                    }
                }
            }
        } catch (Throwable ex) {
            CaerulaArborMod.LOGGER.error("Persist server config failed", ex);
        }
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