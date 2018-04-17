package se.Matryoshika.Underworld.Integration.CraftTweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import se.Matryoshika.Underworld.API.MetamorphicTableRecipes;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
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
			// CraftTweakerAPI.ijeiRecipeRegistry.addRecipe(MetamorphicTableRecipes.getRecipe(input,
			// output, meta));
		}

		@Override
		protected void remove() {
			// CraftTweakerAPI.getIjeiRecipeRegistry().removeRecipe(MetamorphicTableRecipes.getRecipe(input,
			// output, meta));
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
			output = CraftTweakerMC.getItemStack(_output);
			input = CraftTweakerMC.getItemStack(_input);
			meta = _meta;
		} catch (IllegalArgumentException e) {
			CraftTweakerAPI.logError("Invalid MetamorphicTable recipe: " + e.getMessage());
		}

		CraftTweakerAPI.apply(new MetamorphicTableAction(input, output, meta).action_add);

	}

	@ZenMethod
	static public void removeRecipe(IItemStack input, IItemStack output, int meta) {
		if (!MetamorphicTableRecipes.recipeExists(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output), meta)) {
			CraftTweakerAPI.logWarning("Metamorphic Table recipe not found: " + CraftTweakerMC.getItemStack(input).getItem().getRegistryName() + " -> " + CraftTweakerMC.getItemStack(output).getItem().getRegistryName() + " with metadata of " + meta);
			return;
		}
		CraftTweakerAPI.apply(new MetamorphicTableAction(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output), meta).action_remove);
	}

	static public String getItemDescription(ItemStack stack) {
		ResourceLocation item = stack.getItem().getRegistryName();
		return String.format("I(%s:%s:%d,%d)", item.getResourceDomain(), item.getResourcePath(), stack.getItemDamage(), stack.getCount());
	}
}
