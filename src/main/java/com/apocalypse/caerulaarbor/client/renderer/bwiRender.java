package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.BishopImmortalSpawnerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;


public class bwiRender extends EntityRenderer<BishopImmortalSpawnerEntity> {

    public bwiRender(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0f;
    }

    @Override
    public void render(@NotNull BishopImmortalSpawnerEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BishopImmortalSpawnerEntity entity) {
        
        return new ResourceLocation("caerula_arbor", "textures/entity/blank.png");
    }
}