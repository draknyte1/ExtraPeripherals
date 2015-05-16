package com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.peripheral.machine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Facing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.eyeball.minecraft.mods.extraperipherals.util.TurtleFakePlayer;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;

public class RightClickerPeripheral implements IPeripheral {

	ITurtleAccess turtle;

	public RightClickerPeripheral(ITurtleAccess turtle) {
		this.turtle = turtle;
	}

	@Override
	public String getType() {
		return "clicky";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] { "rightClick" };
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws LuaException,
			InterruptedException {

		if (method == 0) {
			int blockX = turtle.getPosition().posX
					+ Facing.offsetsXForSide[turtle.getDirection()];
			int blockY = turtle.getPosition().posY
					+ Facing.offsetsYForSide[turtle.getDirection()];
			int blockZ = turtle.getPosition().posZ
					+ Facing.offsetsZForSide[turtle.getDirection()];

			Block blockFacing = turtle.getWorld().getBlock(blockX, blockY,
					blockZ);

			ExtraPeripheralsMod.LOGGER.info(blockFacing.getLocalizedName()
					.replace(".name", ""));
			EntityPlayer fPlayer = new TurtleFakePlayer(turtle.getWorld(),
					turtle);
			blockFacing.onBlockActivated(turtle.getWorld(), blockX, blockY,
					blockZ, fPlayer, turtle.getDirection(), 0.5F, 0.5F, 0.5F);// Hit
																				// the
																				// center.
			if (turtle.getWorld().isAirBlock(turtle.getPosition().posX,
					turtle.getPosition().posY, turtle.getPosition().posZ)) {
				MinecraftForge.EVENT_BUS.post(new PlayerInteractEvent(fPlayer,
						Action.RIGHT_CLICK_AIR, turtle.getPosition().posX,
						turtle.getPosition().posY, turtle.getPosition().posZ,
						turtle.getDirection(), turtle.getWorld()));
			} else {
				MinecraftForge.EVENT_BUS.post(new PlayerInteractEvent(fPlayer,
						Action.RIGHT_CLICK_BLOCK, turtle.getPosition().posX,
						turtle.getPosition().posY, turtle.getPosition().posZ,
						turtle.getDirection(), turtle.getWorld()));
			}
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

		return false;
	}

}
