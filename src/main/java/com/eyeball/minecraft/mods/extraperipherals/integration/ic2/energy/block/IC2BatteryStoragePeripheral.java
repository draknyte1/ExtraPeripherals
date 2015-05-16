package com.eyeball.minecraft.mods.extraperipherals.integration.ic2.energy.block;

import ic2.api.tile.IEnergyStorage;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class IC2BatteryStoragePeripheral implements IPeripheral {

	private IEnergyStorage tile;

	public IC2BatteryStoragePeripheral(IEnergyStorage storage) {
		tile = storage;
	}

	@Override
	public String getType() {
		return "batteryStorageUnitIC2";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] { "getEUStorage", "getEUCapacity", "getOutput" };
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {

		if (method == 0) {
			return new Object[] { tile.getStored() };
		} else if (method == 1) {
			return new Object[] { tile.getCapacity() };
		} else if (method == 2) {
			return new Object[] { tile.getOutput() };
		}

		return new Object[] {};
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
