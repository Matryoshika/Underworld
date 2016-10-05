package se.Matryoshika.Underworld.API;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Underworld.Content.ContentRegistry;

public class UnderworldMetamorphicTableRecipes {

	private static TableRecipes recipeOakSapling;
	private static TableRecipes recipeSpruceSapling;
	private static TableRecipes recipeBirchSapling;
	private static TableRecipes recipeJungleSapling;
	private static TableRecipes recipeAcaciaSapling;
	private static TableRecipes recipeDarkOakSapling;
	
	private static TableRecipes recipeCactus;
	private static TableRecipes recipeLilyPad;
	private static TableRecipes recipeVines;
	
	private static TableRecipes recipeSunFlower;
	private static TableRecipes recipeLilac;
	private static TableRecipes recipeTallGrass;
	private static TableRecipes recipeLargeFerns;
	private static TableRecipes recipeRoseBush;
	private static TableRecipes recipePeony;
	
	
	private static TableRecipes recipeSugarBeets;
	private static TableRecipes recipeSugarCane;
	
	private static TableRecipes recipeYellowFlower;
	private static TableRecipes recipeRedFlower;
	private static TableRecipes recipeBlueOrchid;
	private static TableRecipes recipeAllium;
	private static TableRecipes recipeAzureBluet;
	private static TableRecipes recipeRedTulip;
	private static TableRecipes recipeOrangeTulip;
	private static TableRecipes recipeWhiteTulip;
	private static TableRecipes recipePinkTulip;
	private static TableRecipes recipeOxeyeDaisy;
	
	private static TableRecipes recipeDisc13;
	private static TableRecipes recipeDiscCat;
	private static TableRecipes recipeDiscBlocks;
	private static TableRecipes recipeDiscChirp;
	private static TableRecipes recipeDiscFar;
	private static TableRecipes recipeDiscMall;
	private static TableRecipes recipeDiscMellohi;
	private static TableRecipes recipeDiscStal;
	private static TableRecipes recipeDiscStrad;
	private static TableRecipes recipeDiscWard;
	private static TableRecipes recipeDisc11;
	private static TableRecipes recipeDiscWait;
	
	private static TableRecipes recipeEnderPorter;
	
	
	
	static ItemStack OAKSAP = new ItemStack(Blocks.SAPLING, 1, 0);
	static ItemStack SPRUCESAP = new ItemStack(Blocks.SAPLING, 1, 1);
	static ItemStack BIRCHSAP = new ItemStack(Blocks.SAPLING, 1, 2);
	static ItemStack JUNGLESAP = new ItemStack(Blocks.SAPLING, 1, 3);
	static ItemStack ACACIASAP = new ItemStack(Blocks.SAPLING, 1, 4);
	static ItemStack DARKOAKSAP = new ItemStack(Blocks.SAPLING, 1, 5);
	
	static ItemStack SUNFLOWER = new ItemStack(Blocks.DOUBLE_PLANT, 1, 0);
	static ItemStack LILAC = new ItemStack(Blocks.DOUBLE_PLANT, 1, 1);
	static ItemStack TALLGRASS = new ItemStack(Blocks.DOUBLE_PLANT, 1, 2);
	static ItemStack LARGEFERNS = new ItemStack(Blocks.DOUBLE_PLANT, 1, 3);
	static ItemStack ROSEBUSH = new ItemStack(Blocks.DOUBLE_PLANT, 1, 4);
	static ItemStack PEONY = new ItemStack(Blocks.DOUBLE_PLANT, 1, 5);
	
	static ItemStack CACTUS = new ItemStack(Blocks.CACTUS);
	static ItemStack LILYPAD = new ItemStack(Blocks.WATERLILY);
	static ItemStack VINES = new ItemStack(Blocks.VINE);
	
