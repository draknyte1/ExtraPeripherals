package com.eyeball.minecraft.mods.extraperipherals.tile.rfchargers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;
import com.eyeball.minecraft.mods.extraperipherals.util.TurtleUtil;

import dan200.computercraft.api.turtle.ITurtleAccess;

public class TileRFChargerT3 extends TileRFCharger implements IEnergyProvider,
		IEnergyHandler {

	int outputRate = 2000;
	private EnergyStorage storage = new EnergyStorage(10000000);
	private String name = "tileEntityRFCharger";

	public TileRFChargerT3() {
		super();
	}

	public String getName() {
		return name;
	}

	@Override
	public void updateEntity() {
		if (!getWorldObj().isRemote) {
			List<ITurtleAccess> turtlesConnected = new ArrayList<ITurtleAccess>();
			ForgeDirection[] dirs = new ForgeDirection[] { ForgeDirection.DOWN,
					ForgeDirection.UP, ForgeDirection.NORTH,
					ForgeDirection.SOUTH, ForgeDirection.EAST,
					ForgeDirection.WEST };
			for (int i = 0; i < 6; i++) {
				int curTurtleX = this.xCoord + dirs[i].offsetX;
				int curTurtleY = this.yCoord + dirs[i].offsetY;
				int curTurtleZ = this.zCoord + dirs[i].offsetZ;
				if (!getWorldObj().blockExists(curTurtleX, curTurtleY,
						curTurtleZ))
					continue;
				TileEntity tileEntity = getWorldObj().getTileEntity(curTurtleX,
						curTurtleY, curTurtleZ);
				if (tileEntity != null) {
					try {
						ITurtleAccess turtle = TurtleUtil.getTurtle(tileEntity);
						if (turtle != null) {
							turtlesConnected.add(turtle);
						}
					} catch (Exception e) {
					}
				}
			}
			if (turtlesConnected.size() == 0)
				return;
			int turtleRate = outputRate / turtlesConnected.size();
			for (ITurtleAccess turtle : turtlesConnected) {
				addFuel(turtle, turtleRate);
			}
		}
	}

	private int addFuel(ITurtleAccess turtle, int rate) {
		if (turtle.getFuelLimit() > turtle.getFuelLevel()) {
			turtle.setFuelLevel(rate + turtle.getFuelLevel());
			return rate;
		}
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		storage.writeToNBT(nbt);
	}

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.te4Blocks.get("rfChargerRedstone"));
	}

}
