package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.MediatorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;

/**
 * No-op renderer for the invisible Mediator entity.
 */
public class MediatorRenderer<T extends MediatorEntity> extends EntityRenderer<T> {

    public MediatorRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0f;
    }

    @Override
    public void render(@NotNull T entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        // Intentionally do nothing: entity is invisible and has no visual representation
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        // Not used as render() is a no-op; return a dummy location.
        return new ResourceLocation("caerula_arbor", "textures/entity/blank.png");
    }
}