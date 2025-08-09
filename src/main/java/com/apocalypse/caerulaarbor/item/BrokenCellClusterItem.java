package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.sanity.SIHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BrokenCellClusterItem extends Item {
    public BrokenCellClusterItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3f).meat().build()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.broken_cell_cluster.description_0"));
        list.add(Component.translatable("item.caerula_arbor.broken_cell_cluster.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        SIHelper.causeSanityInjury(entity, 75);

        if (world instanceof ServerLevel server) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 0));
            server.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 1), z, 32, 0.5, 1, 0.5, 0.1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
