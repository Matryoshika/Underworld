package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import javax.annotation.Nullable;

import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomStructureComponent;

public abstract class Piece extends CustomStructureComponent
{
	
	public StructureBoundingBox boundingBox;
    /** switches the Coordinate System base off the Bounding Box */
    @Nullable
    private EnumFacing coordBaseMode;
    private Mirror mirror;
    private Rotation rotation;
    /** The type ID of this component. */
    protected int componentType;
	
    protected static final IBlockState ROUGH_PRISMARINE = Blocks.PRISMARINE.getStateFromMeta(BlockPrismarine.ROUGH_META);
    protected static final IBlockState BRICKS_PRISMARINE = Blocks.PRISMARINE.getStateFromMeta(BlockPrismarine.BRICKS_META);
    protected static final IBlockState DARK_PRISMARINE = Blocks.PRISMARINE.getStateFromMeta(BlockPrismarine.DARK_META);
    protected static final IBlockState DOT_DECO_DATA = BRICKS_PRISMARINE;
    protected static final IBlockState SEA_LANTERN = Blocks.SEA_LANTERN.getDefaultState();
    protected static final IBlockState WATER = Blocks.WATER.getDefaultState();
    protected static final int GRIDROOM_SOURCE_INDEX = getRoomIndex(2, 0, 0);
    protected static final int GRIDROOM_TOP_CONNECT_INDEX = getRoomIndex(2, 2, 0);
    protected static final int GRIDROOM_LEFTWING_CONNECT_INDEX = getRoomIndex(0, 1, 0);
    protected static final int GRIDROOM_RIGHTWING_CONNECT_INDEX = getRoomIndex(4, 1, 0);
    protected RoomDefinition roomDefinition;

    protected static final int getRoomIndex(int p_175820_0_, int p_175820_1_, int p_175820_2_)
    {
        return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
    }

    public Piece()
    {
        super(0);
    }

    public Piece(int integer)
    {
        super(integer);
    }

    public Piece(EnumFacing facing, StructureBoundingBox box)
    {
        super(1);
        this.setCoordBaseMode(facing);
        this.boundingBox = box;
    }

    protected Piece(int p_i45590_1_, EnumFacing p_i45590_2_, RoomDefinition def, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_)
    {
        super(p_i45590_1_);
        this.setCoordBaseMode(p_i45590_2_);
        this.roomDefinition = def;
        int i = def.index;
        int j = i % 5;
        int k = i / 5 % 5;
        int l = i / 50;

        if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH)
        {
            this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
        }
        else
        {
            this.boundingBox = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
        }

