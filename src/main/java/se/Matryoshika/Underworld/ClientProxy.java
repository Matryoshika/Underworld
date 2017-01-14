package se.Matryoshika.Underworld;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Underworld.Content.Blocks.BlockMetamorphicTable;
import se.Matryoshika.Underworld.Content.Rendering.BlockRenderRegister;
import se.Matryoshika.Underworld.Content.Rendering.FireflyFX;
import se.Matryoshika.Underworld.Content.Rendering.ItemRenderRegister;
import se.Matryoshika.Underworld.Content.Rendering.TEMetamorphicTableRenderer;
import se.Matryoshika.Underworld.Content.Rendering.TERenderEnderPortal;
import se.Matryoshika.Underworld.Content.TileEntity.TileMetamorphicTable;
import se.Matryoshika.Underworld.Content.TileEntity.TileUnderworldEnderPortal;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		BlockRenderRegister.registerBlockRenderer();
		ItemRenderRegister.registerItemRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileMetamorphicTable.class, new TEMetamorphicTableRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileUnderworldEnderPortal.class, new TERenderEnderPortal());
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		
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
	public void spawnCustomParticle(String name,World world, double x, double y, double z, int age, float red, float green, float blue){
		if(name.equals("firefly")){
			Minecraft.getMinecraft().effectRenderer.addEffect(new FireflyFX(world,x, y, z, 1, red, green, blue, true, true, age));
		}
	}

}
