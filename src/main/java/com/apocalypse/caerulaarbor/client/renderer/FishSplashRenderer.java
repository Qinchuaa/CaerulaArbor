package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.client.model.Modelfishbullet_Converted;
import com.apocalypse.caerulaarbor.entity.bullets.FishSplashEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FishSplashRenderer extends EntityRenderer<FishSplashEntity> {
	private static final ResourceLocation texture = new ResourceLocation("caerula_arbor:textures/entities/splashbullet.png");
	private final Modelfishbullet_Converted model;

	public FishSplashRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelfishbullet_Converted(context.bakeLayer(Modelfishbullet_Converted.LAYER_LOCATION));
	}

	@Override
	public void render(FishSplashEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(FishSplashEntity entity) {
		return texture;
	}
}
