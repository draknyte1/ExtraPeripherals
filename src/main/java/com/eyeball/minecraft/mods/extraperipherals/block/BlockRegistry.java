package com.eyeball.minecraft.mods.extraperipherals.block;

import java.util.HashMap;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;

public class BlockRegistry {

	public static final HashMap<String, BlockEPBase> blocks = new HashMap<String, BlockEPBase>();
	public static final HashMap<String, String> blockClass = new HashMap<String, String>();

	static {
		blocks.put("chatBox", new BlockChatBox());
		blocks.put("playerDetector", new BlockPlayerDetector());
		blocks.put("rfChargerLeadstone", new BlockRFChargerT1());
		blocks.put("rfChargerHardened", new BlockRFChargerT2());
		blocks.put("rfChargerRedstone", new BlockRFChargerT3());
		blocks.put("rfChargerResonant", new BlockRFChargerT4());
		blocks.put("euCharger", new BlockEUCharger());
		for (BlockEPBase block : blocks.values()) {
			block.setCreativeTab(EPCreativeTab.CREATIVETAB);
		}
	}

}
