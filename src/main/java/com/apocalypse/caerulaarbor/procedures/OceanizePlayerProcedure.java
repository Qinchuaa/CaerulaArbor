package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.network.CaerulaArborModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class OceanizePlayerProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amplifier) {
        if (entity == null)
            return;
        double ampli;
        if (entity instanceof Player) {
            ampli = amplifier;
            if (amplifier > 2) {
                ampli = 2;
            }
            double finalAmpli = ampli;
            entity.getCapability(CaerulaArborModVariables.PLAYER_VARIABLES_CAPABILITY).ifPresent(capability -> {
                capability.player_oceanization = finalAmpli + 1;
                capability.syncPlayerVariables(entity);
            });
            DeductPlayerSanityProcedure.execute(entity, 750 * (amplifier + 1));
            if (world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")), SoundSource.PLAYERS, 2, 1);
                } else {
                    _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")), SoundSource.PLAYERS, 2, 1, false);
                }
            }
        }
    }
}
