package se.Matryoshika.Underworld.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetamorphicTableRecipes {
	
	public static List<TableRecipes> metamorphicTableRecipes = new ArrayList<TableRecipes>();
	public static HashMap<String, ItemStack> registry = new HashMap<String, ItemStack>();
	
	
	public static TableRecipes registerTableRecipes(ItemStack input, ItemStack output, int outputMeta){
		registry.put(String.valueOf(input.getItem())+String.valueOf(input.getMetadata()), output);
		TableRecipes recipe = new TableRecipes(input, output, outputMeta);
		metamorphicTableRecipes.add(recipe);
		return recipe;
	}
	
	public static void removeTableRecipes(ItemStack input, ItemStack output, int outputMeta){
		System.out.println(input.getItem().getRegistryName() + " + " + output.getItem().getRegistryName() + " with meta of " + outputMeta);
		TableRecipes wantedRecipe = null;
		for(TableRecipes recipe : metamorphicTableRecipes){
			if(ItemStack.areItemsEqual(input, recipe.input) &&
				ItemStack.areItemsEqual(output, recipe.output) &&
				recipe.outputMeta == outputMeta){
				wantedRecipe = recipe;
			}
		}
		if(wantedRecipe != null){
			metamorphicTableRecipes.remove(wantedRecipe);
			registry.remove(String.valueOf(input.getItem())+String.valueOf(input.getMetadata()));
		}
		
	}
	
	public static boolean recipeExists(ItemStack input, ItemStack output, int outputMeta){
		boolean exists = false;
		for(TableRecipes recipe : metamorphicTableRecipes){
			if(ItemStack.areItemsEqual(input, recipe.input) &&
				ItemStack.areItemsEqual(output, recipe.output) &&
				recipe.outputMeta == outputMeta){
				exists = true;
			}
		}
		return exists;
	}

}
