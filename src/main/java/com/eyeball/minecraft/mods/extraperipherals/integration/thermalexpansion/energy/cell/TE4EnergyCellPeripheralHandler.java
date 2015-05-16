package com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.energy.cell;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyHandler;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class TE4EnergyCellPeripheralHandler implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (tile instanceof IEnergyHandler) {
			return new TE4EnergyCellPeripheral((IEnergyHandler) tile);
		}
		return null;
	}

}