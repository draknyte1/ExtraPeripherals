package com.eyeball.minecraft.mods.extraperipherals;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;
import com.eyeball.minecraft.mods.extraperipherals.item.ItemRegistry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry {

	public static final HashMap<String, IRecipe> recipes = new HashMap<String, IRecipe>();

	static {

		recipes.put("mouse",
				new ShapedOreRecipe(ItemRegistry.items.get("mouse"), "IRI",
						"III", "III", 'I', "ingotIron", 'R', "materialRubber"));
		recipes.put("mouse",
				new ShapedOreRecipe(ItemRegistry.items.get("mouse"), "IRI",
						"III", "III", 'I', "ingotIron", 'R', "itemRubber"));

		recipes.put("playerDetector", new ShapedOreRecipe(
				BlockRegistry.includedBlocks.get("playerDetector"), "GRG",
				"EDE", "GRG", 'G', "ingotGold", 'R', "dustRedstone", 'E',
				Items.ender_eye, 'D', "gemDiamond"));

		if (Loader.isModLoaded("IC2")) {
			recipes.put(
					"euCharger",
					new ShapedOreRecipe(
							BlockRegistry.ic2Blocks.get("euCharger"),
							"BM ",
							"R  ",
							"   ",
							'B',
							GameRegistry.findItem("IC2", "itemBatREDischarged"),
							'M', new ItemStack(GameRegistry.findBlock("IC2",
									"blockMachine"), 1, 0), 'R', "dustRedstone"));

		}

		if (Loader.isModLoaded("ThermalExpansion")) {
			ItemStack leadStoneCell = new ItemStack(GameRegistry.findItem(
					"ThermalExpansion", "Cell"));
			ItemStack hardenedCell = new ItemStack(GameRegistry.findItem(
					"ThermalExpansion", "Cell"));
			ItemStack redstoneCell = new ItemStack(GameRegistry.findItem(
					"ThermalExpansion", "Cell"));
			ItemStack resonantCell = new ItemStack(GameRegistry.findItem(
					"ThermalExpansion", "Cell"));

			leadStoneCell.setItemDamage(1);
			hardenedCell.setItemDamage(2);
			redstoneCell.setItemDamage(3);
			resonantCell.setItemDamage(4);

			ItemStack rsC = new ItemStack(GameRegistry.findItem(
					"ThermalExpansion", "material"), 1, 3);

			GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.te4Blocks
					.get("rfChargerLeadstone"), "RLR", "LCL", "RcR", 'R',
					"dustRedstone", 'L', "ingotLead", 'C', leadStoneCell, 'c',
					rsC));
			GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.te4Blocks
					.get("rfChargerHardened"), "RLR", "LCL", "RcR", 'R',
					"dustRedstone", 'L', "ingotLead", 'C', hardenedCell, 'c',
					rsC));
			GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.te4Blocks
					.get("rfChargerRedstone"), "RLR", "LCL", "RcR", 'R',
					"dustRedstone", 'L', "ingotLead", 'C', redstoneCell, 'c',
					rsC));
			GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.te4Blocks
					.get("rfChargerResonant"), "RLR", "LCL", "RcR", 'R',
					"dustRedstone", 'L', "ingotLead", 'C', resonantCell, 'c',
					rsC));

		}

		if (Loader.isModLoaded("PeripheralsPlusPlus")) {
			ExtraPeripheralsMod.LOGGER
					.info("PPP detected! Adding ChatBox conversion recipe.");
			ItemStack pppChat = GameRegistry.findItemStack(
					"PeripheralsPlusPlus", "chatBox", 1);
			GameRegistry.addRecipe(pppChat, "   ", " C ", "   ", 'C',
					BlockRegistry.includedBlocks.get("chatBox"));
			GameRegistry.addRecipe(
					new ItemStack(BlockRegistry.includedBlocks.get("chatBox"),
							1, 1), "   ", " C ", "   ", 'C', pppChat);
		} else {
			recipes.put("chatBox", new ShapedOreRecipe(
					BlockRegistry.includedBlocks.get("chatBox"), "gng", "ndn",
					"gng", 'g', "ingotGold", 'n', Blocks.noteblock, 'd',
					"diamond"));
		}
	}

}