	static ItemStack FLOWERYELLOW = new ItemStack(Blocks.YELLOW_FLOWER, 1, 0);
	static ItemStack FLOWERRED = new ItemStack(Blocks.RED_FLOWER, 1, 0);
	static ItemStack FLOWERBLUEORCHID = new ItemStack(Blocks.RED_FLOWER, 1, 1);
	static ItemStack FLOWERALLIUM = new ItemStack(Blocks.RED_FLOWER, 1, 2);
	static ItemStack FLOWERAZUREBLUET = new ItemStack(Blocks.RED_FLOWER, 1, 3);
	static ItemStack FLOWERREDTULIP = new ItemStack(Blocks.RED_FLOWER, 1, 4);
	static ItemStack FLOWERORANGETULIP = new ItemStack(Blocks.RED_FLOWER, 1, 5);
	static ItemStack FLOWERWHITETULIP = new ItemStack(Blocks.RED_FLOWER, 1, 6);
	static ItemStack FLOWERPINKTULIP = new ItemStack(Blocks.RED_FLOWER, 1, 7);
	static ItemStack FLOWEROXEYEDAISY = new ItemStack(Blocks.RED_FLOWER, 1, 8);
	
	static ItemStack DISC13 = new ItemStack(Items.RECORD_13, 1,0);
	static ItemStack DISCCAT = new ItemStack(Items.RECORD_CAT, 1,0);
	static ItemStack DISCBLOCKS = new ItemStack(Items.RECORD_BLOCKS, 1,0);
	static ItemStack DISCCHIRP = new ItemStack(Items.RECORD_CHIRP, 1,0);
	static ItemStack DISCFAR = new ItemStack(Items.RECORD_FAR, 1,0);
	static ItemStack DISCMALL = new ItemStack(Items.RECORD_MALL, 1,0);
	static ItemStack DISCMELLOHI = new ItemStack(Items.RECORD_MELLOHI, 1,0);
	static ItemStack DISCSTAL = new ItemStack(Items.RECORD_STAL, 1,0);
	static ItemStack DISCSTRAD = new ItemStack(Items.RECORD_STRAD, 1,0);
	static ItemStack DISCWARD = new ItemStack(Items.RECORD_WARD, 1,0);
	static ItemStack DISC11 = new ItemStack(Items.RECORD_11, 1,0);
	static ItemStack DISCWAIT = new ItemStack(Items.RECORD_WAIT, 1,0);
	
	static ItemStack ENDCRYSTAL = new ItemStack(Items.END_CRYSTAL);
	static ItemStack ENDERPORTER = new ItemStack(ContentRegistry.BlockCustomEndPortal);
	
