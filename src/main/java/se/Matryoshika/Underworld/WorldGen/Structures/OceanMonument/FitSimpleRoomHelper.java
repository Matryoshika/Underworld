package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

public class FitSimpleRoomHelper implements MonumentRoomFitHelper
{
    FitSimpleRoomHelper()
    {
    }

    public boolean fits(RoomDefinition definition)
    {
        return true;
    }

    public Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_)
    {
        p_175968_2_.claimed = true;
        return new SimpleRoom(p_175968_1_, p_175968_2_, p_175968_3_);
    }
}
