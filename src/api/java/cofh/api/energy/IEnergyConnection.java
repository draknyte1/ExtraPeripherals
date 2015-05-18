package cofh.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Implement this interface on TileEntities which should connect to energy transportation IncludedBlocks. This is intended for IncludedBlocks which generate energy but do not
 * accept it; otherwise just use IEnergyHandler.
 * <p>
 * Note that {@link IEnergyHandler} is an extension of this.
 * 
 * @author King Lemming
 * 
 */
public interface IEnergyConnection {

	/**
	 * Returns TRUE if the TileEntity can connect on a given side.
	 */
	boolean canConnectEnergy(ForgeDirection from);

}
