package se.Matryoshika.Underworld.WorldGen;

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
		this.hasNoSky = true;
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
    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_)
    {
        return true;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_)
    {
        return 0.50F;
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
    public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_)
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
        return 128;
    }
    
    @Override
    public int getActualHeight()
    {
        return 128;
    }


}
