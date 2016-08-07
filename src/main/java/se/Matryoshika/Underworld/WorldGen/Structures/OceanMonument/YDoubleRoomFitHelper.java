package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class YDoubleRoomFitHelper implements MonumentRoomFitHelper
{
    YDoubleRoomFitHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        return definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed;
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        p_175968_2_.claimed = true;
        p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
        return new DoubleYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
    }
}