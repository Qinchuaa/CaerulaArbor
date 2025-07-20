
package com.apocalypse.caerulaarbor.item.relic.rare;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UniversalKeyItem extends RelicItem {
    public UniversalKeyItem() {
        super(new Item.Properties().durability(64).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.universal_key.description_0").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("item.caerula_arbor.universal_key.description_1").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable Relic getRelic() {
        return Relic.UTIL_OMNIKEY;
    }

    @Override
    public boolean checkUsedMark() {
        return true;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        var world = context.getLevel();
        var pos = context.getClickedPos();
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        BlockState blockstate = world.getBlockState(pos);
        ItemStack itemstack = context.getItemInHand();
        if (blockstate.getBlock() == Blocks.IRON_DOOR || blockstate.getBlock() == Blocks.IRON_TRAPDOOR) {
            if (!(blockstate.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp5 && blockstate.getValue(_getbp5))) {
                {
                    BlockState _bs = world.getBlockState(pos);
                    if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
                        world.setBlock(pos, _bs.setValue(_booleanProp, true), 3);
                }
                world.scheduleTick(pos, world.getBlockState(pos).getBlock(), 22);

                if (!world.isClientSide()) {
                    world.playSound(null, pos, SoundEvents.CHAIN_STEP, SoundSource.NEUTRAL, 1, 1);
                } else {
                    world.playLocalSound(x, y, z, SoundEvents.CHAIN_STEP, SoundSource.NEUTRAL, 1, 1, false);
                }

                CaerulaArborMod.queueServerWork(20, () -> {

                    BlockState _bs = world.getBlockState(pos);
                    if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
                        world.setBlock(pos, _bs.setValue(_booleanProp, false), 3);

                });

                if (itemstack.hurt(1, RandomSource.create(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }

            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
