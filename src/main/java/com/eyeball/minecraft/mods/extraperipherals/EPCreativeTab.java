package com.eyeball.minecraft.mods.extraperipherals;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockRegistry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.turtle.ITurtleUpgrade;

public class EPCreativeTab {

	public static final List<ITurtleUpgrade> upgrades = new LinkedList<ITurtleUpgrade>();

	public static final CreativeTabs CREATIVETAB = new CreativeTabs(
			Reference.MODID.toLowerCase()) {

		@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(BlockRegistry.includedBlocks
					.get("chatBox"));
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@SideOnly(Side.CLIENT)
		@Override
		public void displayAllReleventItems(List list) {
			super.displayAllReleventItems(list);
			ItemStack base = GameRegistry.findItemStack("ComputerCraft",
					"CC-TurtleExpanded", 1);
			if (base != null) {
				for (ITurtleUpgrade upgrade : upgrades) {
					ItemStack upg1 = base.copy();
					upg1.stackTagCompound = new NBTTagCompound();
					upg1.stackTagCompound.setShort("leftUpgrade",
							(short) upgrade.getUpgradeID());
					list.add(upg1);
				}
			}
			ItemStack base2 = GameRegistry.findItemStack("ComputerCraft",
					"CC-TurtleAdvanced", 1);
			if (base2 == null)
				return;
			for (ITurtleUpgrade upgrade : upgrades) {
				ItemStack upg1 = base2.copy();
				upg1.stackTagCompound = new NBTTagCompound();
				upg1.stackTagCompound.setShort("leftUpgrade",
						(short) upgrade.getUpgradeID());
				list.add(upg1);
			}

		};
	};
}