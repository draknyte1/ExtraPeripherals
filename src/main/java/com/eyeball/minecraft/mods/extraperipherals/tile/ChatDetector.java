package com.eyeball.minecraft.mods.extraperipherals.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.eyeball.minecraft.mods.extraperipherals.tile.TileChatBox.ChatList;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChatDetector {

	@SubscribeEvent
	public void onChat(ServerChatEvent event) {
		for (TileChatBox box : ChatList.chatBoxMap.keySet()) {
			box.onChat(event.player, event.message);
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			for (TileChatBox box : ChatList.chatBoxMap.keySet()) {
				box.onDeath((EntityPlayer) event.entity, event.source);
			}
		}
	}

}
