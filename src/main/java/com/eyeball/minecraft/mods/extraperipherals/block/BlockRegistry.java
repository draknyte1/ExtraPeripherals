package com.eyeball.minecraft.mods.extraperipherals.block;

import java.util.HashMap;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;

public class BlockRegistry {

	public static final HashMap<String, BlockEPBase> blocks = new HashMap<String, BlockEPBase>();
	public static final HashMap<String, String> blockClass = new HashMap<String, String>();

	static {
		blocks.put("chatBox", new BlockChatBox());
		blocks.put("playerDetector", new BlockPlayerDetector());
		blocks.put("universalCharger", new BlockUniversalCharger());
		for (BlockEPBase block : blocks.values()) {
			ExtraPeripheralsMod.LOGGER.info(block.getLocalizedName());
		}
	}

}
