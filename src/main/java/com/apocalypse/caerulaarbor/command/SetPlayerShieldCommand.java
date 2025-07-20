
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SetPlayerShieldCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("caerula_arbor:setShield").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.player()).then(Commands.argument("shield", DoubleArgumentType.doubleArg(0, 999)).executes(arguments -> {
            Level world = arguments.getSource().getUnsidedLevel();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null && world instanceof ServerLevel server) {
                entity = FakePlayerFactory.getMinecraft(server);
            }

            Entity finalEntity = entity;
            entity.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(cap -> {
                cap.shield = DoubleArgumentType.getDouble(arguments, "shield");
                cap.syncPlayerVariables(finalEntity);
            });

            return 0;
        }))));
    }
}
