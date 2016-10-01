package se.Matryoshika.Underworld.Integration.JEI;

import java.awt.Point;
import java.util.Collection;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class MetamorphicTableCategory implements IRecipeCategory {
	
	public static final String UID = Underworld.MODID+".metamorphictable";
	private final IDrawable background;
	private final String localizedName;
	private final IDrawable overlay;
	private final IDrawableAnimated arrow;
	protected final ResourceLocation backgroundLocation;
	private final ICraftingGridHelper craftingGridHelper;
	private static final int OUTPUT_SLOT = 2;
	private static final int INPUT_SLOT = 0;
	private static final int TABLE_SLOT = 1;
	
	public MetamorphicTableCategory(IGuiHelper guihelper){
		backgroundLocation = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
		
		background = guihelper.createBlankDrawable(150, 110);
		localizedName = I18n.format(Underworld.MODID+".jei.metamorphictable");
		overlay = guihelper.createDrawable(new ResourceLocation(Underworld.MODID, "textures/gui/jeimetamorphictable.png"), 0, 0, 150, 110);
		IDrawableStatic arrowDrawable = guihelper.createDrawable(backgroundLocation, 176, 14, 24, 17);
		arrow = guihelper.createAnimatedDrawable(arrowDrawable, 100, IDrawableAnimated.StartDirection.LEFT, false);
		craftingGridHelper = guihelper.createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
		
		
	}

	@Nonnull
	@Override
	public String getUid() {
		return UID;
	}

	@Nonnull
	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		overlay.draw(minecraft);
		
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();

	}

	@Override
	public void drawAnimations(@Nonnull Minecraft minecraft) {
		arrow.draw(minecraft, 58, 38);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		if(!(recipeWrapper instanceof MetamorphicTableWrapper))
			return;
		
		MetamorphicTableWrapper wrapper = (MetamorphicTableWrapper) recipeWrapper;
		recipeLayout.getItemStacks().init(INPUT_SLOT, true, 48, 20);
		
		for(Object o : wrapper.getInputs()) {
			if(o instanceof Collection) {
				recipeLayout.getItemStacks().set(INPUT_SLOT, ((Collection<ItemStack>) o));
			}
			if(o instanceof ItemStack) {
				recipeLayout.getItemStacks().set(INPUT_SLOT, ((ItemStack) o));
			}
		}
		
		recipeLayout.getItemStacks().init(TABLE_SLOT, false, 48, 50);
		recipeLayout.getItemStacks().init(OUTPUT_SLOT, false, 84, 36);
		recipeLayout.getItemStacks().set(TABLE_SLOT, new ItemStack(ContentRegistry.BlockMetamorphicTable));
		
		recipeLayout.getItemStacks().set(OUTPUT_SLOT, wrapper.getOutputs().get(0));

	}

}
