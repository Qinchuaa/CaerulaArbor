package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.BasinSeaReaperEntity;
import com.apocalypse.caerulaarbor.client.layer.BasinSeaReaperLayer;
import com.apocalypse.caerulaarbor.client.model.entity.BasinSeaReaperModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BasinSeaReaperRenderer extends GeoEntityRenderer<BasinSeaReaperEntity> {

    public BasinSeaReaperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BasinSeaReaperModel());
        this.shadowRadius = 0.8f;
        this.addRenderLayer(new BasinSeaReaperLayer(this));
    }

    @Override
    public RenderType getRenderType(BasinSeaReaperEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, BasinSeaReaperEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
                          float blue, float alpha) {
        float scale = 2.5f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
