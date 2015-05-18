package com.eyeball.minecraft.mods.extraperipherals.util;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.mojang.authlib.GameProfile;

import dan200.computercraft.api.turtle.ITurtleAccess;

public class TurtleFakePlayer extends EntityPlayer {

	private static final GameProfile gameProfile = new GameProfile(
			UUID.fromString("0d0c4ca0-4ff1-11e4-916c-0800200c9a66"),
			"ExtraPeripherals-FakePlayer");

	ITurtleAccess turtleAccess;

	World w;

	@SuppressWarnings("unchecked")
	public TurtleFakePlayer(World world, ITurtleAccess turtle) {
		super(world, gameProfile);
		turtleAccess = turtle;
		world.playerEntities.add(this);
		w = world;
	}

	public void done() {
		w.playerEntities.remove(this);
	}

	@Override
	public void addChatMessage(IChatComponent message) {
		ExtraPeripheralsMod.LOGGER.info("Fake player got message: "
				+ message.getUnformattedText());
	}

	@Override
	public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
		return true;
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return turtleAccess.getPosition();
	}

}
