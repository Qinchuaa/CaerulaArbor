
package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.model.ReaperFishModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.apocalypse.caerulaarbor.entity.layer.ReaperFishLayer;
import com.apocalypse.caerulaarbor.entity.ReaperFishEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ReaperFishRenderer extends GeoEntityRenderer<ReaperFishEntity> {
	public ReaperFishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ReaperFishModel());
		this.shadowRadius = 0.8f;
		this.addRenderLayer(new ReaperFishLayer(this));
	}

	@Override
	public RenderType getRenderType(ReaperFishEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, ReaperFishEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 2.5f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
