package com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.energy.cell;

import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class TE4EnergyCellPeripheral implements IPeripheral {

	IEnergyHandler tile;

	public TE4EnergyCellPeripheral(IEnergyHandler tile2) {
		tile = tile2;
	}

	@Override
	public String getType() {
		return "te4EnergyCell";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] { "getRFStorage", "getRFCapacity" };
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {
		if (method == 0) {
			return new Object[] { tile.getEnergyStored(ForgeDirection.UNKNOWN) };
		} else if (method == 1) {
			return new Object[] { tile.getMaxEnergyStored(ForgeDirection.UNKNOWN) };
		}
		return null;
	}

	@Override
	public void attach(IComputerAccess computer) {

	}

	@Override
	public void detach(IComputerAccess computer) {

	}

	@Override
	public boolean equals(IPeripheral other) {

		return this == other;
	}

}
