package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class DoubleXYRoom extends Piece
{
    public DoubleXYRoom()
    {
    }

    public DoubleXYRoom(EnumFacing p_i45596_1_, RoomDefinition p_i45596_2_, Random p_i45596_3_)
    {
        super(1, p_i45596_1_, p_i45596_2_, 2, 2, 1);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
    	RoomDefinition structureoceanmonumentpieces$roomdefinition = this.roomDefinition.connections[EnumFacing.EAST.getIndex()];
    	RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.roomDefinition;
    	RoomDefinition structureoceanmonumentpieces$roomdefinition2 = structureoceanmonumentpieces$roomdefinition1.connections[EnumFacing.UP.getIndex()];
    	RoomDefinition structureoceanmonumentpieces$roomdefinition3 = structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()];

        if (this.roomDefinition.index / 25 > 0)
        {
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 8, 0, structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
        }

        if (structureoceanmonumentpieces$roomdefinition2.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 1, 7, 8, 6, ROUGH_PRISMARINE);
        }

        if (structureoceanmonumentpieces$roomdefinition3.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 8, 8, 1, 14, 8, 6, ROUGH_PRISMARINE);
        }

        for (int i = 1; i <= 7; ++i)
        {
            IBlockState iblockstate = BRICKS_PRISMARINE;

            if (i == 2 || i == 6)
            {
                iblockstate = ROUGH_PRISMARINE;
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, i, 0, 0, i, 7, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 15, i, 0, 15, i, 7, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, i, 0, 15, i, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, i, 7, 14, i, 7, iblockstate, iblockstate, false);
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 3, 2, 7, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 2, 4, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 5, 4, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 13, 1, 3, 13, 7, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 1, 2, 12, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 1, 5, 12, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 3, 5, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 1, 3, 10, 3, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 7, 2, 10, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 2, 5, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 2, 10, 7, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 5, 5, 5, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 5, 10, 7, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 6, 6, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 9, 6, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 6, 6, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 9, 6, 5, structureBoundingBoxIn);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 3, 6, 4, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 4, 3, 10, 4, 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.setBlockState(worldIn, SEA_LANTERN, 5, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 5, 4, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 10, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 10, 4, 5, structureBoundingBoxIn);

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 7, 4, 2, 7, false);
        }

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 3, 0, 2, 4, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 1, 0, 12, 2, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 1, 7, 12, 2, 7, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 15, 1, 3, 15, 2, 4, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 0, 4, 6, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 7, 4, 6, 7, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 5, 3, 0, 6, 4, false);
        }

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 5, 0, 12, 6, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 11, 5, 7, 12, 6, 7, false);
        }

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 15, 5, 3, 15, 6, 4, false);
        }

        return true;
    }
}
