package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;
import net.mcreator.caerulaarbor.CaerulaArborMod;

public class KnifeGainProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			ItemStack _setval = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
			entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.chitin_knife_selected = _setval.copy();
				capability.syncPlayerVariables(entity);
			});
		}
		CaerulaArborMod.queueServerWork(5, () -> {
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
		});
	}
}
