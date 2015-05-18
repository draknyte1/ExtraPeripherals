package com.eyeball.minecraft.mods.extraperipherals.block;

import java.util.HashMap;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;

public class BlockRegistry {

	public static final HashMap<String, BlockEPBase> includedBlocks = new HashMap<String, BlockEPBase>();
	public static final HashMap<String, BlockEPBase> ic2Blocks = new HashMap<String, BlockEPBase>();
	public static final HashMap<String, BlockEPBase> te4Blocks = new HashMap<String, BlockEPBase>();
	public static final HashMap<String, String> blockClass = new HashMap<String, String>();

	static {
		includedBlocks.put("chatBox", new BlockChatBox());
		includedBlocks.put("playerDetector", new BlockPlayerDetector());
		te4Blocks.put("rfChargerLeadstone", new BlockRFChargerT1());
		te4Blocks.put("rfChargerHardened", new BlockRFChargerT2());
		te4Blocks.put("rfChargerRedstone", new BlockRFChargerT3());
		te4Blocks.put("rfChargerResonant", new BlockRFChargerT4());
		ic2Blocks.put("euCharger", new BlockEUCharger());
		for (BlockEPBase block : includedBlocks.values()) {
			block.setCreativeTab(EPCreativeTab.CREATIVETAB);
		}
	}

}
