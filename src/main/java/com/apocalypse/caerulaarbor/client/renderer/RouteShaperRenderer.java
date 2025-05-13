
package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.model.RouteShaperModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.apocalypse.caerulaarbor.entity.RouteShaperEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class RouteShaperRenderer extends GeoEntityRenderer<RouteShaperEntity> {
	public RouteShaperRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RouteShaperModel());
		this.shadowRadius = 1f;
	}

	@Override
	public RenderType getRenderType(RouteShaperEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, RouteShaperEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 2f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(RouteShaperEntity entityLivingBaseIn) {
		return 0.0F;
	}
}
