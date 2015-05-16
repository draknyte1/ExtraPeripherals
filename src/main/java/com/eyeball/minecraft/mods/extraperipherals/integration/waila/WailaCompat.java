package com.eyeball.minecraft.mods.extraperipherals.integration.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.block.BlockEPBase;

public class WailaCompat implements IWailaDataProvider {

	public static final WailaCompat INSTANCE = new WailaCompat();

	@SuppressWarnings("unused")
	private static IWailaDataAccessor _accessor = null;
	public static void register(IWailaRegistrar registrar) {

	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		return new ItemStack(Items.diamond);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {

		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {

		if (Block.getBlockFromItem(itemStack.getItem()) != null) {
			if (Block.getBlockFromItem(itemStack.getItem()) instanceof BlockEPBase) {
				currenttip.add("Awesome as everything!");
			}
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te,
			NBTTagCompound tag, World world, int x, int y, int z) {

		return tag;
	}

}
