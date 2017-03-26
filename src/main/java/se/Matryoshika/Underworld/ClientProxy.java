package se.Matryoshika.Underworld;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Content.Blocks.BlockMetamorphicTable;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;
import se.Matryoshika.Underworld.Content.Rendering.FireflyFX;
import se.Matryoshika.Underworld.Content.Rendering.ItemColourHandler;
import se.Matryoshika.Underworld.Content.Rendering.ItemRenderRegister;
import se.Matryoshika.Underworld.Content.Rendering.ParticleRenderer;
import se.Matryoshika.Underworld.Content.Rendering.TEMetamorphicTableRenderer;
import se.Matryoshika.Underworld.Content.Rendering.TERenderEnderPortal;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;

public class ClientProxy extends CommonProxy{
	
	public static final ResourceLocation firefly = new ResourceLocation(Underworld.MODID,"textures/particles/firefly.png");
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(ParticleRenderer.class);
		BlockRenderRegister.registerBlockRenderer();
		ItemRenderRegister.registerItemRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileMetamorphicTable.class, new TEMetamorphicTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileUnderworldEnderPortal.class, new TERenderEnderPortal());
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		ItemColourHandler.init();
    }
	
	@Override
    public void postInit(FMLPostInitializationEvent event){

    }
	
	@Override
	public void trailParticles(World world, BlockPos pos, Random rand){
		final BlockMetamorphicTable table = (BlockMetamorphicTable) world.getBlockState(pos).getBlock();
		table.trailParticles(world, pos, rand);
	}
	
	@Override 
	public void boomParticles(World world, BlockPos pos, Random rand){
		final BlockMetamorphicTable table = (BlockMetamorphicTable) world.getBlockState(pos).getBlock();
		table.boomParticles(world, pos, rand);
	}
	
	@Override
	public void spawnCustomParticle(String name,World world, double x, double y, double z, double motx, double moty, double motz, int age, float red, float green, float blue, boolean distanceLimit){
		if(name.equals("firefly")){
			Minecraft.getMinecraft().effectRenderer.addEffect(new FireflyFX(world,x, y, z, motx, moty, motz, 1, red, green, blue, distanceLimit, true, age));
		}
	}

}
