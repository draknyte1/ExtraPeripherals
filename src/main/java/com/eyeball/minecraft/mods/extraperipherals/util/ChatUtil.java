package com.eyeball.minecraft.mods.extraperipherals.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.eyeball.minecraft.mods.extraperipherals.network.packet.ChatMessagePacket;

public class ChatUtil {

	@SuppressWarnings("unchecked")
	public static void sendMessageToEveryone(TileEntity tileEntity, String text) {
		for (EntityPlayer player : (Iterable<EntityPlayer>) tileEntity
				.getWorldObj().playerEntities) {
			Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY,
					player.posZ);
			playerPos.yCoord = tileEntity.yCoord;
			if (playerPos.distanceTo(Vec3.createVectorHelper(tileEntity.xCoord,
					tileEntity.yCoord, tileEntity.zCoord)) > Integer.MAX_VALUE)
				continue;
			ExtraPeripheralsMod.NETWORK.sendTo(new ChatMessagePacket(text),
					(EntityPlayerMP) player);
		}
	}

	@SuppressWarnings("unchecked")
	public static void sendPrivateMessageToPlayer(World worldObject,
			String text, String playerName) {
		for (EntityPlayer player : (Iterable<EntityPlayer>) worldObject.playerEntities) {
			ExtraPeripheralsMod.LOGGER.info(player.getDisplayName());
			if (player.getDisplayName().trim().equals(playerName.trim()))
				ExtraPeripheralsMod.NETWORK.sendTo(new ChatMessagePacket(text),
						(EntityPlayerMP) player);
		}
	}

	public static void sendPrivateMessageToPlayer(String text,
			EntityPlayer player) {

		if (player.getDisplayName().trim()
				.equals(player.getDisplayName().trim()))
			
			ExtraPeripheralsMod.NETWORK.sendTo(new ChatMessagePacket(text),
					(EntityPlayerMP) player);
	}

}
