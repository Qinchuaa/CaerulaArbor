
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.procedures.DeductPlayerSanityProcedure;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class TrailCakePieceItem extends Item {
    public TrailCakePieceItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.2f).build()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        DeductPlayerSanityProcedure.execute(entity, 150);

        if (world instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 0.7), z, 32, 0.5, 1.5, 0.5, 1);
        }
        return super.finishUsingItem(itemstack, world, entity);
    }
}
