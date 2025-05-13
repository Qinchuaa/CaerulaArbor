package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.caerulaarbor.init.CaerulaArborModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ItembreakFuncProcedure {
	@SubscribeEvent
	public static void onItemDestroyed(PlayerDestroyItemEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getOriginal());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		execute(null, world, x, y, z, itemstack);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		if (itemstack.getItem() == CaerulaArborModItems.THE_SPEAR.get()) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(CaerulaArborModItems.KINGS_SPEAR.get()));
				entityToSpawn.setPickUpDelay(10);
				entityToSpawn.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn);
			}
		}
		if (itemstack.getItem() == CaerulaArborModItems.WEARABLE_CHEST_CHESTPLATE.get()) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(CaerulaArborModItems.KINGS_ARMOUR.get()));
				entityToSpawn.setPickUpDelay(10);
				entityToSpawn.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn);
			}
		}
		if (itemstack.getItem() == CaerulaArborModItems.WEARABLE_CROWN_HELMET.get()) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(CaerulaArborModItems.RELIC_CROWN.get()));
				entityToSpawn.setPickUpDelay(10);
				entityToSpawn.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn);
			}
		}
		if (itemstack.getItem() == CaerulaArborModItems.LEGENDARY_SPEAR.get()) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(CaerulaArborModItems.CHITIN_KNIFE.get()));
				entityToSpawn.setPickUpDelay(10);
				entityToSpawn.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn);
			}
		}
	}
}
