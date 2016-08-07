package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class WingRoom extends Piece
{
    private int mainDesign;

    public WingRoom()
    {
    }

    public WingRoom(EnumFacing p_i45585_1_, StructureBoundingBox p_i45585_2_, int p_i45585_3_)
    {
        super(p_i45585_1_, p_i45585_2_);
        this.mainDesign = p_i45585_3_ & 1;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        if (this.mainDesign == 0)
        {
            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 6, 15, 0, 16, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 6, 6, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 16, 0, 6, 16, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 7, 7, 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 1, 7, 15, 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 6, 9, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 1, 6, 15, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 7, 9, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 1, 7, 14, 1, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 5, 13, 0, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 7, 12, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 10, 8, 0, 12, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 0, 10, 14, 0, 12, DARK_PRISMARINE, DARK_PRISMARINE, false);

            for (int i1 = 18; i1 >= 7; i1 -= 3)
            {
                this.setBlockState(worldIn, SEA_LANTERN, 6, 3, i1, structureBoundingBoxIn);
                this.setBlockState(worldIn, SEA_LANTERN, 16, 3, i1, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, SEA_LANTERN, 10, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 12, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 10, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 12, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 8, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 14, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 4, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 4, 0, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 18, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 18, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 18, 0, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 4, 2, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 4, 1, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 4, 0, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 18, 2, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 18, 1, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 18, 0, 18, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 9, 7, 20, structureBoundingBoxIn);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 13, 7, 20, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 21, 7, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, 0, 21, 16, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.spawnElder(worldIn, structureBoundingBoxIn, 11, 2, 16);
        }
        else if (this.mainDesign == 1)
        {
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 3, 18, 13, 3, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 18, 9, 2, 18, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 0, 18, 13, 2, 18, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            int j1 = 9;
            int j = 20;
            int k = 5;

            for (int l = 0; l < 2; ++l)
            {
                this.setBlockState(worldIn, BRICKS_PRISMARINE, j1, 6, 20, structureBoundingBoxIn);
                this.setBlockState(worldIn, SEA_LANTERN, j1, 5, 20, structureBoundingBoxIn);
                this.setBlockState(worldIn, BRICKS_PRISMARINE, j1, 4, 20, structureBoundingBoxIn);
                j1 = 13;
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 7, 15, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            j1 = 10;

            for (int k1 = 0; k1 < 2; ++k1)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 0, 10, j1, 6, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 0, 12, j1, 6, 12, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.setBlockState(worldIn, SEA_LANTERN, j1, 0, 10, structureBoundingBoxIn);
                this.setBlockState(worldIn, SEA_LANTERN, j1, 0, 12, structureBoundingBoxIn);
                this.setBlockState(worldIn, SEA_LANTERN, j1, 4, 10, structureBoundingBoxIn);
                this.setBlockState(worldIn, SEA_LANTERN, j1, 4, 12, structureBoundingBoxIn);
                j1 = 12;
            }

            j1 = 8;

            for (int l1 = 0; l1 < 2; ++l1)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 0, 7, j1, 2, 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, j1, 0, 14, j1, 2, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                j1 = 14;
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 3, 8, 8, 3, 13, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 3, 8, 14, 3, 13, DARK_PRISMARINE, DARK_PRISMARINE, false);
            this.spawnElder(worldIn, structureBoundingBoxIn, 11, 5, 13);
        }

        return true;
    }
}