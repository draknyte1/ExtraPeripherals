package com.eyeball.minecraft.mods.extraperipherals.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.eyeball.minecraft.mods.extraperipherals.ExtraPeripheralsMod;
import com.eyeball.minecraft.mods.extraperipherals.tile.TilePlayerDetector;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class BlockPlayerDetector extends BlockEPBase implements
		IPeripheralProvider {
	TilePlayerDetector detector;

	IIcon icon;

	public BlockPlayerDetector() {
		super(Material.iron);
		detector = new TilePlayerDetector();
	}

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null)
			return null;
		if (tile instanceof TilePlayerDetector) {
			return detector;
		}
		return null;
	}

	@Override
	public String getUnlocalizedName() {
		return "playerDetector";
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ExtraPeripheralsMod.LOGGER.info("Clicked!");
		for (TilePlayerDetector pd : TilePlayerDetector.PlayerDetectorRegistry.playerDetectors
				.keySet()) {
			pd.onBlockActivated(player);
		}
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY,
				hitZ);
	}

	@Override
	public IIcon getIcon(int meta, int side) {
		return icon;
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		icon = register.registerIcon("ExtraPeripherals:playerDetector");
	}

}
