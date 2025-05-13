
package net.mcreator.caerulaarbor.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.caerulaarbor.entity.model.CrackerAbyssalModel;
import net.mcreator.caerulaarbor.entity.CrackerAbyssalEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CrackerAbyssalRenderer extends GeoEntityRenderer<CrackerAbyssalEntity> {
	public CrackerAbyssalRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CrackerAbyssalModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(CrackerAbyssalEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, CrackerAbyssalEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1.5f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
