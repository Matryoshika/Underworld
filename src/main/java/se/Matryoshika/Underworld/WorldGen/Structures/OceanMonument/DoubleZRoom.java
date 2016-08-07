package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class DoubleZRoom extends Piece
{
    public DoubleZRoom()
    {
    }

    public DoubleZRoom(EnumFacing p_i45593_1_, RoomDefinition p_i45593_2_, Random p_i45593_3_)
    {
        super(1, p_i45593_1_, p_i45593_2_, 1, 1, 2);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        RoomDefinition structureoceanmonumentpieces$roomdefinition = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
        RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.roomDefinition;

        if (this.roomDefinition.index / 25 > 0)
        {
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 8, structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
        }

        if (structureoceanmonumentpieces$roomdefinition1.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 4, 1, 6, 4, 7, ROUGH_PRISMARINE);
        }

        if (structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 4, 8, 6, 4, 14, ROUGH_PRISMARINE);
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 0, 0, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 0, 7, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 0, 7, 3, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 15, 6, 3, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 0, 7, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 2, 0, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 15, 6, 2, 15, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 0, 7, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 7, 1, 0, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 15, 6, 1, 15, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 1, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 1, 6, 1, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 1, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 1, 6, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 13, 1, 1, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 13, 6, 1, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 13, 1, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 13, 6, 3, 14, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 2, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 6, 5, 3, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 9, 2, 3, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 9, 5, 3, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 2, 6, 4, 2, 6, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 2, 9, 4, 2, 9, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 2, 7, 2, 2, 8, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 7, 5, 2, 8, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        this.setBlockState(worldIn, SEA_LANTERN, 2, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 5, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 2, 2, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, SEA_LANTERN, 5, 2, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 2, 3, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 5, 3, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 2, 3, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, BRICKS_PRISMARINE, 5, 3, 10, structureBoundingBoxIn);

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 0, 4, 2, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 1, 3, 7, 2, 4, false);
        }

        if (structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 3, 0, 2, 4, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 1, 15, 4, 2, 15, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 1, 11, 0, 2, 12, false);
        }

        if (structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 1, 11, 7, 2, 12, false);
        }

        return true;
    }
}
