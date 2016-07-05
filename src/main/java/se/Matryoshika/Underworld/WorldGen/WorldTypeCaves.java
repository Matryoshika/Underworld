package se.Matryoshika.Underworld.WorldGen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;

public class WorldTypeCaves extends WorldType{
	
	public static final WorldType CAVES = (new WorldTypeCaves());
	
	public WorldTypeCaves() {
        super("CAVES");
	}

	@Override
    public BiomeProvider getBiomeProvider(World world){
        return new BiomeProviderCaves(world.getWorldInfo());
    }
    
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderCaves(world, world.getWorldInfo().isMapFeaturesEnabled(), world.getSeed());
        //return new ChunkProviderGenerateVanilla(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }
    
    @Override
    public boolean isCustomizable()
    {
        return false;
    }
    
    @Override
    public float getCloudHeight()
    {
        return 256.0F;
    }

}

