package se.Matryoshika.Underworld.WorldGen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;

public class WorldTypeCaves extends WorldType{
	
	//private static final WorldType CAVES = (new WorldTypeCaves("CAVES"));
	
	
	public WorldTypeCaves(String name) {
        super(name);
	}

	@Override
    public BiomeProvider getBiomeProvider(World world){
        return new BiomeProviderCaves(world.getWorldInfo());
    }
    
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions){
        return new ChunkProviderCaves(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
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
        else if (this == Underworld.getCaves())
        {
            mc.displayGuiScreen(new net.minecraft.client.gui.GuiCustomizeWorldScreen(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
        }
    }

}

