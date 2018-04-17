package se.Matryoshika.Underworld.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.Content.Blocks.BlockBrazierOff;
import se.Matryoshika.Underworld.Content.Blocks.BlockBrazierOn;
import se.Matryoshika.Underworld.Content.Blocks.BlockCustomEndPortal;
import se.Matryoshika.Underworld.Content.Blocks.BlockCustomLight;
import se.Matryoshika.Underworld.Content.Blocks.BlockGlowingMossStone;
import se.Matryoshika.Underworld.Content.Blocks.BlockHangVine;
import se.Matryoshika.Underworld.Content.Blocks.BlockInvisMobSpawner;
import se.Matryoshika.Underworld.Content.Blocks.BlockLumeniteLantern;
import se.Matryoshika.Underworld.Content.Blocks.BlockMetamorphicTable;
import se.Matryoshika.Underworld.Content.Blocks.BlockMossStone;
import se.Matryoshika.Underworld.Content.Blocks.BlockSugarPile;
import se.Matryoshika.Underworld.Content.Blocks.BlockSugarbeet;
import se.Matryoshika.Underworld.Content.Blocks.BlockUnderworldDirt;
import se.Matryoshika.Underworld.Content.Items.ItemBlockShiny;
import se.Matryoshika.Underworld.Content.Items.ItemDebugger;
import se.Matryoshika.Underworld.Content.Items.ItemLantern;
import se.Matryoshika.Underworld.Content.Items.ItemLumeniteIngot;
import se.Matryoshika.Underworld.Content.Items.ItemLumeniteNugget;
import se.Matryoshika.Underworld.Content.Items.ItemSugarbeet;

@Mod.EventBusSubscriber(modid = Underworld.MODID)
public class ContentRegistry {

	// Blocks-------------------------------------------------------------------------------------------

	public static Block BlockHangVine;
	public static Block BlockDirt;
	public static Block BlockBrazierOff;
	public static Block BlockBrazierOn;
	public static Block Light;
	public static Block Spawner;
	public static Block BlockSugarBeets;
	public static Block BlockMetamorphicTable;
	public static Block BlockCustomEndPortal;
	public static Block BlockMossStone;
	public static Block BlockGlowMossStone;
	public static Block BlockSugarPile;
	public static Block BlockLumeniteLantern;

	public static List<Block> BlockList = new ArrayList<Block>();

	public static void prepareBlocks() {
		BlockList.add(BlockHangVine = new BlockHangVine());
		BlockList.add(BlockDirt = new BlockUnderworldDirt());
		BlockList.add(BlockBrazierOff = new BlockBrazierOff());
		BlockList.add(BlockBrazierOn = new BlockBrazierOn().setLightLevel(15F));
		BlockList.add(Light = new BlockCustomLight());
		BlockList.add(Spawner = new BlockInvisMobSpawner());
		BlockList.add(BlockSugarBeets = new BlockSugarbeet().setRegistryName("blocksugarbeet").setUnlocalizedName("underworld:blocksugarbeet"));
		BlockList.add(BlockMetamorphicTable = new BlockMetamorphicTable());
		BlockList.add(BlockCustomEndPortal = new BlockCustomEndPortal());
		BlockList.add(BlockMossStone = new BlockMossStone());
		BlockList.add(BlockGlowMossStone = new BlockGlowingMossStone().setLightLevel(5F));
		BlockList.add(BlockSugarPile = new BlockSugarPile().setLightLevel(5F));
		BlockList.add(BlockLumeniteLantern = new BlockLumeniteLantern().setLightLevel(15));

	}

	@SubscribeEvent
	public static void addBlocks(Register<Block> evt) {
		prepareBlocks();
		prepareItems();
		Underworld.log.error("Registered blocks ###########################################################################");
		BlockList.forEach(evt.getRegistry()::register);
	}

	@SubscribeEvent
	public static void addItems(Register<Item> evt) {
		ItemList.forEach(evt.getRegistry()::register);

		for (Block block : BlockList) {
			ItemBlock iblock;
			if (block == ContentRegistry.BlockCustomEndPortal)
				iblock = new ItemBlockShiny(block);
			else
				iblock = new ItemBlock(block);

			evt.getRegistry().registerAll(iblock.setRegistryName(block.getRegistryName()));
		}
	}

	// Items--------------------------------------------------------------------------------------------

	public static Item Debugger;
	public static Item Lantern;
	public static Item Sugarbeets;
	public static Item FireflyShield;
	public static Item Lumenite;
	public static Item LumeniteIngot;

	public static List<Item> ItemList = new ArrayList<Item>();

	public static void prepareItems() {

		ItemList.add(Debugger = new ItemDebugger());
		ItemList.add(Lantern = new ItemLantern());
		ItemList.add(Sugarbeets = new ItemSugarbeet());
		// ItemList.add(FireflyShield = new ItemFireflyShield());
		ItemList.add(Lumenite = new ItemLumeniteNugget());
		ItemList.add(LumeniteIngot = new ItemLumeniteIngot());

	}

}
