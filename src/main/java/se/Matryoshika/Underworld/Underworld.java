package se.Matryoshika.Underworld;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import se.Matryoshika.Underworld.API.UnderworldMetamorphicTableRecipes;
import se.Matryoshika.Underworld.Content.ContentRegistry;
import se.Matryoshika.Underworld.Content.RecipeManager;
import se.Matryoshika.Underworld.Content.TileRegistry;
import se.Matryoshika.Underworld.Events.PlayerTicker;
import se.Matryoshika.Underworld.Events.UnderworldEventHandler;
import se.Matryoshika.Underworld.Events.UnderworldMapEventHandler;
import se.Matryoshika.Underworld.Integration.CraftTweaker.MTIntegration;
import se.Matryoshika.Underworld.Utils.BiomeType;
import se.Matryoshika.Underworld.Utils.ConfigHandler;
import se.Matryoshika.Underworld.Utils.CreativeTabUnderworld;
import se.Matryoshika.Underworld.Utils.SugarPileList;
import se.Matryoshika.Underworld.WorldGen.WorldProviderCaves;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;
import se.Matryoshika.Underworld.WorldGen.Dirty.CustomWorldGenerators;

@Mod(modid = Underworld.MODID, version = Underworld.VERSION, name = "Underworld", clientSideOnly = false, serverSideOnly = false)
@Mod.EventBusSubscriber
public class Underworld {

	public static final String MODID = "underworld";
	public static final String LOCALIZING = "UW";
	public static final String VERSION = "0.3.12";
	public static Configuration mainConfig;
	public static Configuration itemConfig;
	public static Configuration blockConfig;
	public static Configuration genConfig;
	
	public static String pathName;

	static WorldType CAVES;
	private static int dimID = 0;

	private final UnderworldMapEventHandler INIT_MAP_GEN_EVENT_HANDLER = new UnderworldMapEventHandler();
	private final PlayerTicker PLAYER_TICKER = new PlayerTicker();
	private final ContentRegistry REGISTRY = new ContentRegistry();

	public static final CreativeTabUnderworld UnderworldTab = new CreativeTabUnderworld("Underworld") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return new ItemStack(ContentRegistry.Lantern).getItem();
		}
	};

	@EventHandler
	public void serverStarting(FMLServerAboutToStartEvent event) {
		MinecraftForge.EVENT_BUS.register(REGISTRY);
	}

	@Instance("Underworld")
	public static Underworld instance;

	@SidedProxy(clientSide = "se.Matryoshika.Underworld.ClientProxy", serverSide = "se.Matryoshika.Underworld.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.readMain();

		TileRegistry.registerTiles();

		ConfigHandler.setItemAndBlockConfigs();

		BiomeType.init();
		proxy.preInit(event);
		RecipeManager.registerRecipes();

		MinecraftForge.TERRAIN_GEN_BUS.register(INIT_MAP_GEN_EVENT_HANDLER);
		MinecraftForge.EVENT_BUS.register(PLAYER_TICKER);
		MinecraftForge.EVENT_BUS.register(new UnderworldEventHandler());

		UnderworldMetamorphicTableRecipes.init();
		
		if(Loader.isModLoaded("MineTweaker3"))
			MTIntegration.onInit();
		
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		proxy.init(event);

		// See WorldProviderCaves on how this doesn't mess up vanilla WorldTypes
		DimensionManager.unregisterDimension(dimID);
		DimensionManager.registerDimension(dimID, DimensionType.register("CAVES", "_caves", dimID, WorldProviderCaves.class, true));

		CustomWorldGenerators.register();

		OreDictionary.registerOre("string", ContentRegistry.BlockHangVine);
		OreDictionary.registerOre("sugarcane", ContentRegistry.Sugarbeets);
		MinecraftForge.addGrassSeed(new ItemStack(ContentRegistry.Sugarbeets), 3);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);

		CAVES = new WorldTypeCaves("CAVES");

	}

	public static WorldType getCaves() {
		return CAVES;
	}

	public static Underworld getMod() {
		return instance;
	}

	@SubscribeEvent
	public static void onUse(RightClickBlock event) {

		if (event.getItemStack() != null && event.getItemStack().getItem() == Items.SUGAR) {

			for (BlockPos pos : BlockPos.getAllInBox(event.getPos().add(-5, -3, -5), event.getPos().add(5, 3, 5))) {
				if (event.getEntityPlayer().worldObj.getBlockState(pos).getBlock() == ContentRegistry.BlockSugarPile) {
					return;
				}
			}
			if (!event.getEntityPlayer().worldObj.isAirBlock(event.getPos())) {
				event.getEntityPlayer().worldObj.setBlockState(event.getPos().up(),
						ContentRegistry.BlockSugarPile.getDefaultState());
				if (!event.getEntityPlayer().capabilities.isCreativeMode)
					event.getItemStack().stackSize--;
				SugarPileList.addSugarPile(event.getEntityPlayer().worldObj, event.getPos().up());
			}
		}
	}

}
