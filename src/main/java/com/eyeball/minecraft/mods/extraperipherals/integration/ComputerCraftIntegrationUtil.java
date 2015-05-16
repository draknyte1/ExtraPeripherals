package com.eyeball.minecraft.mods.extraperipherals.integration;

import net.minecraft.item.ItemStack;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;

import cpw.mods.fml.common.registry.GameRegistry;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import dan200.computercraft.api.turtle.ITurtleUpgrade;

public class ComputerCraftIntegrationUtil {

	public static void registerTurtleUpgrade(ITurtleUpgrade toRegister) {
		ComputerCraftAPI.registerTurtleUpgrade(toRegister);
		EPCreativeTab.upgrades.add(toRegister);
	}

	public static void registerPeripheralHandler(IPeripheralProvider toRegister) {
		ComputerCraftAPI.registerPeripheralProvider(toRegister);
	}
	
	public static ItemStack getCCItem(String name) {
		return GameRegistry.findItemStack("ComputerCraft", name, 1);
	}
	public static ItemStack getCCTurtle(String name) {
		return GameRegistry.findItemStack("CCTurtle", name, 1);
	}

}
