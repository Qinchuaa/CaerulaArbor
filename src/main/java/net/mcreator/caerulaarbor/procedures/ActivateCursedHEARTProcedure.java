package net.mcreator.caerulaarbor.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.caerulaarbor.network.CaerulaArborModVariables;

public class ActivateCursedHEARTProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		if (!CaerulaArborModVariables.MapVariables.get(world).silence_enabled) {
			CaerulaArborModVariables.MapVariables.get(world).silence_enabled = true;
			CaerulaArborModVariables.MapVariables.get(world).syncData(world);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.MASTER, 4, (float) 0.85);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.MASTER, 4, (float) 0.85, false);
				}
			}
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_14").getString())), false);
			itemstack.shrink(1);
		}
	}
}
