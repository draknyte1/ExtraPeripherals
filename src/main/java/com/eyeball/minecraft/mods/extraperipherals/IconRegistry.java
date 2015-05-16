package com.eyeball.minecraft.mods.extraperipherals;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class IconRegistry {
    
	public static final HashMap<String, IIcon> icons = new HashMap<String, IIcon>();

	static {
		IIconRegister register = Minecraft.getMinecraft().getTextureMapBlocks();
		IconRegistry.icons.put("chatBox", register.registerIcon(Reference.MODID + ":chatBox"));
	}
	
}
