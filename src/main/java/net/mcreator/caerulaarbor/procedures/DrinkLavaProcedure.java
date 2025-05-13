package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.caerulaarbor.init.CaerulaArborModItems;

public class DrinkLavaProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		itemstack.shrink(1);
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(CaerulaArborModItems.EMPTY_CAN.get()).copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.LAVA)), 27);
		entity.setSecondsOnFire(12);
	}
}
