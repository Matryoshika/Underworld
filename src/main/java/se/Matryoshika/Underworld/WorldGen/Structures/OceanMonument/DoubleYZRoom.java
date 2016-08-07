package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class DoubleYZRoom extends Piece
{
    public DoubleYZRoom()
    {
    }

    public DoubleYZRoom(EnumFacing p_i45594_1_, RoomDefinition p_i45594_2_, Random p_i45594_3_)
    {
        super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        RoomDefinition structureoceanmonumentpieces$roomdefinition = this.roomDefinition.connections[EnumFacing.NORTH.getIndex()];
        RoomDefinition structureoceanmonumentpieces$roomdefinition1 = this.roomDefinition;
        RoomDefinition structureoceanmonumentpieces$roomdefinition2 = structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()];
        RoomDefinition structureoceanmonumentpieces$roomdefinition3 = structureoceanmonumentpieces$roomdefinition1.connections[EnumFacing.UP.getIndex()];

        if (this.roomDefinition.index / 25 > 0)
        {
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 8, structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.DOWN.getIndex()]);
            this.generateDefaultFloor(worldIn, structureBoundingBoxIn, 0, 0, structureoceanmonumentpieces$roomdefinition1.hasOpening[EnumFacing.DOWN.getIndex()]);
        }

        if (structureoceanmonumentpieces$roomdefinition3.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 1, 6, 8, 7, ROUGH_PRISMARINE);
        }

        if (structureoceanmonumentpieces$roomdefinition2.connections[EnumFacing.UP.getIndex()] == null)
        {
            this.generateBoxOnFillOnly(worldIn, structureBoundingBoxIn, 1, 8, 8, 6, 8, 14, ROUGH_PRISMARINE);
        }

        for (int i = 1; i <= 7; ++i)
        {
            IBlockState iblockstate = BRICKS_PRISMARINE;

            if (i == 2 || i == 6)
            {
                iblockstate = ROUGH_PRISMARINE;
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, i, 0, 0, i, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, i, 0, 7, i, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, i, 0, 6, i, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, i, 15, 6, i, 15, iblockstate, iblockstate, false);
        }

        for (int j = 1; j <= 7; ++j)
        {
            IBlockState iblockstate1 = DARK_PRISMARINE;

            if (j == 2 || j == 6)
            {
                iblockstate1 = SEA_LANTERN;
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, j, 7, 4, j, 8, iblockstate1, iblockstate1, false);
        }

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

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.SOUTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 0, 4, 6, 0, false);
        }

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 5, 3, 7, 6, 4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 2, 6, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 2, 6, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 5, 6, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        }

        if (structureoceanmonumentpieces$roomdefinition3.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 5, 3, 0, 6, 4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 2, 2, 4, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 2, 1, 3, 2, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 1, 3, 5, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.NORTH.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 3, 5, 15, 4, 6, 15, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.WEST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 5, 11, 0, 6, 12, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 10, 2, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 10, 1, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 13, 1, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        }

        if (structureoceanmonumentpieces$roomdefinition2.hasOpening[EnumFacing.EAST.getIndex()])
        {
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 7, 5, 11, 7, 6, 12, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 4, 10, 6, 4, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 10, 6, 3, 10, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 13, 6, 3, 13, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
        }

        return true;
    }
}
