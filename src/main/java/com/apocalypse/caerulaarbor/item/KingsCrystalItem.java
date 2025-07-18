
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.init.ModBlocks;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KingsCrystalItem extends RelicItem {
    public KingsCrystalItem() {
        super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.kings_crystal.description_0"));
        list.add(Component.translatable("item.caerula_arbor.kings_crystal.description_1"));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.KING_CRYSTAL;
    }

    @Override
    public @NotNull ParticleOptions getGainParticle() {
        return ParticleTypes.ENCHANTED_HIT;
    }

    @Override
    public @NotNull SoundEvent getGainSound() {
        return SoundEvents.TOTEM_USE;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);

        Entity entity = context.getPlayer();
        if (entity == null) return InteractionResult.PASS;

        var world = context.getLevel();
        var pos = context.getClickedPos();
        var state = context.getLevel().getBlockState(pos);

        var stack = context.getItemInHand();

        if (state.getBlock() == Blocks.DEEPSLATE_BRICK_SLAB) {
            world.setBlock(pos, ModBlocks.BLOCK_CRYSTAL.get().defaultBlockState(), 3);

            Direction direction = entity.getDirection().getOpposite();
            world.setBlock(pos, state.setValue(BlockStateProperties.FACING, direction), 3);
            stack.shrink(1);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
