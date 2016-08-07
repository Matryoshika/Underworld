package se.Matryoshika.Underworld.WorldGen.Dirty;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomMapGenStructureData;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomMapGenStructureIO;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomStructureComponent;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomStructureStart;

public abstract class CustomMapGenStructure extends MapGenBase{

	
	private CustomMapGenStructureData structureData;
    protected Long2ObjectMap<CustomStructureStart> structureMap = new Long2ObjectOpenHashMap(1024);

    public abstract String getStructureName();

    /**
     * Recursively called by generate()
     */
    protected final synchronized void recursiveGenerate(World worldIn, final int chunkX, final int chunkZ, int p_180701_4_, int p_180701_5_, ChunkPrimer chunkPrimerIn)
    {
        this.initializeStructureData(worldIn);

        if (!this.structureMap.containsKey(ChunkPos.chunkXZ2Int(chunkX, chunkZ)))
        {
            this.rand.nextInt();

            try
            {
                if (this.canSpawnStructureAtCoords(chunkX, chunkZ))
                {
                    CustomStructureStart structurestart = this.getStructureStart(chunkX, chunkZ);
                    this.structureMap.put(ChunkPos.chunkXZ2Int(chunkX, chunkZ), structurestart);

                    if (structurestart.isSizeableStructure())
                    {
                        this.setStructureStart(chunkX, chunkZ, structurestart);
                    }
                }
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception preparing structure feature");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Feature being prepared");
                crashreportcategory.setDetail("Is feature chunk", new ICrashReportDetail<String>()
                {
                    public String call() throws Exception
                    {
                        return CustomMapGenStructure.this.canSpawnStructureAtCoords(chunkX, chunkZ) ? "True" : "False";
                    }
                });
                crashreportcategory.addCrashSection("Chunk location", String.format("%d,%d", new Object[] {Integer.valueOf(chunkX), Integer.valueOf(chunkZ)}));
                crashreportcategory.setDetail("Chunk pos hash", new ICrashReportDetail<String>()
                {
                    public String call() throws Exception
                    {
                        return String.valueOf(ChunkPos.chunkXZ2Int(chunkX, chunkZ));
                    }
                });
                crashreportcategory.setDetail("Structure type", new ICrashReportDetail<String>()
                {
                    public String call() throws Exception
                    {
                        return CustomMapGenStructure.this.getClass().getCanonicalName();
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
    }

    public synchronized boolean generateStructure(World worldIn, Random randomIn, ChunkPos chunkCoord)
    {
        this.initializeStructureData(worldIn);
        int i = (chunkCoord.chunkXPos << 4) + 8;
        int j = (chunkCoord.chunkZPos << 4) + 8;
        boolean flag = false;

        for (CustomStructureStart structurestart : this.structureMap.values())
        {
            if (structurestart.isSizeableStructure() && structurestart.isValidForPostProcess(chunkCoord) && structurestart.getBoundingBox().intersectsWith(i, j, i + 15, j + 15))
            {
                structurestart.generateStructure(worldIn, randomIn, new StructureBoundingBox(i, j, i + 15, j + 15));
                structurestart.notifyPostProcessAt(chunkCoord);
                flag = true;
                this.setStructureStart(structurestart.getChunkPosX(), structurestart.getChunkPosZ(), structurestart);
            }
        }

        return flag;
    }

    public boolean isInsideStructure(BlockPos pos)
    {
        this.initializeStructureData(this.worldObj);
        return this.getStructureAt(pos) != null;
    }

    protected CustomStructureStart getStructureAt(BlockPos pos)
    {
        label24:

        for (CustomStructureStart structurestart : this.structureMap.values())
        {
            if (structurestart.isSizeableStructure() && structurestart.getBoundingBox().isVecInside(pos)){
            	
                Iterator<CustomStructureComponent> iterator = structurestart.getComponents().iterator();

                while (true)
                {
                    if (!iterator.hasNext())
                    {
                        continue label24;
                    }

                    CustomStructureComponent structurecomponent = (CustomStructureComponent)iterator.next();

                    if (structurecomponent.getBoundingBox().isVecInside(pos))
                    {
                        break;
                    }
                }

                return structurestart;
            }
        }

        return null;
    }

    public boolean isPositionInStructure(World worldIn, BlockPos pos)
    {
        this.initializeStructureData(worldIn);

        for (CustomStructureStart structurestart : this.structureMap.values())
        {
            if (structurestart.isSizeableStructure() && structurestart.getBoundingBox().isVecInside(pos))
            {
                return true;
            }
        }

        return false;
    }

    public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos)
    {
        this.worldObj = worldIn;
        this.initializeStructureData(worldIn);
        this.rand.setSeed(worldIn.getSeed());
        long i = this.rand.nextLong();
        long j = this.rand.nextLong();
        long k = (long)(pos.getX() >> 4) * i;
        long l = (long)(pos.getZ() >> 4) * j;
        this.rand.setSeed(k ^ l ^ worldIn.getSeed());
        this.recursiveGenerate(worldIn, pos.getX() >> 4, pos.getZ() >> 4, 0, 0, (ChunkPrimer)null);
        double d0 = Double.MAX_VALUE;
        BlockPos blockpos = null;

        for (CustomStructureStart structurestart : this.structureMap.values())
        {
            if (structurestart.isSizeableStructure())
            {
            	CustomStructureComponent structurecomponent = (CustomStructureComponent)structurestart.getComponents().get(0);
                BlockPos blockpos1 = structurecomponent.getBoundingBoxCenter();
                double d1 = blockpos1.distanceSq(pos);

                if (d1 < d0)
                {
                    d0 = d1;
                    blockpos = blockpos1;
                }
            }
        }

        if (blockpos != null)
        {
            return blockpos;
        }
        else
        {
            List<BlockPos> list = this.getCoordList();

            if (list != null)
            {
                BlockPos blockpos2 = null;

                for (BlockPos blockpos3 : list)
                {
                    double d2 = blockpos3.distanceSq(pos);

                    if (d2 < d0)
                    {
                        d0 = d2;
                        blockpos2 = blockpos3;
                    }
                }

                return blockpos2;
            }
            else
            {
                return null;
            }
        }
    }

    protected List<BlockPos> getCoordList()
    {
        return null;
    }

    protected void initializeStructureData(World worldIn)
    {
        this.structureData = (CustomMapGenStructureData)worldIn.getPerWorldStorage().getOrLoadData(CustomMapGenStructureData.class, this.getStructureName());

        if (this.structureData == null)
        {
            this.structureData = new CustomMapGenStructureData(this.getStructureName());
            worldIn.getPerWorldStorage().setData(this.getStructureName(), this.structureData);
        }
        else
        {
            NBTTagCompound nbttagcompound = this.structureData.getTagCompound();

            for (String s : nbttagcompound.getKeySet())
            {
                NBTBase nbtbase = nbttagcompound.getTag(s);

                if (nbtbase.getId() == 10)
                {
                    NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbtbase;

                    if (nbttagcompound1.hasKey("ChunkX") && nbttagcompound1.hasKey("ChunkZ"))
                    {
                        int i = nbttagcompound1.getInteger("ChunkX");
                        int j = nbttagcompound1.getInteger("ChunkZ");
                        CustomStructureStart structurestart = CustomMapGenStructureIO.getStructureStart(nbttagcompound1, worldIn);

                        if (structurestart != null)
                        {
                        	//System.out.println("Putting down StructureMap");
                            this.structureMap.put(ChunkPos.chunkXZ2Int(i, j), structurestart);
                        }
                        else{
                        	System.out.println("This is where it catches");
                        }
                    }
                }
            }
        }
        
    }

    private void setStructureStart(int chunkX, int chunkZ, CustomStructureStart start)
    {
        this.structureData.writeInstance(start.writeStructureComponentsToNBT(chunkX, chunkZ), chunkX, chunkZ);
        this.structureData.markDirty();
    }

    protected abstract boolean canSpawnStructureAtCoords(int chunkX, int chunkZ);

    protected abstract CustomStructureStart getStructureStart(int chunkX, int chunkZ);
}
