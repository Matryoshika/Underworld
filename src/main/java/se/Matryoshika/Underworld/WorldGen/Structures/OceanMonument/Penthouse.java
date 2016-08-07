package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class Penthouse extends Piece
{
    public Penthouse()
    {
    }

    public Penthouse(EnumFacing p_i45591_1_, StructureBoundingBox p_i45591_2_)
    {
        super(p_i45591_1_, p_i45591_2_);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, -1, 2, 11, -1, 11, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, -1, 0, 1, -1, 11, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, -1, 0, 13, -1, 11, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, -1, 0, 11, -1, 1, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, -1, 12, 11, -1, 13, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 0, 0, 13, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 12, 0, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 13, 12, 0, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

        for (int i = 2; i <= 11; i += 3)
        {
            this.setBlockState(worldIn, SEA_LANTERN, 0, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, 13, 0, i, structureBoundingBoxIn);
            this.setBlockState(worldIn, SEA_LANTERN, i, 0, 0, structureBoundingBoxIn);
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 3, 4, 0, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 3, 11, 0, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 9, 9, 0, 11, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 5, 0, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 8, 0, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 10, 0, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 3, 0, 10, structureBoundingBoxIn);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 3, 3, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 3, 10, 0, 7, DARK_PRISMARINE, DARK_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 10, 7, 0, 10, DARK_PRISMARINE, DARK_PRISMARINE, false);
        int l = 3;

        for (int j = 0; j < 2; ++j)
        {
            for (int k = 2; k <= 8; k += 3)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, l, 0, k, l, 2, k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            l = 10;
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 10, 5, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 10, 8, 2, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, -1, 7, 7, -1, 8, DARK_PRISMARINE, DARK_PRISMARINE, false);
        this.generateWaterBox(worldIn, structureBoundingBoxIn, 6, -1, 3, 7, -1, 4, false);
        this.spawnElder(worldIn, structureBoundingBoxIn, 6, 1, 6);
        return true;
    }
}
