package com.apocalypse.caerulaarbor.command;

import com.apocalypse.caerulaarbor.capability.map.MapVariables;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class EvolutionCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> get() {
        return Commands.literal("evolution").requires(s -> s.hasPermission(2))
                .then(Commands.literal("upgrade").then(Commands.argument("strategy", LowerEnumArgument.enumArgument(MapVariables.StrategyType.class)).executes(arguments -> {
                    var strategy = arguments.getArgument("strategy", MapVariables.StrategyType.class);
                    arguments.getSource().sendSuccess(() -> Component.literal("upgrade " + strategy), true);
                    return 0;
                })))
                .then(Commands.literal("set").then(Commands.argument("strategy", LowerEnumArgument.enumArgument(MapVariables.StrategyType.class)).then(Commands.argument("level", DoubleArgumentType.doubleArg(0, 4))).executes(arguments -> {
                    var strategy = StringArgumentType.getString(arguments, "strategy");
                    System.out.println("set " + strategy + " to " + DoubleArgumentType.getDouble(arguments, "level"));
                    return 1;
                })));
    }

//		event.getDispatcher().register(Commands.literal("caerula_arbor:evolution").requires(s -> s.hasPermission(2)).then(Commands.literal("grow").then(Commands.literal("update").executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			UpgradeGrowProcedure.execute(world);
//			return 0;
//		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			EvoLvlGrowProcedure.execute(world, x, y, z, arguments);
//			return 0;
//		}))).then(Commands.literal("breed").then(Commands.literal("update").executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			UpgradeBreedProcedure.execute(world);
//			return 0;
//		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			EvoLvlBreedProcedure.execute(world, x, y, z, arguments);
//			return 0;
//		}))).then(Commands.literal("migration").then(Commands.literal("update").executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			UpgradeMigraProcedure.execute(world);
//			return 0;
//		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			EvoLvlMigraProcedure.execute(world, x, y, z, arguments);
//			return 0;
//		}))).then(Commands.literal("subsisting").then(Commands.literal("update").executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			UpgradeSubsisProcedure.execute(world);
//			return 0;
//		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//			Direction direction = Direction.DOWN;
//			if (entity != null)
//				direction = entity.getDirection();
//
//			EvoLvlSubsisProcedure.execute(world, x, y, z, arguments);
//			return 0;
//		}))).then(Commands.literal("silence").then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
//			Level world = arguments.getSource().getUnsidedLevel();
//			double x = arguments.getSource().getPosition().x();
//			double y = arguments.getSource().getPosition().y();
//			double z = arguments.getSource().getPosition().z();
//			Entity entity = arguments.getSource().getEntity();
//			if (entity == null && world instanceof ServerLevel _servLevel)
//				entity = FakePlayerFactory.getMinecraft(_servLevel);
//
//			if (entity != null) {
//				if (IfCanSilenceProcedure.execute(world)) {
//					MapVariables.get(world).strategySilence = DoubleArgumentType.getDouble(arguments, "lvl");
//					MapVariables.get(world).syncData(world);
//					if (DoubleArgumentType.getDouble(arguments, "lvl") == 1) {
//						if ((LevelAccessor) world instanceof Level _level) {
//							if (!_level.isClientSide()) {
//								_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.SILENCE1.get(), SoundSource.NEUTRAL, 4, 1);
//							} else {
//								_level.playLocalSound(x, y, z, ModSounds.SILENCE1.get(), SoundSource.NEUTRAL, 4, 1, false);
//							}
//						}
//						if (entity instanceof Player _player && !_player.level().isClientSide())
//							_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_6").getString())), true);
//					} else if (DoubleArgumentType.getDouble(arguments, "lvl") == 2) {
//						if ((LevelAccessor) world instanceof Level _level) {
//							if (!_level.isClientSide()) {
//								_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.SILENCE2.get(), SoundSource.NEUTRAL, 4, 1);
//							} else {
//								_level.playLocalSound(x, y, z, ModSounds.SILENCE2.get(), SoundSource.NEUTRAL, 4, 1, false);
//							}
//						}
//						if (entity instanceof Player _player && !_player.level().isClientSide())
//							_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_7").getString())), true);
//					} else if (DoubleArgumentType.getDouble(arguments, "lvl") == 3) {
//						if ((LevelAccessor) world instanceof Level _level) {
//							if (!_level.isClientSide()) {
//								_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.SILENCE3.get(), SoundSource.NEUTRAL, 4, 1);
//							} else {
//								_level.playLocalSound(x, y, z, ModSounds.SILENCE3.get(), SoundSource.NEUTRAL, 4, 1, false);
//							}
//						}
//						if (entity instanceof Player _player && !_player.level().isClientSide())
//							_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_8").getString())), true);
//					} else if (DoubleArgumentType.getDouble(arguments, "lvl") == 4) {
//						if ((LevelAccessor) world instanceof Level _level) {
//							if (!_level.isClientSide()) {
//								_level.playSound(null, BlockPos.containing(x, y, z), ModSounds.SILENCE4.get(), SoundSource.NEUTRAL, 4, 1);
//							} else {
//								_level.playLocalSound(x, y, z, ModSounds.SILENCE4.get(), SoundSource.NEUTRAL, 4, 1, false);
//							}
//						}
//						if (entity instanceof Player _player && !_player.level().isClientSide())
//							_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_9").getString())), true);
//					}
//				} else {
//					if (entity instanceof Player _player && !_player.level().isClientSide())
//						_player.displayClientMessage(Component.literal((Component.translatable("item.caerula_arbor.language_key.description_5").getString())), true);
//				}
//			}
//			return 0;
//		}))));
}
