package com.eyeball.minecraft.mods.extraperipherals;

import com.eyeball.minecraft.mods.extraperipherals.integration.ic2.energy.block.IC2BatteryStoragePeripheralHandler;
import com.eyeball.minecraft.mods.extraperipherals.integration.thermalexpansion.energy.cell.TE4EnergyCellPeripheralHandler;

public class Reference {

	public static final String MODID = "extraperipherals";
	public static final String NAME = "Extra Peripherals";
	public static final String VERSION = "1.0";
	public static final String[] softDependencies = { "PeripheralsPlusPlus",
			"MoarPeripherals", "OpenPeripheralCore", "appliedenergistics2",
			"IC2", "Thaumcraft", "Forestry", "Railcraft", "ThermalExpansion",
			"BuildCraft|Core", "Waila", "NotEnoughItems", "EnderStorage",
			"WR-CBE|Core", "WR-CBE|Addons", "WR-CBE|Logic" };
	public static final String[] hardDependencies = { "ComputerCraft" };
	public static final StringBuilder depsBuilder = new StringBuilder();
	public static final String DEPENDENCIES;

	static {

		System.out.println();

		for (String currentModID : hardDependencies) {
			depsBuilder.append("required-after:" + currentModID + "; ");
		}
		for (String currentModID : softDependencies) {
			depsBuilder.append("after:" + currentModID + "; ");
		}
		DEPENDENCIES = depsBuilder.toString();
	}

	public static class PeripheralHandlers {

		public static final IC2BatteryStoragePeripheralHandler IC2POWERHANDLER = new IC2BatteryStoragePeripheralHandler();
		public static final TE4EnergyCellPeripheralHandler TE4POWERHANDLER = new TE4EnergyCellPeripheralHandler();

	}

}
