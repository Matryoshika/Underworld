package se.Matryoshika.Underworld.Integration.CraftTweaker;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.collect.ImmutableMap;

import mezz.jei.Internal;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.RecipeTransferRegistry;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import se.Matryoshika.Underworld.API.MetamorphicTableRecipes;
import se.Matryoshika.Underworld.Integration.JEI.MetamorphicTableCategory;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.underworld.metamorphictable")
public class MTMetamorphicTableRecipes {

	public static class MetamorphicTableAction extends AddRemoveAction {

		ItemStack input;
		ItemStack output;
		int meta;

		public MetamorphicTableAction(ItemStack input, ItemStack output, int meta) {
			this.input = input;
			this.output = output;
			this.meta = meta;
		}

		@Override
		protected void add() {
			MetamorphicTableRecipes.registerTableRecipes(input, output, meta);
			MineTweakerAPI.ijeiRecipeRegistry.addRecipe(MetamorphicTableRecipes.getRecipe(input, output, meta));
		}

		@Override
		protected void remove() {
			MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(MetamorphicTableRecipes.getRecipe(input, output, meta));
			MetamorphicTableRecipes.removeTableRecipes(input, output, meta);
		}

		@Override
		public String getDescription() {
			return String.format(" %s -> %s ", getItemDescription(this.input), getItemDescription(this.output));
		}

		@Override
		public String getRecipeType() {
			return "metamorphic table";
		}

	}

	@ZenMethod
	static public void addRecipe(IItemStack _input, IItemStack _output, int _meta) {
		ItemStack input = null;
		ItemStack output = null;
		int meta = 0;

		try {
			output = MineTweakerMC.getItemStack(_output);
			input = MineTweakerMC.getItemStack(_input);
			meta = _meta;
		} catch (IllegalArgumentException e) {
			MineTweakerAPI.logError("Invalid MetamorphicTable recipe: " + e.getMessage());
		}

		MineTweakerAPI.apply(new MetamorphicTableAction(input, output, meta).action_add);

	}

	@ZenMethod
	static public void removeRecipe(IItemStack input, IItemStack output, int meta) {
		if (!MetamorphicTableRecipes.recipeExists(MineTweakerMC.getItemStack(input), MineTweakerMC.getItemStack(output),
				meta)) {
			MineTweakerAPI.logWarning("Metamorphic Table recipe not found: "
					+ MineTweakerMC.getItemStack(input).getItem().getRegistryName() + " -> "
					+ MineTweakerMC.getItemStack(output).getItem().getRegistryName() + " with metadata of " + meta);
			return;
		}
		MineTweakerAPI.apply(new MetamorphicTableAction(MineTweakerMC.getItemStack(input),
				MineTweakerMC.getItemStack(output), meta).action_remove);
	}

	static public String getItemDescription(ItemStack stack) {
		ResourceLocation item = stack.getItem().getRegistryName();
		return String.format("I(%s:%s:%d,%d)", item.getResourceDomain(), item.getResourcePath(), stack.getItemDamage(),
				stack.stackSize);
	}
}
