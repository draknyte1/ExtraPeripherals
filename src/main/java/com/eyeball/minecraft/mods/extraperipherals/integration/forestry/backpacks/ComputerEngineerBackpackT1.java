package com.eyeball.minecraft.mods.extraperipherals.integration.forestry.backpacks;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.storage.IBackpackDefinition;

public class ComputerEngineerBackpackT1 implements IBackpackDefinition {

	private List<ItemStack> valid = new LinkedList<ItemStack>();

	public ComputerEngineerBackpackT1() {
		addValidItem(GameRegistry.findItemStack("ComputerCraft", "CC-Computer",
				1));
		addValidItem(GameRegistry.findItemStack("ComputerCraft",
				"CC-Peripheral", 1));
		addValidItem(GameRegistry.findItemStack("ComputerCraft", "CC-Cable", 1));
		addValidItem(GameRegistry.findItemStack("CCTurtle", "CC-Turtle", 1));
		addValidItem(GameRegistry.findItemStack("CCTurtle",
				"CC-Turtle-Expanded", 1));
	}

	@Override
	public String getKey() {
		return "computer";
	}

	@Override
	public String getName(ItemStack backpack) {
		return "Computer Engineer's Backpack";
	}

	@Override
	public int getPrimaryColour() {
		return new Color(0x36187d).getRGB();
	}

	@Override
	public int getSecondaryColour() {
		return 0xffffff;
	}

	@Override
	public void addValidItem(ItemStack validItem) {
		if (validItem != null)
			valid.add(validItem);
	}

	@Override
	public void addValidItems(List<ItemStack> validItems) {
		valid.addAll(validItems);
	}

	@Override
	public boolean isValidItem(ItemStack itemstack) {
		itemstack.stackSize = 1;
		itemstack.setItemDamage(OreDictionary.WILDCARD_VALUE);
		return valid.contains(itemstack);
	}

	@Override
	public String getName() {
		return "Computer Engineer's Backpack";
	}

	@Override
	public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
		for (ItemStack entry : valid) {
			if (ItemStack.areItemStacksEqual(entry, itemstack))
				return true;
		}
		return false;
	}

}
