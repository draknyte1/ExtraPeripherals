package com.eyeball.minecraft.mods.extraperipherals.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;
import com.eyeball.minecraft.mods.extraperipherals.tile.TileChatBox;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class BlockChatBox extends BlockEPBase implements IPeripheralProvider,
		ITileEntityProvider {

	IIcon icon;

	public BlockChatBox() {
		super(Material.iron);
		setCreativeTab(EPCreativeTab.CREATIVETAB);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileChatBox(world);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		icon = register.registerIcon("ExtraPeripherals:chatBox");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icon;
	}

	@Override
	public String getUnlocalizedName() {
		return "chatBox";
	}

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		return (TileChatBox) world.getTileEntity(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileChatBox(world);
	}

}
