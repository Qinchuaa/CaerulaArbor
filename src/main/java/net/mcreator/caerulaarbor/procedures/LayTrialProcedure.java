package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.init.CaerulaArborModBlocks;
import net.mcreator.caerulaarbor.entity.GuideAbyssalEntity;

public class LayTrialProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.5 && ModGriefSettingsProcedure.execute(world)) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 2));
			if ((entity instanceof GuideAbyssalEntity _datEntI ? _datEntI.getEntityData().get(GuideAbyssalEntity.DATA_laylimit) : 0) > 0) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).canBeReplaced() && !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CaerulaArborModBlocks.SEA_TRAIL_GROWN.get())) {
					if (CaerulaArborModBlocks.SEA_TRAIL_GROWN.get().defaultBlockState().canSurvive(world, BlockPos.containing(x, y, z))) {
						world.setBlock(BlockPos.containing(x, y, z), CaerulaArborModBlocks.SEA_TRAIL_GROWN.get().defaultBlockState(), 3);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.step")), SoundSource.NEUTRAL, 2, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_vein.step")), SoundSource.NEUTRAL, 2, 1, false);
							}
						}
						if (entity instanceof GuideAbyssalEntity _datEntSetI)
							_datEntSetI.getEntityData().set(GuideAbyssalEntity.DATA_laylimit, (int) ((entity instanceof GuideAbyssalEntity _datEntI ? _datEntI.getEntityData().get(GuideAbyssalEntity.DATA_laylimit) : 0) - 1));
					}
				}
			}
		}
	}
}
