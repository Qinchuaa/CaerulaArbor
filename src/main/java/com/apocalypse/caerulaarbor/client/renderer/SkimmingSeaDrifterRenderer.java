package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.SkimmingSeaDrifterEntity;
import com.apocalypse.caerulaarbor.client.layer.SkimmingSeaDrifterLayer;
import com.apocalypse.caerulaarbor.client.model.entity.SkimmingSeaDrifterModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkimmingSeaDrifterRenderer extends GeoEntityRenderer<SkimmingSeaDrifterEntity> {

    public SkimmingSeaDrifterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkimmingSeaDrifterModel());
        this.shadowRadius = 0.5f;
        this.addRenderLayer(new SkimmingSeaDrifterLayer(this));
    }

    @Override
    public RenderType getRenderType(SkimmingSeaDrifterEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, SkimmingSeaDrifterEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
                          float blue, float alpha) {
        float scale = 1.2f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected float getDeathMaxRotation(SkimmingSeaDrifterEntity entityLivingBaseIn) {
        return 0.0F;
    }
}