	public static void init(){
		
		recipeOakSapling = MetamorphicTableRecipes.registerTableRecipes(DARKOAKSAP, OAKSAP, 0);
		recipeSpruceSapling = MetamorphicTableRecipes.registerTableRecipes(OAKSAP, SPRUCESAP, 1);
		recipeBirchSapling = MetamorphicTableRecipes.registerTableRecipes(SPRUCESAP, BIRCHSAP, 2);
		recipeJungleSapling = MetamorphicTableRecipes.registerTableRecipes(BIRCHSAP, JUNGLESAP, 3);
		recipeAcaciaSapling = MetamorphicTableRecipes.registerTableRecipes(JUNGLESAP, ACACIASAP, 4);
		recipeDarkOakSapling = MetamorphicTableRecipes.registerTableRecipes(ACACIASAP, DARKOAKSAP, 5);
		
		recipeSunFlower = MetamorphicTableRecipes.registerTableRecipes(PEONY, SUNFLOWER, 0);
		recipeLilac = MetamorphicTableRecipes.registerTableRecipes(SUNFLOWER, LILAC, 0);
		recipeTallGrass = MetamorphicTableRecipes.registerTableRecipes(LILAC, TALLGRASS, 0);
		recipeLargeFerns = MetamorphicTableRecipes.registerTableRecipes(TALLGRASS, LARGEFERNS, 0);
		recipeRoseBush = MetamorphicTableRecipes.registerTableRecipes(LARGEFERNS, ROSEBUSH, 0);
		recipePeony = MetamorphicTableRecipes.registerTableRecipes(ROSEBUSH, PEONY, 0);
		
		recipeCactus = MetamorphicTableRecipes.registerTableRecipes(LILYPAD, CACTUS, 0);
		recipeVines = MetamorphicTableRecipes.registerTableRecipes(CACTUS, VINES, 0);
		recipeLilyPad = MetamorphicTableRecipes.registerTableRecipes(VINES, LILYPAD, 0);
		
		recipeYellowFlower = MetamorphicTableRecipes.registerTableRecipes(FLOWEROXEYEDAISY, FLOWERYELLOW, 0);
		recipeRedFlower = MetamorphicTableRecipes.registerTableRecipes(FLOWERYELLOW, FLOWERRED, 0);
		recipeBlueOrchid = MetamorphicTableRecipes.registerTableRecipes(FLOWERRED, FLOWERBLUEORCHID, 1);
		recipeAllium = MetamorphicTableRecipes.registerTableRecipes(FLOWERBLUEORCHID, FLOWERALLIUM, 2);
		recipeAzureBluet = MetamorphicTableRecipes.registerTableRecipes(FLOWERALLIUM, FLOWERAZUREBLUET, 3);
		recipeRedTulip = MetamorphicTableRecipes.registerTableRecipes(FLOWERAZUREBLUET, FLOWERREDTULIP, 4);
		recipeOrangeTulip = MetamorphicTableRecipes.registerTableRecipes(FLOWERREDTULIP, FLOWERORANGETULIP, 5);
		recipeWhiteTulip = MetamorphicTableRecipes.registerTableRecipes(FLOWERORANGETULIP, FLOWERWHITETULIP, 6);
		recipePinkTulip = MetamorphicTableRecipes.registerTableRecipes(FLOWERWHITETULIP, FLOWERPINKTULIP, 7);
		recipeOxeyeDaisy = MetamorphicTableRecipes.registerTableRecipes(FLOWERPINKTULIP, FLOWEROXEYEDAISY, 8);
		
		recipeDisc13 = MetamorphicTableRecipes.registerTableRecipes(DISCWAIT, DISC13, 0);
		recipeDiscCat = MetamorphicTableRecipes.registerTableRecipes(DISC13, DISCCAT, 0);
		recipeDiscBlocks = MetamorphicTableRecipes.registerTableRecipes(DISCCAT, DISCBLOCKS, 0);
		recipeDiscChirp = MetamorphicTableRecipes.registerTableRecipes(DISCBLOCKS, DISCCHIRP, 0);
		recipeDiscFar = MetamorphicTableRecipes.registerTableRecipes(DISCCHIRP, DISCFAR, 0);
		recipeDiscMall = MetamorphicTableRecipes.registerTableRecipes(DISCFAR, DISCMALL, 0);
		recipeDiscMellohi = MetamorphicTableRecipes.registerTableRecipes(DISCMALL, DISCMELLOHI, 0);
		recipeDiscStal = MetamorphicTableRecipes.registerTableRecipes(DISCMELLOHI, DISCSTAL, 0);
		recipeDiscStrad = MetamorphicTableRecipes.registerTableRecipes(DISCSTAL, DISCSTRAD, 0);
		recipeDiscWard = MetamorphicTableRecipes.registerTableRecipes(DISCSTRAD, DISCWARD, 0);
		recipeDisc11 = MetamorphicTableRecipes.registerTableRecipes(DISCWARD, DISC11, 0);
		recipeDiscWait = MetamorphicTableRecipes.registerTableRecipes(DISC11, DISCWAIT, 0);
		
		recipeEnderPorter = MetamorphicTableRecipes.registerTableRecipes(ENDCRYSTAL, ENDERPORTER, 0);
	}
}
