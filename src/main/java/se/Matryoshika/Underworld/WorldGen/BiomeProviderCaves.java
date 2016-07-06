package se.Matryoshika.Underworld.WorldGen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;

public class BiomeProviderCaves extends BiomeProvider{
	//Old WorldChunkManager

	
	
	/** The biome generator object. */
    private Biome biomeGenerator;
    /** The rainfall in the world */
    private float rainfall;
    private final BiomeCache biomeCache;
    private final List<Biome> biomesToSpawnIn;
    private GenLayer biomeIndexLayer;
    private GenLayer genBiomes;
    private static final String __OBFID = "CL_00000169";

    protected BiomeProviderCaves()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = Lists.newArrayList(allowedBiomes);
    }

    private BiomeProviderCaves(long seed, WorldType worldTypeIn, String options)
    {
        this();
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldTypeIn, options);
        agenlayer = getModdedBiomeGenerators(worldTypeIn, seed, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }

    public BiomeProviderCaves(WorldInfo info)
    {
        this(info.getSeed(), info.getTerrainType(), info.getGeneratorOptions());
    }

    @Override
    public List<Biome> getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

	/**
     * Returns the BiomeGenBase related to the x, z position on the world.
     */
    public Biome getBiomeGenAt(int p_76935_1_, int p_76935_2_)
    {
        return this.biomeGenerator;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        try
        {
            for (int i = 1; i < width * height; ++i)
            {
                biomes[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
            }
            
            //System.out.println("Biomes enabled are: "+biomes.toString());
            
            return biomes;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("w", Integer.valueOf(width));
            crashreportcategory.addCrashSection("h", Integer.valueOf(height));
            throw new ReportedException(crashreport);
        }
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
     */
    public float[] getRainfall(float[] p_76936_1_, int p_76936_2_, int p_76936_3_, int p_76936_4_, int p_76936_5_)
    {
        if (p_76936_1_ == null || p_76936_1_.length < p_76936_4_ * p_76936_5_)
        {
            p_76936_1_ = new float[p_76936_4_ * p_76936_5_];
        }

        Arrays.fill(p_76936_1_, 0, p_76936_4_ * p_76936_5_, this.rainfall);
        return p_76936_1_;
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
     * WorldChunkManager Args: oldBiomeList, x, z, width, depth
     */
    @Override
    public Biome[] loadBlockGeneratorData(Biome[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_)
    {
        if (p_76933_1_ == null || p_76933_1_.length < p_76933_4_ * p_76933_5_)
        {
            p_76933_1_ = new Biome[p_76933_4_ * p_76933_5_];
        }

        Arrays.fill(p_76933_1_, 0, p_76933_4_ * p_76933_5_, this.biomeGenerator);
        return p_76933_1_;
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    @Override
    public Biome[] getBiomeGenAt(Biome[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_)
    {
        return this.loadBlockGeneratorData(p_76931_1_, p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = Biome.getBiome(aint[l1]);

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }
    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_)
    {
        return p_76940_4_.contains(this.biomeGenerator);
    }
}
