
package com.apocalypse.caerulaarbor.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import com.apocalypse.caerulaarbor.procedures.UpgradeSubsisProcedure;
import com.apocalypse.caerulaarbor.procedures.UpgradeMigraProcedure;
import com.apocalypse.caerulaarbor.procedures.UpgradeGrowProcedure;
import com.apocalypse.caerulaarbor.procedures.UpgradeBreedProcedure;
import com.apocalypse.caerulaarbor.procedures.EvoLvlSubsisProcedure;
import com.apocalypse.caerulaarbor.procedures.EvoLvlSilenceProcedure;
import com.apocalypse.caerulaarbor.procedures.EvoLvlMigraProcedure;
import com.apocalypse.caerulaarbor.procedures.EvoLvlGrowProcedure;
import com.apocalypse.caerulaarbor.procedures.EvoLvlBreedProcedure;

import com.mojang.brigadier.arguments.DoubleArgumentType;

@Mod.EventBusSubscriber
public class EvolutionCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("caerula_arbor:evolution").requires(s -> s.hasPermission(2)).then(Commands.literal("grow").then(Commands.literal("update").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			UpgradeGrowProcedure.execute(world);
			return 0;
		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			EvoLvlGrowProcedure.execute(world, x, y, z, arguments);
			return 0;
		}))).then(Commands.literal("breed").then(Commands.literal("update").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			UpgradeBreedProcedure.execute(world);
			return 0;
		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			EvoLvlBreedProcedure.execute(world, x, y, z, arguments);
			return 0;
		}))).then(Commands.literal("migration").then(Commands.literal("update").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			UpgradeMigraProcedure.execute(world);
			return 0;
		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			EvoLvlMigraProcedure.execute(world, x, y, z, arguments);
			return 0;
		}))).then(Commands.literal("subsisting").then(Commands.literal("update").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			UpgradeSubsisProcedure.execute(world);
			return 0;
		})).then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			EvoLvlSubsisProcedure.execute(world, x, y, z, arguments);
			return 0;
		}))).then(Commands.literal("silence").then(Commands.argument("lvl", DoubleArgumentType.doubleArg(0, 4)).executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			EvoLvlSilenceProcedure.execute(world, x, y, z, arguments, entity);
			return 0;
		}))));
	}
}
