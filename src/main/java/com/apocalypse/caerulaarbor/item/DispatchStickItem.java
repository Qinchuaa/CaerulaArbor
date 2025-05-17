
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.procedures.MixedDispatchProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class DispatchStickItem extends Item {
    public DispatchStickItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.dispatch_stick.description_0"));
        list.add(Component.translatable("item.caerula_arbor.dispatch_stick.description_1"));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        MixedDispatchProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
        return ar;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        final Vec3 center = new Vec3(target.getX(), target.getY(), target.getZ());
        for (var entity : target.level()
                .getEntitiesOfClass(Mob.class, new AABB(center, center).inflate(64 / 2d), e -> true)
        ) {
            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("caerula_arbor:oceanoffspring")))
                    && target != entity
            ) {
                entity.setTarget(target);
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}
