package com.eyeball.minecraft.mods.extraperipherals.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.tile.TilePlayerDetector;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class BlockPlayerDetector extends BlockEPBase implements
		IPeripheralProvider {

	IIcon icon;
	
	protected BlockPlayerDetector() {
		super(Material.iron);
	}

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (tile instanceof TilePlayerDetector) {
			return new TilePlayerDetector();
		}
		return null;
	}

	@Override
	public String getUnlocalizedName() {
		return "playerDetector";
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z_,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		for (TilePlayerDetector pd : TilePlayerDetector.PlayerDetectorRegistry.playerDetectors
				.keySet()) {
			pd.onBlockActivated(world, x, y, z_, player, side, hitX, hitY, hitZ);
		}
		return true;
	}

	@Override
	public IIcon getIcon(int meta, int side) {
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		icon = p_149651_1_.registerIcon("ExtraPeripherals:playerDetector");
	}
	
}
