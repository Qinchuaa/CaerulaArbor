
package com.apocalypse.caerulaarbor.item;

import com.apocalypse.caerulaarbor.capability.Relic;
import com.apocalypse.caerulaarbor.item.relic.RelicItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class RedstoneIrisFlowerItem extends RelicItem {
	public RedstoneIrisFlowerItem() {
		super(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC));
	}

	@Override
	public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(@NotNull ItemStack itemstack) {
		return 20;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack itemstack, Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.caerula_arbor.redstone_iris_flower.description_0"));
		list.add(Component.translatable("item.caerula_arbor.redstone_iris_flower.description_1"));
	}

	@Override
	@ParametersAreNonnullByDefault
	public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		return ar;
	}

	// TODO 优化这坨
	@Override
	@ParametersAreNonnullByDefault
	public @NotNull ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		for (int index0 = 0; index0 < 16; index0++) {
			if ((LevelAccessor) world instanceof ServerLevel _level)
				_level.addFreshEntity(new ExperienceOrb(_level, (x + Mth.nextDouble(RandomSource.create(), -1, 1)), (y + Mth.nextDouble(RandomSource.create(), 0.6, 0.75)), (z + Mth.nextDouble(RandomSource.create(), -1, 1)), 4));
		}
		Relic.UTIL_IRIS.gainAndSync(entity);
		if ((LevelAccessor) world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1);
			} else {
				_level.playLocalSound(x, y, z, SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 2, 1, false);
			}
		}
		if ((LevelAccessor) world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 72, 1, 1, 1, 1);
		itemstack.shrink(1);

		return retval;
	}
}
