package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class YZDoubleRoomFitHelper implements MonumentRoomFitHelper
{
    YZDoubleRoomFitHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        if (definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed && definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed)
        {
            RoomDefinition structureoceanmonumentpieces$roomdefinition = definition.connections[EnumFacing.NORTH.getIndex()];
            return structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.UP.getIndex()] && !structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()].claimed;
        }
        else
        {
            return false;
        }
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        p_175968_2_.claimed = true;
        p_175968_2_.connections[EnumFacing.NORTH.getIndex()].claimed = true;
        p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
        p_175968_2_.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
        return new DoubleYZRoom(p_175968_1_, p_175968_2_, p_175968_3_);
    }
}