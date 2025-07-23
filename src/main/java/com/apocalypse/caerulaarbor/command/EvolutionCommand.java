package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.apocalypse.caerulaarbor.capability.map.MapVariablesHandler;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class EvolutionCommand {

    // TODO 补全命令提示
    public static LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal("evolution").requires(s -> s.hasPermission(2))
                .then(Commands.literal("upgrade").then(Commands.argument("strategy", LowerCamelCaseEnumArgument.enumArgument(MapVariables.StrategyType.class)).executes(arguments -> {
                    var strategy = arguments.getArgument("strategy", MapVariables.StrategyType.class);
                    var level = arguments.getSource().getUnsidedLevel();
                    if (MapVariablesHandler.upgradeStrategy(strategy, level)) {
                        arguments.getSource().sendSuccess(() -> Component.literal("upgrade " + strategy), true);
                        return 0;
                    }
                    arguments.getSource().sendFailure(Component.literal("failed to upgrade " + strategy));
                    return 0;
                })))
                .then(Commands.literal("set").then(Commands.argument("strategy", LowerCamelCaseEnumArgument.enumArgument(MapVariables.StrategyType.class)).then(Commands.argument("level", IntegerArgumentType.integer(0, 4)).executes(arguments -> {
                    var strategy = arguments.getArgument("strategy", MapVariables.StrategyType.class);
                    int strategyLevel = IntegerArgumentType.getInteger(arguments, "level");
                    var level = arguments.getSource().getUnsidedLevel();
                    if (MapVariablesHandler.setStrategyLevel(strategy, strategyLevel, level)) {
                        arguments.getSource().sendSuccess(() -> Component.literal("set level of " + strategy + " to " + strategyLevel), true);
                        return 0;
                    }
                    arguments.getSource().sendFailure(Component.literal("failed to set level of " + strategy));
                    return 0;
                }))));
    }
}
