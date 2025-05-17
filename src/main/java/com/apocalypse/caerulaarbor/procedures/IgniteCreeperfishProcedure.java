package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.entity.CreeperFishEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class IgniteCreeperfishProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return InteractionResult.PASS;
		if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.primed")), SoundSource.HOSTILE, 2, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.primed")), SoundSource.HOSTILE, 2, 1, false);
				}
			}
			if (entity instanceof CreeperFishEntity) {
				((CreeperFishEntity) entity).setAnimation("animation.explosivefish.jump");
			}
			new Object() {
				void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
					RangedSanityAttackProcedure.execute(world, x, y, z, entity, entity);
					final int tick2 = ticks;
					CaerulaArborMod.queueServerWork(tick2, () -> {
						if (timedlooptotal > timedloopiterator + 1) {
							timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
						}
					});
				}
			}.timedLoop(0, 5, 4);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
