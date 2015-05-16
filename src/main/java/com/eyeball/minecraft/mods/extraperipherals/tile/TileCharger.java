package com.eyeball.minecraft.mods.extraperipherals.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.eyeball.minecraft.mods.extraperipherals.block.BlockUniversalCharger;

import cpw.mods.fml.relauncher.ReflectionHelper;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;

public class TileCharger extends TileEntity implements IPeripheral {

	World world;

	BlockUniversalCharger parent;

	public TileCharger(World world, BlockUniversalCharger parent) {
		this.world = world;
		this.parent = parent;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		parent.energyStored = nbtTagCompound.getInteger("energyStored");
	};

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setInteger("energyStoreg", parent.energyStored);
	};

	@Override
	public String getType() {
		return "Charger";
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
	}

	@Override
	public void detach(IComputerAccess computer) {
	}

	@Override
	public boolean equals(IPeripheral other) {
		return false;
	}

	private int addFuel(ITurtleAccess turtle, int rate) {
		if (turtle.getFuelLimit() > turtle.getFuelLevel()) {
			turtle.setFuelLevel(rate + turtle.getFuelLevel());
			return rate;
		}
		return 0;
	}

	@Override
	public void updateEntity() {
		if (!getWorldObj().isRemote) {
			List<ITurtleAccess> turtles = new ArrayList<ITurtleAccess>(6);
			ForgeDirection[] dirs = { ForgeDirection.DOWN, ForgeDirection.EAST,
					ForgeDirection.NORTH, ForgeDirection.SOUTH,
					ForgeDirection.UP, ForgeDirection.WEST };
			for (int i = 0; i < 6; i++) {
				int x = this.xCoord + dirs[i].offsetX;
				int y = this.yCoord + dirs[i].offsetY;
				int z = this.zCoord + dirs[i].offsetZ;
				if (!getWorldObj().blockExists(x, y, z))
					continue;
				TileEntity te = getWorldObj().getTileEntity(x, y, z);
				if (te != null) {
					try {
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			int rate = ((int) Math.floor((float) 6 / (float) turtles.size()));
			for (ITurtleAccess turtle : turtles) {
				if (parent.getEnergyStored(ForgeDirection.UNKNOWN) >= rate) {
					parent.energyStored -= (addFuel(turtle, rate) * 100);
				}
			}
		}
	}
}
