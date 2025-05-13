package net.mcreator.caerulaarbor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.caerulaarbor.CaerulaArborMod;

public class WoodenSwordAtkProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double dam = 0;
		dam = 40 + 8 * itemstack.getEnchantmentLevel(Enchantments.SHARPNESS);
		DeductPlayerSanityProcedure.execute(entity, dam);
		new Object() {
			void timedLoop(int timedloopiterator, int timedlooptotal, int ticks) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, (y + entity.getBbHeight() * 0.5), z, 8, 1.2, 1.5, 1.2, 0.1);
				final int tick2 = ticks;
				CaerulaArborMod.queueServerWork(tick2, () -> {
					if (timedlooptotal > timedloopiterator + 1) {
						timedLoop(timedloopiterator + 1, timedlooptotal, tick2);
					}
				});
			}
		}.timedLoop(0, 5, 1);
	}
}
