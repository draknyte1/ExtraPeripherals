package com.eyeball.minecraft.mods.extraperipherals.tile.rfchargers;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.energy.TE4TileEntity;

public abstract class TileRFCharger extends TE4TileEntity {

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
		return true;
	}

	@Override
	public short getFacing() {
		return 0;
	}

	@Override
	public void setFacing(short facing) {
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 100;
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player,
			World world, int x, int y, int z, boolean returnDrops) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public boolean canDismantle(EntityPlayer player, World world, int x, int y,
			int z) {
		return true;
	}

}
