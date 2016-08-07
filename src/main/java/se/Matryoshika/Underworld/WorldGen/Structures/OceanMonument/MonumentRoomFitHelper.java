package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;

interface MonumentRoomFitHelper{
    boolean fits(RoomDefinition definition);

    Piece create(EnumFacing p_175968_1_, RoomDefinition p_175968_2_, Random p_175968_3_);
}