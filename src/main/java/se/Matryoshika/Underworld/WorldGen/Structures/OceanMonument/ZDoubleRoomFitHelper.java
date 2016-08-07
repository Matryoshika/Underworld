package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class ZDoubleRoomFitHelper implements MonumentRoomFitHelper
{
    ZDoubleRoomFitHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        return definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed;
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        RoomDefinition structureoceanmonumentpieces$roomdefinition = p_175968_2_;

        if (!p_175968_2_.hasOpening[EnumFacing.NORTH.getIndex()] || p_175968_2_.connections[EnumFacing.NORTH.getIndex()].claimed)
        {
            structureoceanmonumentpieces$roomdefinition = p_175968_2_.connections[EnumFacing.SOUTH.getIndex()];
        }

        structureoceanmonumentpieces$roomdefinition.claimed = true;
        structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.NORTH.getIndex()].claimed = true;
        return new DoubleZRoom(p_175968_1_, structureoceanmonumentpieces$roomdefinition, p_175968_3_);
    }
}
