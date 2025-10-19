
package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.client.model.entity.TidelinkedImmortalModel;
import com.apocalypse.caerulaarbor.entity.TidelinkedImmortalEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class TidelinkedImmortalRenderer extends GeoEntityRenderer<TidelinkedImmortalEntity> {
	public TidelinkedImmortalRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TidelinkedImmortalModel());
		this.shadowRadius = 0.8f;
	}

	@Override
	public RenderType getRenderType(TidelinkedImmortalEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, TidelinkedImmortalEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 1.2f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(TidelinkedImmortalEntity entityLivingBaseIn) {
		return 0.0F;
	}
}
