
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.procedures.KeyUseProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class OmniKeyItem extends Item {
	public OmniKeyItem() {
		super(new Item.Properties().durability(64).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.caerula_arbor.omni_key.description_0"));
		list.add(Component.translatable("item.caerula_arbor.omni_key.description_1"));
	}

	@Override
	@ParametersAreNonnullByDefault
	public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		ItemStack itemstack = ar.getObject();

		if (!itemstack.getOrCreateTag().getBoolean("Used")) {
			Relic.UTIL_OMNIKEY.gainAndSync(entity);

			if ((Entity) entity instanceof Player _player)
				_player.giveExperienceLevels(3);
			if ((LevelAccessor) world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1);
				} else {
					_level.playLocalSound(x, y, z, SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1, false);
				}
			}
			if ((LevelAccessor) world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 72, 1, 1, 1, 1);
			itemstack.getOrCreateTag().putBoolean("Used", true);
		}
		return ar;
	}

	@Override
	public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
		super.useOn(context);
		return KeyUseProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()), context.getItemInHand());
	}
}
