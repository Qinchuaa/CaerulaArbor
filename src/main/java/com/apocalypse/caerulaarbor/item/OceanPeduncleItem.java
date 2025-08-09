
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.sanity.SIHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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

public class OceanPeduncleItem extends Item {
    public OceanPeduncleItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(3).saturationMod(1f).meat().build()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.ocean_peduncle.description_0"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        for (int i = 0; i < 4; i++) {
            CaerulaArborMod.queueServerWork(i * 10, () -> {
                SIHelper.causeSanityInjury(entity, 20);

                if (world instanceof ServerLevel _level) {
                    _level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + 0.9), z, 16, 0.55, 1, 0.55, 0.1);
                    _level.playLocalSound(x, y, z, SoundEvents.SLIME_SQUISH_SMALL, SoundSource.PLAYERS, 1, 1, false);
                }
                entity.push((Mth.nextDouble(RandomSource.create(), -0.1, 0.1)), (Mth.nextDouble(RandomSource.create(), 0, 0.1)), (Mth.nextDouble(RandomSource.create(), -0.1, 0.1)));
            });
        }

        return super.finishUsingItem(itemstack, world, entity);
    }
}
