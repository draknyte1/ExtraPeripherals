package com.eyeball.minecraft.mods.extraperipherals.tile;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.eyeball.minecraft.mods.extraperipherals.util.ChatUtil;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class TileChatBox extends TileEPBase implements IPeripheral {

	public static class ChatList {

		public static HashMap<TileChatBox, Boolean> chatBoxMap = new HashMap<TileChatBox, Boolean>();

		@SubscribeEvent
		public void onChat(ServerChatEvent event) {
			for (TileChatBox box : ChatList.chatBoxMap.keySet()) {
				if (Vec3.createVectorHelper(box.xCoord, box.yCoord, box.zCoord)
						.distanceTo(event.player.getPosition(1.0f)) <= Integer.MAX_VALUE)
					box.onChat(event.player, event.message);
			}
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.entity instanceof EntityPlayer) {
			for (TileChatBox box : ChatList.chatBoxMap.keySet()) {
				if (Vec3.createVectorHelper(box.xCoord, box.yCoord, box.zCoord)
						.distanceTo(
								((EntityPlayer) event.entity).getPosition(1.0f)) <= Integer.MAX_VALUE)
					box.onDeath((EntityPlayer) event.entity, event.source);
			}
		}
	}

	World world;
	String cbName = "Chat Box";

	private HashMap<IComputerAccess, Boolean> computers = new HashMap<IComputerAccess, Boolean>();

	public TileChatBox(World world) {
		this.world = world;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		cbName = nbtTagCompound.getString("cbName");
		if (cbName.isEmpty())
			cbName = "ChatBox";
	};

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setString("cBName", cbName);
	};

	@Override
	public String getType() {
		return "ChatBox";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] { "say", "tell", "setName", "getName" };
	}

	public void onChat(EntityPlayer player, String message) {
		for (IComputerAccess computer : computers.keySet())
			computer.queueEvent("chat", new Object[] { player.getDisplayName(),
					message });
	}

	public void onDeath(EntityPlayer player, DamageSource source) {
		String killer = null;
		if (source instanceof EntityDamageSource) {
			Entity ent = ((EntityDamageSource) source).getEntity();
			if (ent != null)
				killer = ent.getCommandSenderName();
		}
		for (IComputerAccess computer : computers.keySet())
			computer.queueEvent("death", new Object[] {
					player.getDisplayName(), killer, source.damageType });
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {

		if (method == 0) {
			if (arguments.length < 1) {
				return new Object[] { false,
						"Bad argument #1, expected string, got nil" };
			}
			if (!(arguments[0] instanceof String)) {
				return new Object[] {
						false,
						"Bad argument #1, expected string, got "
								+ arguments.getClass().getSimpleName()
										.toLowerCase() };
			}
			ChatUtil.sendMessageToEveryone(this, "<" + cbName + "> "
					+ (String) arguments[0]);
		} else if (method == 1) {

			// arguments[0] = playername. arguments[1] = message

			if (arguments.length < 1)
				return new Object[] { false,
						"Bad argument #1, expected string, got nil" };
			if (arguments.length < 2)
				return new Object[] { false,
						"Bad argument #2, expected string, got nil" };
			String gi = EnumChatFormatting.GRAY + ""
					+ EnumChatFormatting.ITALIC;
			String message = gi
					+ StatCollector.translateToLocal(
							"extraperipherals.chat.whisperPrefix").replaceFirst(
							"%s", cbName).replaceFirst("%s", arguments[1].toString());
			if (!world.isRemote)
				ChatUtil.sendPrivateMessageToPlayer(world, message,
						arguments[0].toString());
		} else if (method == 2) {
			if (arguments[0].toString().isEmpty()) {
				return new Object[] { false,
						"Bad argument #1, chat box name cannot be empty" };
			}
			cbName = arguments[0].toString();
		} else if (method == 3) {
			return new Object[] { cbName };
		}

		return null;
	}

	@Override
	public void attach(IComputerAccess computer) {
		if (computers.size() == 0)
			ChatList.chatBoxMap.put(this, true);
		computers.put(computer, true);
	}

	@Override
	public void detach(IComputerAccess computer) {
		computers.remove(computer);
		if (computers.size() == 0)
			ChatList.chatBoxMap.remove(this);
	}

	@Override
	public boolean equals(IPeripheral other) {

		return false;
	}

}
