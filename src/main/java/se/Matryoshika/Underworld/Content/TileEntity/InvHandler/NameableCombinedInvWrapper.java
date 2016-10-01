package se.Matryoshika.Underworld.Content.TileEntity.InvHandler;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IWorldNameable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class NameableCombinedInvWrapper extends CombinedInvWrapper implements IItemHandler, IWorldNameable{

	private final IWorldNameable worldNameable;

	public NameableCombinedInvWrapper(IWorldNameable worldNameable, IItemHandlerModifiable... itemHandler) {
		super(itemHandler);
		this.worldNameable = worldNameable;
	}

	@Override
	public String getName() {
		return worldNameable.getName();
	}

	@Override
	public boolean hasCustomName() {
		return worldNameable.hasCustomName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return worldNameable.getDisplayName();
	}

}
