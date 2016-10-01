package se.Matryoshika.Underworld.Content.TileEntity.InvHandler;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import se.Matryoshika.Underworld.Content.TileEntity.CustomTileClass;

public abstract class TEInventoryHandler<INVENTORY extends IItemHandler & INBTSerializable<NBTTagCompound>> extends TileEntityBeacon{


	/*
	 * Massive thanks to Choonster's TestMod3 GitHub!
	 * Capabilities makes my head hurt
	 */
	
	
	public final INVENTORY inventory = createInventory();
	protected abstract INVENTORY createInventory();
	
	public abstract Container createContainer(EntityPlayer player);
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		inventory.deserializeNBT(nbt.getCompoundTag("ItemHandler"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setTag("ItemHandler", inventory.serializeNBT());
		return nbt;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		
		return super.getCapability(capability, facing);
	}
	
}
