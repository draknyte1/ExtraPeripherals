package com.eyeball.minecraft.mods.extraperipherals.tile;

import ic2.api.energy.tile.IEnergySink;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;
import com.eyeball.minecraft.mods.extraperipherals.integration.ic2.energy.IC2TileEntityElectricMachine;

import dan200.computercraft.api.turtle.ITurtleAccess;

public class TileEUCharger extends IC2TileEntityElectricMachine implements
		IEnergySink {

	public TileEUCharger(int maxenergy, int tier1, int oldDischargeIndex) {
		super(maxenergy, tier1, oldDischargeIndex);
	}

	int outputRate = 100;
	private String name = "tileEntityEUCharger";

	int amount = 0;

	public String getName() {
		return name;
	}

	@Override
	public void updateEntity() {
		if (!getWorldObj().isRemote) {
			List<ITurtleAccess> turtlesConnected = new ArrayList<ITurtleAccess>();
			ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
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
						ITurtleAccess turtle = (ITurtleAccess) tileEntity;
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
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.ic2Blocks.get("euCharger"));
	}

}
