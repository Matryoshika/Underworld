package se.Matryoshika.Underworld.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class TableRecipes {
	
	ItemStack output;
	ItemStack input;
	int outputMeta;
	List<ItemStack> ingredient;
	public static HashMap linker = new HashMap();
	
	public TableRecipes(ItemStack input, ItemStack output, int outputMeta){
		this.input = input;
		this.output = output;
		this.outputMeta = outputMeta;
		linker.put(input, output);
		
		
		List<ItemStack> ingredientToAdd = new ArrayList();
		if(input instanceof ItemStack)
			ingredientToAdd.add(input);
		else
			throw new IllegalArgumentException("Invalid Metamorphic Table recipe-ingredient");
		
		this.ingredient = ingredientToAdd;
		
	}
	
	public int getOPMeta(){
		return outputMeta;
	}
	
	public int getOPStackSize(){
		return output.getCount();
	}
	
	public ItemStack result(){
		return output;
	}
	
	public List<ItemStack> getIngredient(){
		return new ArrayList(ingredient);
	}
	
	boolean compareItems(ItemStack stackx, ItemStack stacky) {
		return stackx.getItem() == stacky.getItem() && stackx.getItemDamage() == stacky.getItemDamage();
	}
	
	public boolean matches(IItemHandler inv) {
		List<ItemStack> inputsMissing = new ArrayList(ingredient);

		for(int i = 0; i < inv.getSlots(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack == null)
				break;

			int stackIndex = -1, oredictIndex = -1;

			for(int j = 0; j < inputsMissing.size(); j++) {
				ItemStack input = inputsMissing.get(j);
				 if(input instanceof ItemStack && compareItems((ItemStack) input, stack)) {
					stackIndex = j;
					break;
				}
			}

			if(stackIndex != -1)
				inputsMissing.remove(stackIndex);
			else if(oredictIndex != -1)
				inputsMissing.remove(oredictIndex);
			else return false;
		}

		return inputsMissing.isEmpty();
	}

}
