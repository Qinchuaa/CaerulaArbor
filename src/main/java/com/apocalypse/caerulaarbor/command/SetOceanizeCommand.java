
package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.procedures.SwitchOceanizeProcedure;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SetOceanizeCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("caerula_arbor:setOceanization").requires(s -> s.hasPermission(2)).then(Commands.argument("name", EntityArgument.player()).then(Commands.argument("state", DoubleArgumentType.doubleArg(0, 3)).executes(arguments -> {
                    SwitchOceanizeProcedure.execute(arguments);
                    return 0;
                }))));
    }
}
