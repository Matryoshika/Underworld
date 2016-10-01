package se.Matryoshika.Underworld.Content.TileEntity.InvHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CustomOneSlot extends SlotItemHandler{

	public CustomOneSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		
	}
	
	@Override
	public int getSlotStackLimit(){
        return 1;
    }
	
	
	@Override
    public int getItemStackLimit(ItemStack stack){
        return 1;
    }

}
