package se.Matryoshika.Underworld.WorldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions){
        return new ChunkProviderCaves(world, world.getWorldInfo().isMapFeaturesEnabled(), world.getSeed());
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
    
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(net.minecraft.client.Minecraft mc, net.minecraft.client.gui.GuiCreateWorld guiCreateWorld)
    {
        if (this == WorldType.FLAT)
        {
            mc.displayGuiScreen(new net.minecraft.client.gui.GuiCreateFlatWorld(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
        }
        else if (this == CAVES)
        {
            mc.displayGuiScreen(new net.minecraft.client.gui.GuiCustomizeWorldScreen(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
        }
    }

}

