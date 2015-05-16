package com.eyeball.minecraft.mods.extraperipherals.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ChatMessagePacket implements IMessage {

	public String text;

	public ChatMessagePacket() {
	}

	public ChatMessagePacket(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}

	public static class ChatMessagePacketHandler implements
			IMessageHandler<ChatMessagePacket, IMessage> {

		@Override
		public IMessage onMessage(ChatMessagePacket message,
				MessageContext context) {
			if (Minecraft.getMinecraft() != null
					&& Minecraft.getMinecraft().thePlayer != null)
				Minecraft.getMinecraft().thePlayer
						.addChatComponentMessage(new ChatComponentText(
								message.text));
			return null;
		}
	}
}
