package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.client.model.Modelfakebullet;
import com.apocalypse.caerulaarbor.entity.bullets.FakerggShootEntity;
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

public class FakerggShootRenderer extends EntityRenderer<FakerggShootEntity> {
	private static final ResourceLocation texture = new ResourceLocation("caerula_arbor:textures/entities/fakebullet.png");
	private final Modelfakebullet model;

	public FakerggShootRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelfakebullet(context.bakeLayer(Modelfakebullet.LAYER_LOCATION));
	}

	@Override
	public void render(FakerggShootEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(FakerggShootEntity entity) {
		return texture;
	}
}
