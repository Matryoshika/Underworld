package se.Matryoshika.Underworld.WorldGen;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCaves extends WorldProvider{
	
	public WorldProviderCaves(){
	}
	
	@Override
	public void setSpawnPoint(BlockPos pos){
		if(this.worldObj.getWorldType() instanceof WorldTypeCaves){
			worldObj.getWorldInfo().setSpawn(new BlockPos(0,50,0));
		}
		else{
			worldObj.getWorldInfo().setSpawn(pos);
		}
    }
	
	@Override
	public BlockPos getSpawnCoordinate(){
		if(this.worldObj.getWorldType() instanceof WorldTypeCaves){
			return new BlockPos(0,51,0);
		}
		else{
			return null;
		}
	}
	
	@Override
	public BlockPos getSpawnPoint(){
        if(this.worldObj.getWorldType() instanceof WorldTypeCaves){
        	return new BlockPos(0, 51, 0);
		}
		else{
			net.minecraft.world.storage.WorldInfo info = worldObj.getWorldInfo();
	        return new BlockPos(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());
		}
        
    }
	
	public boolean getHasNoSky(){
		
		return this.worldObj.getWorldType() instanceof WorldTypeCaves ? true : this.hasNoSky;
    }

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.OVERWORLD;
	}
	
	/**
     * Called to determine if the chunk at the given chunk coordinates within the provider's world can be dropped. Used
     * in WorldProviderSurface to prevent spawn chunks from being unloaded.
     */
    public boolean canDropChunk(int x, int z)
    {
        return !this.worldObj.isSpawnChunk(x, z) || !this.worldObj.provider.getDimensionType().shouldLoadSpawn();
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
    	float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    	
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return true;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return true;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
    	int i = (int)(worldTime % 24000L);
        float f = ((float)i + partialTicks) / 24000.0F - 0.25F;

        if (f < 0.0F)
        {
            ++f;
        }

        if (f > 1.0F)
        {
            --f;
        }

        float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0D) / 2.0D);
        f = f + (f1 - f) / 3.0F;
    	
    	return this.worldObj.getWorldType() instanceof WorldTypeCaves ? 0.50F : f;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return true;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Overworld";
    }
    @Override
    public int getHeight()
    {
    	return this.worldObj.getWorldType() instanceof WorldTypeCaves ? 128 : 256;

    }
    
    @Override
    public int getActualHeight()
    {
    	return hasNoSky ? 128 : 256;
    }


}
