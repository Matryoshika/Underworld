package se.Matryoshika.Underworld.Integration.JEI;

import java.util.List;

import com.google.common.collect.ImmutableList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import se.Matryoshika.Underworld.API.TableRecipes;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;

public class MetamorphicTableWrapper implements IRecipeWrapper {
	
	private final List input;
	private final ItemStack output;
	
	
	public MetamorphicTableWrapper(TableRecipes recipe){
		input = recipe.getIngredient();
		ItemStack op = (ItemStack) input.get(0);
		output = TileMetamorphicTable.getOutputFromInput((ItemStack) input.get(0));
		
	}

	@Override
	public List getInputs() {
		return input;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return ImmutableList.of(output);
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		// TODO Auto-generated method stub
		return ImmutableList.of();
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		// TODO Auto-generated method stub
		return ImmutableList.of();
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return ImmutableList.of();
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}

	@Override
	public void getIngredients(IIngredients arg0) {
		// TODO Auto-generated method stub
		
	}

}
