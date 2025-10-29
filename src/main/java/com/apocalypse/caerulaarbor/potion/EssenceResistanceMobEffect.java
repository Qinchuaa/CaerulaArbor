package com.apocalypse.caerulaarbor.potion;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
// 可以把这个坐进金苹果和附魔金苹果 或者是药水等等 
public class EssenceResistanceMobEffect extends MobEffect {
    private static final ResourceLocation ICON = CaerulaArborMod.loc("textures/mob_effect/essenceresistance.png");

    public EssenceResistanceMobEffect() {
        super(MobEffectCategory.BENEFICIAL, MobEffects.DAMAGE_RESISTANCE.getColor());
    }

    @Override
    public List<net.minecraft.world.item.ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }

    @Override
    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            
            public ResourceLocation getIconTextureLocation() {
                return ICON;
            }

            @Override
            public boolean isVisibleInGui(MobEffectInstance instance) {
                return true;
            }

            @Override
            public boolean isVisibleInInventory(MobEffectInstance instance) {
                return true;
            }
        });
    }
}