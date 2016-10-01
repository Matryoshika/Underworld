package se.Matryoshika.Underworld.Content.TileEntity.InvHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;

public class ItemHandlerItem extends ItemHandlerNameable{

	protected final IWorldContainer worldContainer;

	protected ResourceLocation lootTableLocation;

	protected long lootTableSeed;

	public ItemHandlerItem(ITextComponent defaultName, IWorldContainer worldContainer) {
		super(defaultName);
		this.worldContainer = worldContainer;
	}

	public ItemHandlerItem(int size, ITextComponent defaultName, IWorldContainer worldContainer) {
		super(size, defaultName);
		this.worldContainer = worldContainer;
	}

	public ItemHandlerItem(ItemStack[] stacks, ITextComponent defaultName, IWorldContainer worldContainer) {
		super(stacks, defaultName);
		this.worldContainer = worldContainer;
	}


	@Override
	public NBTTagCompound serializeNBT() {
		final NBTTagCompound tagCompound = super.serializeNBT();

		return tagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		super.deserializeNBT(nbt);
	}


	@Override
	public ItemStack getStackInSlot(int slot) {
		return super.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return super.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return super.extractItem(slot, amount, simulate);
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		super.setStackInSlot(slot, stack);
	}
	
}
