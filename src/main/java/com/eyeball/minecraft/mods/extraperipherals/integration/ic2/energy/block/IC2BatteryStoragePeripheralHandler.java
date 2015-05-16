package com.eyeball.minecraft.mods.extraperipherals.integration.ic2.energy.block;

import ic2.api.tile.IEnergyStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class IC2BatteryStoragePeripheralHandler implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (tile instanceof IEnergyStorage)
			return new IC2BatteryStoragePeripheral((IEnergyStorage) tile);
		return null;
	}

}
