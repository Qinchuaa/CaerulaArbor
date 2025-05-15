package com.apocalypse.caerulaarbor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import com.apocalypse.caerulaarbor.init.ModMobEffects;
import com.apocalypse.caerulaarbor.entity.FakeOffspringEntity;

public class FakeeggInitProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ModMobEffects.SELF_KILL.get(), 9999, 0, false, false));
		if (entity instanceof FakeOffspringEntity _datEntSetI)
			_datEntSetI.getEntityData().set(FakeOffspringEntity.DATA_dx, Mth.nextInt(RandomSource.create(), -50, 50));
		if (entity instanceof FakeOffspringEntity _datEntSetI)
			_datEntSetI.getEntityData().set(FakeOffspringEntity.DATA_dz, Mth.nextInt(RandomSource.create(), -50, 50));
	}
}
