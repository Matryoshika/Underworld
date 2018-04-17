package se.Matryoshika.Underworld.WorldGen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Underworld.Underworld;

public class WorldTypeCaves extends WorldType {
	
	private static ResourceLocation loc = new ResourceLocation(Underworld.MODID, "worldgen/world.json");
	
	private String JSON;
	private boolean tried = false;

	public WorldTypeCaves(String name) {
		super(name);
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProvider(world.getWorldInfo());
	}

	@Override
	public IChunkGenerator getChunkGenerator(World world, String options) {
		return new ChunkGeneratorCaves(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), JSON != null ? JSON : options);
	}

	@Override
	public boolean isCustomizable() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void onCustomizeButton(net.minecraft.client.Minecraft mc, net.minecraft.client.gui.GuiCreateWorld createWorld) {
		if (this == WorldType.FLAT)
			mc.displayGuiScreen(new net.minecraft.client.gui.GuiCreateFlatWorld(createWorld, createWorld.chunkProviderSettingsJson));
		else if (this == Underworld.getCaves())
			mc.displayGuiScreen(new net.minecraft.client.gui.GuiCustomizeWorldScreen(createWorld, createWorld.chunkProviderSettingsJson));

	}
	
	@Override
	public float getCloudHeight(){
        return 256.0F;
    }

}
