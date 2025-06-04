
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ToponymTextologyItem extends RelicItem {
    public ToponymTextologyItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
        list.add(Component.translatable("item.caerula_arbor.toponym_textology.description_0"));
        list.add(Component.translatable("item.caerula_arbor.toponym_textology.description_1"));
    }

    // TODO 优化这坨
    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        if (!itemstack.getOrCreateTag().getBoolean("Used")) {
            Relic.UTIL_TOPONYM.gainAndSync(entity);

            if ((LevelAccessor) world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.NEUTRAL, 2, 1);
                } else {
                    _level.playLocalSound(x, y, z, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.NEUTRAL, 2, 1, false);
                }
            }
            if ((LevelAccessor) world instanceof ServerLevel _level)
                _level.sendParticles(ParticleTypes.NAUTILUS, x, y, z, 72, 0.75, 1, 0.75, 1);
            itemstack.getOrCreateTag().putBoolean("Used", true);
            if (((LevelAccessor) world).isClientSide())
                Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);

            var cap = entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).orElse(new CaerulaArborModVariables.PlayerVariables());
            cap.shield = cap.shield + 6;
            cap.syncPlayerVariables(entity);
        }
        return ar;
    }
}
