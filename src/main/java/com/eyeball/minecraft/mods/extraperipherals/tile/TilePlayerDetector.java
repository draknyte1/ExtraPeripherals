package com.eyeball.minecraft.mods.extraperipherals.tile;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class TilePlayerDetector extends TileEPBase implements IPeripheral {

	public static class PlayerDetectorRegistry {
		public static HashMap<TilePlayerDetector, Boolean> playerDetectors = new HashMap<TilePlayerDetector, Boolean>();
		public static HashMap<IComputerAccess, Boolean> computers = new HashMap<IComputerAccess, Boolean>();
	}

	@Override
	public String getType() {
		return "playerDetector";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] {};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {
		return null;
	}

	@Override
	public void attach(IComputerAccess computer) {
		ExtraPeripheralsMod.LOGGER.info("ATTACH");
		PlayerDetectorRegistry.playerDetectors.put(this, true);
		PlayerDetectorRegistry.computers.put(computer, true);
	}

	@Override
	public void detach(IComputerAccess computer) {
		ExtraPeripheralsMod.LOGGER.info("DETACH");
		PlayerDetectorRegistry.playerDetectors.remove(this);
		PlayerDetectorRegistry.computers.remove(computer);
	}

	@Override
	public boolean equals(IPeripheral other) {
		return false;
	}

	public void onBlockActivated(EntityPlayer player) {
		for (IComputerAccess computer : PlayerDetectorRegistry.computers
				.keySet()) {
			computer.queueEvent("player",
					new Object[] { player.getDisplayName() });
		}
	}
}
