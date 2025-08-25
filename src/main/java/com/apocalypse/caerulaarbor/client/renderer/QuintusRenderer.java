
package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.client.layer.QuintusLayer;
import com.apocalypse.caerulaarbor.client.model.entity.QuintusModel;
import com.apocalypse.caerulaarbor.entity.QuintusEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class QuintusRenderer extends GeoEntityRenderer<QuintusEntity> {
	public QuintusRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new QuintusModel());
		this.shadowRadius = 1.5f;
		this.addRenderLayer(new QuintusLayer(this));
	}

	@Override
	public RenderType getRenderType(QuintusEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, QuintusEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1.5f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(QuintusEntity entityLivingBaseIn) {
		return 0.0F;
	}
}
