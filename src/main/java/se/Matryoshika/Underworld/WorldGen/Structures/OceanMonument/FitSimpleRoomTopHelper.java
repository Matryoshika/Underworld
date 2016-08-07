package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class FitSimpleRoomTopHelper implements MonumentRoomFitHelper
{
    FitSimpleRoomTopHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        return !definition.hasOpening[EnumFacing.WEST.getIndex()] && !definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.hasOpening[EnumFacing.SOUTH.getIndex()] && !definition.hasOpening[EnumFacing.UP.getIndex()];
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        p_175968_2_.claimed = true;
        return new SimpleTopRoom(p_175968_1_, p_175968_2_, p_175968_3_);
    }
}
