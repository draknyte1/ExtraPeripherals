package com.eyeball.minecraft.mods.extraperipherals.item;

import java.util.HashMap;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;

public class ItemRegistry {

	public static final HashMap<String, ItemEPBase> items = new HashMap<String, ItemEPBase>();
	public static final HashMap<String, String> itemClass = new HashMap<String, String>();

	static {
		items.put("mouse", new ClickerItem());
		for (ItemEPBase item : items.values()) {
			ExtraPeripheralsMod.LOGGER.info(item.getUnlocalizedName());
		}
	}
	
}
