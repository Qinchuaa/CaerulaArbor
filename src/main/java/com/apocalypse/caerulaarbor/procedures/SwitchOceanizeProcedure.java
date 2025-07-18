package com.apocalypse.caerulaarbor.procedures;

import com.apocalypse.caerulaarbor.capability.ModCapabilities;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;

public class SwitchOceanizeProcedure {
    public static void execute(CommandContext<CommandSourceStack> arguments) throws CommandSyntaxException {
        var entity = EntityArgument.getEntity(arguments, "name");

        entity.getCapability(ModCapabilities.PLAYER_VARIABLE).ifPresent(capability -> {
            capability.player_oceanization = IntegerArgumentType.getInteger(arguments, "state");
            capability.syncPlayerVariables(entity);
        });
    }
}
