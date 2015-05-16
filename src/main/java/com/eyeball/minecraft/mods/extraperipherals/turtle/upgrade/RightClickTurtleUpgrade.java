package com.eyeball.minecraft.mods.extraperipherals.turtle.upgrade;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.eyeball.minecraft.mods.extraperipherals.Reference;
import com.eyeball.minecraft.mods.extraperipherals.api.IIconNeeded;
import com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.peripheral.machine.RightClickerPeripheral;
import com.eyeball.minecraft.mods.extraperipherals.item.ItemRegistry;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleCommandResult;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import dan200.computercraft.api.turtle.TurtleVerb;

public class RightClickTurtleUpgrade implements ITurtleUpgrade, IIconNeeded {

	IIcon icon;

	@Override
	public int getUpgradeID() {
		return 100;
	}

	@Override
	public String getUnlocalisedAdjective() {
		return Reference.MODID.toLowerCase() + ".turtleUpgrade.clicky";
	}

	@Override
	public TurtleUpgradeType getType() {
		return TurtleUpgradeType.Peripheral;
	}

	@Override
	public ItemStack getCraftingItem() {
		return new ItemStack(ItemRegistry.items.get("mouse"));
	}

	@Override
	public IPeripheral createPeripheral(ITurtleAccess turtle, TurtleSide side) {
		return new RightClickerPeripheral(turtle);
	}

	@Override
	public TurtleCommandResult useTool(ITurtleAccess turtle, TurtleSide side,
			TurtleVerb verb, int direction) {
		return null;
	}

	@Override
	public IIcon getIcon(ITurtleAccess turtle, TurtleSide side) {
		return icon;
	}

	@Override
	public void update(ITurtleAccess turtle, TurtleSide side) {
	}

	@Override
	public void registerIcons(IIconRegister register) {
		ExtraPeripheralsMod.LOGGER.info("Loading mouse texture. Class: "
				+ register.getClass().getName());
		icon = register.registerIcon("ExtraPeripherals:mouse");
		ExtraPeripheralsMod.LOGGER.info(icon == null);
	}

}