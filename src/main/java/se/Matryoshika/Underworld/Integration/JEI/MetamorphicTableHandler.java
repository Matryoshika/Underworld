package se.Matryoshika.Underworld.Integration.JEI;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.API.TableRecipes;

public class MetamorphicTableHandler implements IRecipeHandler<TableRecipes> {

	@Override
	public Class<TableRecipes> getRecipeClass() {
		return TableRecipes.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return Underworld.MODID+".metamorphictable";
	}

	@Override
	public String getRecipeCategoryUid(TableRecipes recipe) {
		return getRecipeCategoryUid();
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(TableRecipes recipe) {
		return new MetamorphicTableWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(TableRecipes recipe) {
		return true;
	}

}
