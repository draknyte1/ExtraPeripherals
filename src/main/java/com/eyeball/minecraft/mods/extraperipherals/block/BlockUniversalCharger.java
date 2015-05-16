package com.eyeball.minecraft.mods.extraperipherals.block;

import ic2.api.energy.tile.IEnergySink;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyReceiver;

import com.eyeball.minecraft.mods.extraperipherals.EPCreativeTab;
import com.eyeball.minecraft.mods.extraperipherals.tile.TileCharger;
import com.eyeball.minecraft.mods.extraperipherals.tile.TileChatBox;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class BlockUniversalCharger extends BlockEPBase implements IPeripheralProvider,
		ITileEntityProvider, IEnergySink, IEnergyReceiver {
	
	public int energyStored = 0;
	public int maxEnergyStored = 10000000;

	IIcon icon;

	public BlockUniversalCharger() {
		super(Material.iron);
		setCreativeTab(EPCreativeTab.CREATIVETAB);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileCharger(world, this);
	}

	@Override
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		icon = register.registerIcon("ExtraPeripherals:charger");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icon;
	}

	@Override
	public String getUnlocalizedName() {
		return "universalCharger";
	}

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		return (TileChatBox) world.getTileEntity(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileChatBox(world);
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter,
			ForgeDirection direction) {
		return true;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		return 1000;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energyStored;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return maxEnergyStored;
	}

	@Override
	public double getDemandedEnergy() {
		return 1000;
	}

	@Override
	public int getSinkTier() {
		return 3;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount,
			double voltage) {
		energyStored += amount;
		return 0;
	}

}
