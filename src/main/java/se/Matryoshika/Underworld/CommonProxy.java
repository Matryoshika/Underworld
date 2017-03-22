package se.Matryoshika.Underworld;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;

public class CommonProxy {
	
	
	public void preInit(FMLPreInitializationEvent event){
		
    }

    public void init(FMLInitializationEvent event) {

    }
	

    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    public void trailParticles(World world, BlockPos pos, Random rand){
    	
    }
    
    public void boomParticles(World world, BlockPos pos, Random rand){
    	
    }
    
    public void spawnCustomParticle(String name,World world, double x, double y, double z, double motx, double moty, double motz, int age, float red, float green, float blue){
    	
    }

}
