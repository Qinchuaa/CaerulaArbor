
package com.apocalypse.caerulaarbor.network;

import com.apocalypse.caerulaarbor.CaerulaArborMod;
import com.apocalypse.caerulaarbor.world.inventory.RelicShowcaseMenu;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import com.apocalypse.caerulaarbor.procedures.UngainRelicSPEARProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainRelicEXTENProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainRelicCrownProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainRelicCRYSTALProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainRelicARMORProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandThornsProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandStrangleProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandSpeedProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandSWIPEProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandFERTILITYProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainHandBARRENProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainCursedResearchProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainCursedGlowbodyProcedure;
import com.apocalypse.caerulaarbor.procedures.UngainCursedEMELIGHTProcedure;
import com.apocalypse.caerulaarbor.procedures.InnerOpenCaerulaProcedure;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RelicShowcaseButtonMessage {
	private final int buttonID, x, y, z;

	public RelicShowcaseButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public RelicShowcaseButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(RelicShowcaseButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(RelicShowcaseButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = RelicShowcaseMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			InnerOpenCaerulaProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			UngainRelicCrownProcedure.execute(entity);
		}
		if (buttonID == 2) {

			UngainRelicSPEARProcedure.execute(entity);
		}
		if (buttonID == 3) {

			UngainRelicARMORProcedure.execute(entity);
		}
		if (buttonID == 4) {

			UngainRelicEXTENProcedure.execute(entity);
		}
		if (buttonID == 5) {

			UngainRelicCRYSTALProcedure.execute(entity);
		}
		if (buttonID == 10) {

			UngainHandThornsProcedure.execute(entity);
		}
		if (buttonID == 11) {

			UngainHandStrangleProcedure.execute(entity);
		}
		if (buttonID == 12) {

			UngainHandFERTILITYProcedure.execute(entity);
		}
		if (buttonID == 13) {

			UngainHandBARRENProcedure.execute(entity);
		}
		if (buttonID == 14) {

			UngainHandSWIPEProcedure.execute(entity);
		}
		if (buttonID == 19) {

			UngainCursedEMELIGHTProcedure.execute(entity);
		}
		if (buttonID == 20) {

			UngainCursedGlowbodyProcedure.execute(entity);
		}
		if (buttonID == 21) {

			UngainCursedResearchProcedure.execute(entity);
		}
		if (buttonID == 37) {

			UngainHandSpeedProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		CaerulaArborMod.addNetworkMessage(RelicShowcaseButtonMessage.class, RelicShowcaseButtonMessage::buffer, RelicShowcaseButtonMessage::new, RelicShowcaseButtonMessage::handler);
	}
}
