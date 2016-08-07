package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyOceanMonument;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyOceanMonument.StartMonument;
import se.Matryoshika.Underworld.WorldGen.Structures.CustomMapGenStructureIO;


public class UnderworldStructureOceanMonumentPieces{

	
	public static void registerOceanMonumentPieces()
    {
        CustomMapGenStructureIO.registerStructureComponent(MonumentBuilding.class, "UWOMB");
        CustomMapGenStructureIO.registerStructureComponent(MonumentCoreRoom.class, "UWOMCR");
        CustomMapGenStructureIO.registerStructureComponent(DoubleXRoom.class, "UWOMDXR");
        CustomMapGenStructureIO.registerStructureComponent(DoubleXYRoom.class, "UWOMDXYR");
        CustomMapGenStructureIO.registerStructureComponent(DoubleYRoom.class, "UWOMDYR");
        CustomMapGenStructureIO.registerStructureComponent(DoubleYZRoom.class, "UWOMDYZR");
        CustomMapGenStructureIO.registerStructureComponent(DoubleZRoom.class, "UWOMDZR");
        CustomMapGenStructureIO.registerStructureComponent(EntryRoom.class, "UWOMEntry");
        CustomMapGenStructureIO.registerStructureComponent(Penthouse.class, "UWOMPenthouse");
        CustomMapGenStructureIO.registerStructureComponent(SimpleRoom.class, "UWOMSimple");
        CustomMapGenStructureIO.registerStructureComponent(SimpleTopRoom.class, "UWOMSimpleT");
        CustomMapGenStructureIO.registerStructure(StartMonument.class, "underworld_oceanMonument");
    }
}


