package com.apocalypse.caerulaarbor.client.renderer;

import com.apocalypse.caerulaarbor.entity.ShellSeaRunnerEntity;
import com.apocalypse.caerulaarbor.client.model.entity.ShellSeaRunnerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShellSeaRunnerRenderer extends GeoEntityRenderer<ShellSeaRunnerEntity> {

    public ShellSeaRunnerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShellSeaRunnerModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(ShellSeaRunnerEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
