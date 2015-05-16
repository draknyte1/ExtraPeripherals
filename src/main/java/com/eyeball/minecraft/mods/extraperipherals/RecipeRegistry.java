package com.eyeball.minecraft.mods.extraperipherals;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;
import com.eyeball.minecraft.mods.extraperipherals.item.ItemRegistry;

public class RecipeRegistry {

	public static final HashMap<String, IRecipe> recipes = new HashMap<String, IRecipe>();

	static {
		recipes.put("chatBox",
				new ShapedOreRecipe(BlockRegistry.blocks.get("chatBox"), "gng",
						"ndn", "gng", 'g', "ingotGold", 'n', Blocks.noteblock,
						'd', "diamond"));
		recipes.put("mouse",
				new ShapedOreRecipe(ItemRegistry.items.get("mouse"), "IRI",
						"III", "III", 'I', "ingotIron", 'R', "materialRubber"));
		recipes.put("mouse",
				new ShapedOreRecipe(ItemRegistry.items.get("mouse"), "IRI",
						"III", "III", 'I', "ingotIron", 'R', "itemRubber"));
		
		recipes.put("playerDetector", new ShapedOreRecipe(BlockRegistry.blocks.get("playerDetector"), "GRG", "EDE", "GRG" ,'G', "ingotGold", 'R', "dustRedstone", 'E', Items.ender_eye, 'D', "gemDiamond"));
		
//		recipes.put("universalCharger", new ShapedOreRecipe(BlockRegistry.blocks.get("universalCharger"), "", "", ""));

	}

}
