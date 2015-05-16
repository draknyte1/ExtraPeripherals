/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.carts;

import net.minecraft.item.ItemStack;

import com.mojang.authlib.GameProfile;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public interface IRoutableCart {

    String getDestination();

    boolean setDestination(ItemStack ticket);
    
    GameProfile getOwner();

}
