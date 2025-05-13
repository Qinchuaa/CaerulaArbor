
package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.model.PregnantFishModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.apocalypse.caerulaarbor.entity.layer.PregnantFishLayer;
import com.apocalypse.caerulaarbor.entity.PregnantFishEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class PregnantFishRenderer extends GeoEntityRenderer<PregnantFishEntity> {
	public PregnantFishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new PregnantFishModel());
		this.shadowRadius = 0.6f;
		this.addRenderLayer(new PregnantFishLayer(this));
	}

	@Override
	public RenderType getRenderType(PregnantFishEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, PregnantFishEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
