package com.eyeball.minecraft.mods.extraperipherals.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;
import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;

public class ClickerItem extends ItemEPBase {

	IIcon[] icons;

	public ClickerItem() {
		setCreativeTab(EPCreativeTab.CREATIVETAB);
		setUnlocalizedName("mouse");
		icons = new IIcon[2];
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (!player.isSneaking())
			return stack;
		if (stack.getItemDamage() == 0) {
			stack.setItemDamage(1);
			String message = StatCollector
					.translateToLocal("extraperipherals.chat.changemode")
					+ StatCollector
							.translateToLocal("extraperipherals.chat.modeRC");
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(message));
			}
		} else {
			stack.setItemDamage(0);
			String message = StatCollector
					.translateToLocal("extraperipherals.chat.changemode")
					+ StatCollector
							.translateToLocal("extraperipherals.chat.modeRC");
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(message));
			}
		}
		return stack;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		try {
			return icons[damage];
		} catch (ArrayIndexOutOfBoundsException e) {
			return icons[0];
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		try {
			return icons[stack.getItemDamage()];
		} catch (ArrayIndexOutOfBoundsException e) {
			return icons[0];
		}
	}

	@Override
	public void registerIcons(IIconRegister register) {
		super.registerIcons(register);
		ExtraPeripheralsMod.LOGGER.info("Icon Registry class: "
				+ register.getClass().getName());
		icons[0] = register.registerIcon("ExtraPeripherals:mouse");
		icons[1] = register.registerIcon("ExtraPeripherals:rClick");
	}

}
