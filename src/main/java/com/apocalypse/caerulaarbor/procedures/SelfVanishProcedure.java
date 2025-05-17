package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.init.CaerulaArborModParticleTypes;
import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class SelfVanishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).chitin_knife_selected).getItem())) {
			if (world instanceof Level _level) {
				if (_level.isClientSide()) {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, (float) 3.2, 1, false);
				}
			}
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(ModMobEffects.TIDE_OF_CHITIN.get());
		}
		if ((entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CaerulaArborModVariables.PlayerVariables())).kingShowPtc) {
			world.addParticle((SimpleParticleType) (CaerulaArborModParticleTypes.KNIFEPTC.get()), (x + Mth.nextDouble(RandomSource.create(), -0.45, 0.45)), (y + Mth.nextDouble(RandomSource.create(), 0, entity.getBbHeight() * 0.8)),
					(z + Mth.nextDouble(RandomSource.create(), -0.45, 0.45)), Math.sin(Mth.nextDouble(RandomSource.create(), 0, 6.283)), 0.1, Math.cos(Mth.nextDouble(RandomSource.create(), 0, 6.283)));
		}
	}
}