        switch (p_i45590_2_)
        {
            case NORTH:
                this.boundingBox.offset(j * 8, l * 1, -(k + p_i45590_6_) * 8 + 1);
                break;
            case SOUTH:
                this.boundingBox.offset(j * 8, l * 41, k * 8);
                break;
            case WEST:
                this.boundingBox.offset(-(k + p_i45590_6_) * 8 + 1, l * 1, j * 8);
                break;
            default:
                this.boundingBox.offset(k * 8, l * 1, j * 8);
        }
    }

    /**
     * (abstract) Helper method to write subclass data to NBT
     */
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
    }

    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
    }

    protected void generateWaterBox(World p_181655_1_, StructureBoundingBox box, int x, int y, int z, int xtop, int ytop, int ztop, boolean p_181655_9_)
    {
        for (int i = y; i <= ytop; ++i)
        {
            for (int j = x; j <= xtop; ++j)
            {
                for (int k = z; k <= ztop; ++k)
                {
                    if (!p_181655_9_ || this.getBlockStateFromPos(p_181655_1_, j, i, k, box).getMaterial() != Material.AIR)
                    {
                        if (this.getYWithOffset(i) >= p_181655_1_.getSeaLevel())
                        {
                            this.setBlockState(p_181655_1_, Blocks.AIR.getDefaultState(), j, i, k, box);
                        }
                        else
                        {
                            this.setBlockState(p_181655_1_, WATER, j, i, k, box);
                        }
                    }
                }
            }
        }
    }


    protected void generateDefaultFloor(World worldIn, StructureBoundingBox box, int p_175821_3_, int p_175821_4_, boolean bool)
    {
        if (bool)
        {
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 0, -20, p_175821_4_ + 0, p_175821_3_ + 2, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 5, -20, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 3, -20, p_175821_4_ + 0, p_175821_3_ + 4, 0, p_175821_4_ + 2, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 3, -20, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 3, -20, p_175821_4_ + 2, p_175821_3_ + 4, 0, p_175821_4_ + 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 3, -20, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 2, -20, p_175821_4_ + 3, p_175821_3_ + 2, 0, p_175821_4_ + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 5, -20, p_175821_4_ + 3, p_175821_3_ + 5, 0, p_175821_4_ + 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        }
        else
        {
            this.fillWithBlocks(worldIn, box, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }
    }

    protected void generateBoxOnFillOnly(World worldIn, StructureBoundingBox box, int x, int y, int z, int dx, int dy, int dz, IBlockState state)
    {
    	y-=20;
    	dy-=20;
        for (int i = y; i <= dy; ++i)
        {
            for (int j = x; j <= dx; ++j)
            {
                for (int k = z; k <= dz; ++k)
                {
                    if (this.getBlockStateFromPos(worldIn, j, i, k, box) == WATER)
                    {
                        this.setBlockState(worldIn, state, j, i, k, box);
                    }
                }
            }
        }
    }

    protected boolean doesChunkIntersect(StructureBoundingBox p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_)
    {
        int i = this.getXWithOffset(p_175818_2_, p_175818_3_);
        int j = this.getZWithOffset(p_175818_2_, p_175818_3_);
        int k = this.getXWithOffset(p_175818_4_, p_175818_5_);
        int l = this.getZWithOffset(p_175818_4_, p_175818_5_);
        return p_175818_1_.intersectsWith(Math.min(i, k), Math.min(j, l), Math.max(i, k), Math.max(j, l));
    }

    protected boolean spawnElder(World worldIn, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_)
    {
        int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
        int j = this.getYWithOffset(p_175817_4_);
        int k = this.getZWithOffset(p_175817_3_, p_175817_5_);

        if (p_175817_2_.isVecInside(new BlockPos(i, j, k)))
        {
            EntityGuardian entityguardian = new EntityGuardian(worldIn);
            entityguardian.setElder(true);
            entityguardian.heal(entityguardian.getMaxHealth());
            entityguardian.setLocationAndAngles((double)i + 0.5D, (double)j, (double)k + 0.5D, 0.0F, 0.0F);
            entityguardian.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityguardian)), (IEntityLivingData)null);
            worldIn.spawnEntityInWorld(entityguardian);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    protected void fillWithBlocks(World worldIn, StructureBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, IBlockState boundaryBlockState, IBlockState insideBlockState, boolean existingOnly)
    {
    	yMin = yMin-20;
    	yMax = yMax-20;
        for (int i = yMin; i <= yMax; ++i)
        {
            for (int j = xMin; j <= xMax; ++j)
            {
                for (int k = zMin; k <= zMax; ++k)
                {
                    if (!existingOnly || this.getBlockStateFromPos(worldIn, j, i, k, boundingboxIn).getMaterial() != Material.AIR)
                    {
                        if (i != yMin && i != yMax && j != xMin && j != xMax && k != zMin && k != zMax)
                        {
                            this.setBlockState(worldIn, insideBlockState, j, i, k, boundingboxIn);
                        }
                        else
                        {
                            this.setBlockState(worldIn, boundaryBlockState, j, i, k, boundingboxIn);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    protected void setBlockState(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
    {
        BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), y-20, this.getZWithOffset(x, z));

        if (boundingboxIn.isVecInside(blockpos))
        {
            if (this.mirror != Mirror.NONE)
            {
                blockstateIn = blockstateIn.withMirror(this.mirror);
            }

            if (this.rotation != Rotation.NONE)
            {
                blockstateIn = blockstateIn.withRotation(this.rotation);
            }
            BlockPos alt = new BlockPos(blockpos.getX(), blockpos.getY()-20, blockpos.getZ());

            worldIn.setBlockState(alt, blockstateIn, 2);
        }
    }
    
    public BlockPos getBoundingBoxCenter()
    {
    	BlockPos pos = new BlockPos(this.boundingBox.getCenter().getX(), this.boundingBox.getCenter().getY()-20, this.boundingBox.getCenter().getZ());
        return pos;
    }
    public StructureBoundingBox getUWBoundingBox(){
    	return boundingBox;
    }
}
