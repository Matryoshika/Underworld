package se.Matryoshika.Underworld.WorldGen.Structures.OceanMonument;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class MonumentBuilding extends Piece{

    private RoomDefinition sourceRoom;
    private RoomDefinition coreRoom;
    private final List<Piece> childPieces = Lists.<Piece>newArrayList();

    
    public StructureBoundingBox box;

    public MonumentBuilding()
    {
    }

    public MonumentBuilding(Random rand, int x, int z, EnumFacing face)
    {
        super(0);
        this.setCoordBaseMode(face);
        EnumFacing enumfacing = this.getCoordBaseMode();

        if (enumfacing.getAxis() == EnumFacing.Axis.Z)
        {
        	boundingBox = new StructureBoundingBox(x, 39, z, x + 58 - 1, 61, z + 58 - 1);
        }
        else
        {
        	boundingBox = new StructureBoundingBox(x, 39, z, x + 58 - 1, 61, z + 58 - 1);
        }

        List<RoomDefinition> list = this.generateRoomGraph(rand);
        this.sourceRoom.claimed = true;
        this.childPieces.add(new EntryRoom(enumfacing, this.sourceRoom));
        this.childPieces.add(new MonumentCoreRoom(enumfacing, this.coreRoom, rand));
        List<MonumentRoomFitHelper> list1 = Lists.<MonumentRoomFitHelper>newArrayList();
        list1.add(new XYDoubleRoomFitHelper());
        list1.add(new YZDoubleRoomFitHelper());
        list1.add(new ZDoubleRoomFitHelper());
        list1.add(new XDoubleRoomFitHelper());
        list1.add(new YDoubleRoomFitHelper());
        list1.add(new FitSimpleRoomTopHelper());
        list1.add(new FitSimpleRoomHelper());
        label294:

        for (RoomDefinition structureoceanmonumentpieces$roomdefinition : list)
        {
            if (!structureoceanmonumentpieces$roomdefinition.claimed && !structureoceanmonumentpieces$roomdefinition.isSpecial())
            {
                Iterator iterator = list1.iterator();
                MonumentRoomFitHelper structureoceanmonumentpieces$monumentroomfithelper;

                while (true)
                {
                    if (!iterator.hasNext())
                    {
                        continue label294;
                    }

                    structureoceanmonumentpieces$monumentroomfithelper = (MonumentRoomFitHelper)iterator.next();

                    if (structureoceanmonumentpieces$monumentroomfithelper.fits(structureoceanmonumentpieces$roomdefinition))
                    {
                        break;
                    }
                }

                this.childPieces.add(structureoceanmonumentpieces$monumentroomfithelper.create(enumfacing, structureoceanmonumentpieces$roomdefinition, rand));
            }
        }

        int j = 0;
        int k = 9;
        int l = 22;

        for (Piece structureoceanmonumentpieces$piece : this.childPieces)
        {
            structureoceanmonumentpieces$piece.boundingBox.offset(k, j, l);
        }


        StructureBoundingBox structureboundingbox1 = boundingBox.createProper(getXWithOffset(1, 1), getYWithOffset(1), getZWithOffset(1, 1), getXWithOffset(23, 21), getYWithOffset(8), getZWithOffset(23, 21));
        StructureBoundingBox structureboundingbox2 = boundingBox.createProper(getXWithOffset(34, 1), getYWithOffset(1), getZWithOffset(34, 1), getXWithOffset(56, 21), getYWithOffset(8), getZWithOffset(56, 21));
        StructureBoundingBox structureboundingbox  = boundingBox.createProper(getXWithOffset(22, 22), getYWithOffset(13), getZWithOffset(22, 22), getXWithOffset(35, 35), getYWithOffset(17), getZWithOffset(35, 35));
        int i = rand.nextInt();
        this.childPieces.add(new WingRoom(enumfacing, structureboundingbox1, i++));
        this.childPieces.add(new WingRoom(enumfacing, structureboundingbox2, i++));
        this.childPieces.add(new Penthouse(enumfacing, structureboundingbox));
    }
    
    protected int getXWithOffset(int x, int z)
    {
        EnumFacing enumfacing = this.getCoordBaseMode();

        if (enumfacing == null)
        {
            return x;
        }
        else
        {
            switch (enumfacing)
            {
                case NORTH:
                case SOUTH:
                    return boundingBox.minX + x;
                case WEST:
                    return boundingBox.maxX - z;
                case EAST:
                    return boundingBox.minX + z;
                default:
                    return x;
            }
        }
    }

    protected int getYWithOffset(int y)
    {
        return this.getCoordBaseMode() == null ? y : y + this.boundingBox.minY;
    }

    protected int getZWithOffset(int x, int z)
    {
        EnumFacing enumfacing = this.getCoordBaseMode();

        if (enumfacing == null)
        {
            return z;
        }
        else
        {
            switch (enumfacing)
            {
                case NORTH:
                    return this.boundingBox.maxZ - z;
                case SOUTH:
                    return this.boundingBox.minZ + z;
                case WEST:
                case EAST:
                    return this.boundingBox.minZ + x;
                default:
                    return z;
            }
        }
    }

    private List<RoomDefinition> generateRoomGraph(Random p_175836_1_)
    {
    	RoomDefinition[] astructureoceanmonumentpieces$roomdefinition = new RoomDefinition[75];

        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 4; ++j)
            {
                int k = 0;
                int l = getRoomIndex(i, 0, j);
                astructureoceanmonumentpieces$roomdefinition[l] = new RoomDefinition(l);
            }
        }

        for (int i2 = 0; i2 < 5; ++i2)
        {
            for (int l2 = 0; l2 < 4; ++l2)
            {
                int k3 = 1;
                int j4 = getRoomIndex(i2, 1, l2);
                astructureoceanmonumentpieces$roomdefinition[j4] = new RoomDefinition(j4);
            }
        }

        for (int j2 = 1; j2 < 4; ++j2)
        {
            for (int i3 = 0; i3 < 2; ++i3)
            {
                int l3 = 2;
                int k4 = getRoomIndex(j2, 2, i3);
                astructureoceanmonumentpieces$roomdefinition[k4] = new RoomDefinition(k4);
            }
        }

        this.sourceRoom = astructureoceanmonumentpieces$roomdefinition[GRIDROOM_SOURCE_INDEX];

        for (int k2 = 0; k2 < 5; ++k2)
        {
            for (int j3 = 0; j3 < 5; ++j3)
            {
                for (int i4 = 0; i4 < 3; ++i4)
                {
                    int l4 = getRoomIndex(k2, i4, j3);

                    if (astructureoceanmonumentpieces$roomdefinition[l4] != null)
                    {
                        for (EnumFacing enumfacing : EnumFacing.values())
                        {
                            int i1 = k2 + enumfacing.getFrontOffsetX();
                            int j1 = i4 + enumfacing.getFrontOffsetY();
                            int k1 = j3 + enumfacing.getFrontOffsetZ();

                            if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && j1 >= 0 && j1 < 3)
                            {
                                int l1 = getRoomIndex(i1, j1, k1);

                                if (astructureoceanmonumentpieces$roomdefinition[l1] != null)
                                {
                                    if (k1 == j3)
                                    {
                                        astructureoceanmonumentpieces$roomdefinition[l4].setConnection(enumfacing, astructureoceanmonumentpieces$roomdefinition[l1]);
                                    }
                                    else
                                    {
                                        astructureoceanmonumentpieces$roomdefinition[l4].setConnection(enumfacing.getOpposite(), astructureoceanmonumentpieces$roomdefinition[l1]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        RoomDefinition structureoceanmonumentpieces$roomdefinition = new RoomDefinition(1003);
        RoomDefinition structureoceanmonumentpieces$roomdefinition1 = new RoomDefinition(1001);
        RoomDefinition structureoceanmonumentpieces$roomdefinition2 = new RoomDefinition(1002);
        astructureoceanmonumentpieces$roomdefinition[GRIDROOM_TOP_CONNECT_INDEX].setConnection(EnumFacing.UP, structureoceanmonumentpieces$roomdefinition);
        astructureoceanmonumentpieces$roomdefinition[GRIDROOM_LEFTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition1);
        astructureoceanmonumentpieces$roomdefinition[GRIDROOM_RIGHTWING_CONNECT_INDEX].setConnection(EnumFacing.SOUTH, structureoceanmonumentpieces$roomdefinition2);
        structureoceanmonumentpieces$roomdefinition.claimed = true;
        structureoceanmonumentpieces$roomdefinition1.claimed = true;
        structureoceanmonumentpieces$roomdefinition2.claimed = true;
        this.sourceRoom.isSource = true;
        this.coreRoom = astructureoceanmonumentpieces$roomdefinition[getRoomIndex(p_175836_1_.nextInt(4), 0, 2)];
        this.coreRoom.claimed = true;
        this.coreRoom.connections[EnumFacing.EAST.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.NORTH.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.UP.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
        this.coreRoom.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.NORTH.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
        List<RoomDefinition> list = Lists.<RoomDefinition>newArrayList();

        for (RoomDefinition structureoceanmonumentpieces$roomdefinition4 : astructureoceanmonumentpieces$roomdefinition)
        {
            if (structureoceanmonumentpieces$roomdefinition4 != null)
            {
                structureoceanmonumentpieces$roomdefinition4.updateOpenings();
                list.add(structureoceanmonumentpieces$roomdefinition4);
            }
        }

        structureoceanmonumentpieces$roomdefinition.updateOpenings();
        Collections.shuffle(list, p_175836_1_);
        int i5 = 1;

        for (RoomDefinition structureoceanmonumentpieces$roomdefinition3 : list)
        {
            int j5 = 0;
            int k5 = 0;

            while (j5 < 2 && k5 < 5)
            {
                ++k5;
                int l5 = p_175836_1_.nextInt(6);

                if (structureoceanmonumentpieces$roomdefinition3.hasOpening[l5])
                {
                    int i6 = EnumFacing.getFront(l5).getOpposite().getIndex();
                    structureoceanmonumentpieces$roomdefinition3.hasOpening[l5] = false;
                    structureoceanmonumentpieces$roomdefinition3.connections[l5].hasOpening[i6] = false;

                    if (structureoceanmonumentpieces$roomdefinition3.findSource(i5++) && structureoceanmonumentpieces$roomdefinition3.connections[l5].findSource(i5++))
                    {
                        ++j5;
                    }
                    else
                    {
                        structureoceanmonumentpieces$roomdefinition3.hasOpening[l5] = true;
                        structureoceanmonumentpieces$roomdefinition3.connections[l5].hasOpening[i6] = true;
                    }
                }
            }
        }

        list.add(structureoceanmonumentpieces$roomdefinition);
        list.add(structureoceanmonumentpieces$roomdefinition1);
        list.add(structureoceanmonumentpieces$roomdefinition2);
        return list;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        int i = worldIn.getSeaLevel() - this.boundingBox.minY;
        this.generateWaterBox(worldIn, structureBoundingBoxIn, 0, 0, 0, 58, i, 58, false);
        this.generateWing(false, 0, worldIn, randomIn, structureBoundingBoxIn);
        this.generateWing(true, 33, worldIn, randomIn, structureBoundingBoxIn);
        this.generateEntranceArchs(worldIn, randomIn, structureBoundingBoxIn);
        this.generateEntranceWall(worldIn, randomIn, structureBoundingBoxIn);
        this.generateRoofPiece(worldIn, randomIn, structureBoundingBoxIn);
        this.generateLowerWall(worldIn, randomIn, structureBoundingBoxIn);
        this.generateMiddleWall(worldIn, randomIn, structureBoundingBoxIn);
        this.generateUpperWall(worldIn, randomIn, structureBoundingBoxIn);

        for (int j = 0; j < 7; ++j)
        {
            int k = 0;

            while (k < 7)
            {
                if (k == 0 && j == 3)
                {
                    k = 6;
                }

                int l = j * 9;
                int i1 = k * 9;

                for (int j1 = 0; j1 < 4; ++j1)
                {
                    for (int k1 = 0; k1 < 4; ++k1)
                    {
                        this.setBlockState(worldIn, BRICKS_PRISMARINE, l + j1, 0, i1 + k1, structureBoundingBoxIn);
                        this.replaceAirAndLiquidDownwards(worldIn, BRICKS_PRISMARINE, l + j1, -1, i1 + k1, structureBoundingBoxIn);
                    }
                }

                if (j != 0 && j != 6)
                {
                    k += 6;
                }
                else
                {
                    ++k;
                }
            }
        }

        for (int l1 = 0; l1 < 5; ++l1)
        {															//X           Y        z      ztop   ytop ztop
            this.generateWaterBox(worldIn, structureBoundingBoxIn, -1 - l1, 0 + l1 * 2, -1 - l1, -1 - l1, 23, 58 + l1, false);
            this.generateWaterBox(worldIn, structureBoundingBoxIn, 58 + l1, 0 + l1 * 2, -1 - l1, 58 + l1, 23, 58 + l1, false);
            this.generateWaterBox(worldIn, structureBoundingBoxIn,  0 - l1, 0 + l1 * 2, -1 - l1, 57 + l1, 23, -1 - l1, false);
            this.generateWaterBox(worldIn, structureBoundingBoxIn,  0 - l1, 0 + l1 * 2, 58 + l1, 57 + l1, 23, 58 + l1, false);
        }

        for (Piece structureoceanmonumentpieces$piece : this.childPieces)
        {
            if (structureoceanmonumentpieces$piece.getBoundingBox().intersectsWith(structureBoundingBoxIn))
            {
                structureoceanmonumentpieces$piece.addComponentParts(worldIn, randomIn, structureBoundingBoxIn);
            }
        }

        return true;
    }

    private void generateWing(boolean p_175840_1_, int p_175840_2_, World worldIn, Random rand, StructureBoundingBox box)
    {
        int i = 24;

        if (this.doesChunkIntersect(box, p_175840_2_, 0, p_175840_2_ + 23, 20))
        {
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20, false);

            for (int j = 0; j < 4; ++j)
            {										//X                   Y     Z  XTop             YTOP   ZTOP
                this.fillWithBlocks(worldIn, box, p_175840_2_ + j     , j + 1, j, p_175840_2_ + j, j + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, p_175840_2_ + j + 7 , j + 5, j + 7, p_175840_2_ + j + 7, j + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, p_175840_2_ + 17 - j, j + 5, j + 7, p_175840_2_ + 17 - j, j + 5, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, p_175840_2_ + 24 - j, j + 1, j, p_175840_2_ + 24 - j, j + 1, 20, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, p_175840_2_ + j + 1 , j + 1, j, p_175840_2_ + 23 - j, j + 1, j, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, p_175840_2_ + j + 8 , j + 5, j + 7, p_175840_2_ + 16 - j, j + 5, j + 7, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(worldIn, box, p_175840_2_ + 4 , 4, 4, p_175840_2_ + 6, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 7 , 4, 4, p_175840_2_ + 17, 4, 6, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 12, box);
            this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 15, box);
            this.setBlockState(worldIn, DOT_DECO_DATA, p_175840_2_ + 12, 9, 18, box);
            int j1 = p_175840_2_ + (p_175840_1_ ? 19 : 5);
            int k = p_175840_2_ + (p_175840_1_ ? 5 : 19);

            for (int l = 20; l >= 5; l -= 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, j1, 5, l, box);
            }

            for (int k1 = 19; k1 >= 7; k1 -= 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, k, 5, k1, box);
            }

            for (int l1 = 0; l1 < 4; ++l1)
            {
                int i1 = p_175840_1_ ? p_175840_2_ + (24 - (17 - l1 * 3)) : p_175840_2_ + 17 - l1 * 3;
                this.setBlockState(worldIn, DOT_DECO_DATA, i1, 5, 5, box);
            }

            this.setBlockState(worldIn, DOT_DECO_DATA, k, 5, 5, box);
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }
    }

    private void generateEntranceArchs(World worldIn, Random p_175839_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 22, 5, 35, 17))
        {
            this.generateWaterBox(worldIn, box, 25, 0, 0, 32, 8, 20, false);

            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, box, 24, 2, 5 + i * 4, 24, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, 22, 4, 5 + i * 4, 23, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.setBlockState(worldIn, BRICKS_PRISMARINE, 25, 5, 5 + i * 4, box);
                this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 6, 5 + i * 4, box);
                this.setBlockState(worldIn, SEA_LANTERN, 26, 5, 5 + i * 4, box);
                this.fillWithBlocks(worldIn, box, 33, 2, 5 + i * 4, 33, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, 34, 4, 5 + i * 4, 35, 4, 5 + i * 4, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.setBlockState(worldIn, BRICKS_PRISMARINE, 32, 5, 5 + i * 4, box);
                this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 6, 5 + i * 4, box);
                this.setBlockState(worldIn, SEA_LANTERN, 31, 5, 5 + i * 4, box);
                this.fillWithBlocks(worldIn, box, 27, 6, 5 + i * 4, 30, 6, 5 + i * 4, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }
        }
    }

    private void generateEntranceWall(World worldIn, Random p_175837_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 15, 20, 42, 21))
        {
            this.fillWithBlocks(worldIn, box, 15, 0, 21, 42, 0, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 26, 1, 21, 31, 3, 21, false);
            this.fillWithBlocks(worldIn, box, 21, 12, 21, 36, 12, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 17, 11, 21, 40, 11, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 16, 10, 21, 41, 10, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 15, 7, 21, 42, 9, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 16, 6, 21, 41, 6, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 17, 5, 21, 40, 5, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 21, 4, 21, 36, 4, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 22, 3, 21, 26, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 31, 3, 21, 35, 3, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 23, 2, 21, 25, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 32, 2, 21, 34, 2, 21, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 28, 4, 20, 29, 4, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 3, 21, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 3, 21, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 2, 21, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 2, 21, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 25, 1, 21, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 32, 1, 21, box);

            for (int i = 0; i < 7; ++i)
            {
                this.setBlockState(worldIn, DARK_PRISMARINE, 28 - i, 6 + i, 21, box);
                this.setBlockState(worldIn, DARK_PRISMARINE, 29 + i, 6 + i, 21, box);
            }

            for (int j = 0; j < 4; ++j)
            {
                this.setBlockState(worldIn, DARK_PRISMARINE, 28 - j, 9 + j, 21, box);
                this.setBlockState(worldIn, DARK_PRISMARINE, 29 + j, 9 + j, 21, box);
            }

            this.setBlockState(worldIn, DARK_PRISMARINE, 28, 12, 21, box);
            this.setBlockState(worldIn, DARK_PRISMARINE, 29, 12, 21, box);

            for (int k = 0; k < 3; ++k)
            {
                this.setBlockState(worldIn, DARK_PRISMARINE, 22 - k * 2, 8, 21, box);
                this.setBlockState(worldIn, DARK_PRISMARINE, 22 - k * 2, 9, 21, box);
                this.setBlockState(worldIn, DARK_PRISMARINE, 35 + k * 2, 8, 21, box);
                this.setBlockState(worldIn, DARK_PRISMARINE, 35 + k * 2, 9, 21, box);
            }

            this.generateWaterBox(worldIn, box, 15, 13, 21, 42, 15, 21, false);
            this.generateWaterBox(worldIn, box, 15, 1, 21, 15, 6, 21, false);
            this.generateWaterBox(worldIn, box, 16, 1, 21, 16, 5, 21, false);
            this.generateWaterBox(worldIn, box, 17, 1, 21, 20, 4, 21, false);
            this.generateWaterBox(worldIn, box, 21, 1, 21, 21, 3, 21, false);
            this.generateWaterBox(worldIn, box, 22, 1, 21, 22, 2, 21, false);
            this.generateWaterBox(worldIn, box, 23, 1, 21, 24, 1, 21, false);
            this.generateWaterBox(worldIn, box, 42, 1, 21, 42, 6, 21, false);
            this.generateWaterBox(worldIn, box, 41, 1, 21, 41, 5, 21, false);
            this.generateWaterBox(worldIn, box, 37, 1, 21, 40, 4, 21, false);
            this.generateWaterBox(worldIn, box, 36, 1, 21, 36, 3, 21, false);
            this.generateWaterBox(worldIn, box, 33, 1, 21, 34, 1, 21, false);
            this.generateWaterBox(worldIn, box, 35, 1, 21, 35, 2, 21, false);
        }
    }

    private void generateRoofPiece(World worldIn, Random p_175841_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 21, 21, 36, 36))
        {
            this.fillWithBlocks(worldIn, box, 21, 0, 22, 36, 0, 36, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 21, 1, 22, 36, 23, 36, false);

            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, box, 21 + i, 13 + i, 21 + i, 36 - i, 13 + i, 21 + i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, 21 + i, 13 + i, 36 - i, 36 - i, 13 + i, 36 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, 21 + i, 13 + i, 22 + i, 21 + i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
                this.fillWithBlocks(worldIn, box, 36 - i, 13 + i, 22 + i, 36 - i, 13 + i, 35 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(worldIn, box, 25, 16, 25, 32, 16, 32, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 25, 17, 25, 25, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 32, 17, 25, 32, 19, 25, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 25, 17, 32, 25, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 32, 17, 32, 32, 19, 32, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 20, 26, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 21, 27, box);
            this.setBlockState(worldIn, SEA_LANTERN, 27, 20, 27, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 26, 20, 31, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 27, 21, 30, box);
            this.setBlockState(worldIn, SEA_LANTERN, 27, 20, 30, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 20, 31, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 21, 30, box);
            this.setBlockState(worldIn, SEA_LANTERN, 30, 20, 30, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 31, 20, 26, box);
            this.setBlockState(worldIn, BRICKS_PRISMARINE, 30, 21, 27, box);
            this.setBlockState(worldIn, SEA_LANTERN, 30, 20, 27, box);
            this.fillWithBlocks(worldIn, box, 28, 21, 27, 29, 21, 27, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 27, 21, 28, 27, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 28, 21, 30, 29, 21, 30, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 30, 21, 28, 30, 21, 29, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }
    }

    private void generateLowerWall(World worldIn, Random p_175835_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 0, 21, 6, 58))
        {
            this.fillWithBlocks(worldIn, box, 0, 0, 21, 6, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 0, 1, 21, 6, 7, 57, false);
            this.fillWithBlocks(worldIn, box, 4, 4, 21, 6, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, box, i, i + 1, 21, i, i + 1, 57 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 23; j < 53; j += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 5, 5, j, box);
            }

            this.setBlockState(worldIn, DOT_DECO_DATA, 5, 5, 52, box);

            for (int k = 0; k < 4; ++k)
            {
                this.fillWithBlocks(worldIn, box, k, k + 1, 21, k, k + 1, 57 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            this.fillWithBlocks(worldIn, box, 4, 1, 52, 6, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 5, 1, 51, 5, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }

        if (this.doesChunkIntersect(box, 51, 21, 58, 58))
        {
            this.fillWithBlocks(worldIn, box, 51, 0, 21, 57, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 51, 1, 21, 57, 7, 57, false);
            this.fillWithBlocks(worldIn, box, 51, 4, 21, 53, 4, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int l = 0; l < 4; ++l)
            {
                this.fillWithBlocks(worldIn, box, 57 - l, l + 1, 21, 57 - l, l + 1, 57 - l, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int i1 = 23; i1 < 53; i1 += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 52, 5, i1, box);
            }

            this.setBlockState(worldIn, DOT_DECO_DATA, 52, 5, 52, box);
            this.fillWithBlocks(worldIn, box, 51, 1, 52, 53, 3, 52, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 52, 1, 51, 52, 3, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }

        if (this.doesChunkIntersect(box, 0, 51, 57, 57))
        {
            this.fillWithBlocks(worldIn, box, 7, 0, 51, 50, 0, 57, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 7, 1, 51, 50, 10, 57, false);

            for (int j1 = 0; j1 < 4; ++j1)
            {
                this.fillWithBlocks(worldIn, box, j1 + 1, j1 + 1, 57 - j1, 56 - j1, j1 + 1, 57 - j1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }
        }
    }

    private void generateMiddleWall(World worldIn, Random p_175842_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 7, 21, 13, 50))
        {
            this.fillWithBlocks(worldIn, box, 7, 0, 21, 13, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 7, 1, 21, 13, 10, 50, false);
            this.fillWithBlocks(worldIn, box, 11, 8, 21, 13, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, box, i + 7, i + 5, 21, i + 7, i + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 21; j <= 45; j += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 12, 9, j, box);
            }
        }

        if (this.doesChunkIntersect(box, 44, 21, 50, 54))
        {
            this.fillWithBlocks(worldIn, box, 44, 0, 21, 50, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 44, 1, 21, 50, 10, 50, false);
            this.fillWithBlocks(worldIn, box, 44, 8, 21, 46, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int k = 0; k < 4; ++k)
            {
                this.fillWithBlocks(worldIn, box, 50 - k, k + 5, 21, 50 - k, k + 5, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int l = 21; l <= 45; l += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 45, 9, l, box);
            }
        }

        if (this.doesChunkIntersect(box, 8, 44, 49, 54))
        {
            this.fillWithBlocks(worldIn, box, 14, 0, 44, 43, 0, 50, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 14, 1, 44, 43, 10, 50, false);

            for (int i1 = 12; i1 <= 45; i1 += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, i1, 9, 45, box);
                this.setBlockState(worldIn, DOT_DECO_DATA, i1, 9, 52, box);

                if (i1 == 12 || i1 == 18 || i1 == 24 || i1 == 33 || i1 == 39 || i1 == 45)
                {
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 9, 47, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 9, 50, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 10, 45, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 10, 46, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 10, 51, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 10, 52, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 11, 47, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 11, 50, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 12, 48, box);
                    this.setBlockState(worldIn, DOT_DECO_DATA, i1, 12, 49, box);
                }
            }

            for (int j1 = 0; j1 < 3; ++j1)
            {
                this.fillWithBlocks(worldIn, box, 8 + j1, 5 + j1, 54, 49 - j1, 5 + j1, 54, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            }

            this.fillWithBlocks(worldIn, box, 11, 8, 54, 46, 8, 54, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 14, 8, 44, 43, 8, 53, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
        }
    }

    private void generateUpperWall(World worldIn, Random p_175838_2_, StructureBoundingBox box)
    {
        if (this.doesChunkIntersect(box, 14, 21, 20, 43))
        {
            this.fillWithBlocks(worldIn, box, 14, 0, 21, 20, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 14, 1, 22, 20, 14, 43, false);
            this.fillWithBlocks(worldIn, box, 18, 12, 22, 20, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 18, 12, 21, 20, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

            for (int i = 0; i < 4; ++i)
            {
                this.fillWithBlocks(worldIn, box, i + 14, i + 9, 21, i + 14, i + 9, 43 - i, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j = 23; j <= 39; j += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 19, 13, j, box);
            }
        }

        if (this.doesChunkIntersect(box, 37, 21, 43, 43))
        {
            this.fillWithBlocks(worldIn, box, 37, 0, 21, 43, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 37, 1, 22, 43, 14, 43, false);
            this.fillWithBlocks(worldIn, box, 37, 12, 22, 39, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.fillWithBlocks(worldIn, box, 37, 12, 21, 39, 12, 21, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);

            for (int k = 0; k < 4; ++k)
            {
                this.fillWithBlocks(worldIn, box, 43 - k, k + 9, 21, 43 - k, k + 9, 43 - k, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int l = 23; l <= 39; l += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, 38, 13, l, box);
            }
        }

        if (this.doesChunkIntersect(box, 15, 37, 42, 43))
        {
            this.fillWithBlocks(worldIn, box, 21, 0, 37, 36, 0, 43, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);
            this.generateWaterBox(worldIn, box, 21, 1, 37, 36, 14, 43, false);
            this.fillWithBlocks(worldIn, box, 21, 12, 37, 36, 12, 39, ROUGH_PRISMARINE, ROUGH_PRISMARINE, false);

            for (int i1 = 0; i1 < 4; ++i1)
            {
                this.fillWithBlocks(worldIn, box, 15 + i1, i1 + 9, 43 - i1, 42 - i1, i1 + 9, 43 - i1, BRICKS_PRISMARINE, BRICKS_PRISMARINE, false);
            }

            for (int j1 = 21; j1 <= 36; j1 += 3)
            {
                this.setBlockState(worldIn, DOT_DECO_DATA, j1, 13, 38, box);
            }
        }
    }
    
}
