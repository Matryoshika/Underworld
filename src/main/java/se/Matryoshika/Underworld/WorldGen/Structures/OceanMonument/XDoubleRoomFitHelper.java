package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class XDoubleRoomFitHelper implements MonumentRoomFitHelper
{
    XDoubleRoomFitHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        return definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.connections[EnumFacing.EAST.getIndex()].claimed;
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        p_175968_2_.claimed = true;
        p_175968_2_.connections[EnumFacing.EAST.getIndex()].claimed = true;
        return new DoubleXRoom(p_175968_1_, p_175968_2_, p_175968_3_);
    }
}