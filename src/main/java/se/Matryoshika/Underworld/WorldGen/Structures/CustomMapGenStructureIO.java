package se.Matryoshika.Underworld.WorldGen.Structures;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomMapGenStructureIO
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map < String, Class <? extends CustomStructureStart >> startNameToClassMap = Maps. < String, Class <? extends CustomStructureStart >> newHashMap();
    private static final Map < Class <? extends CustomStructureStart > , String > startClassToNameMap = Maps. < Class <? extends CustomStructureStart > , String > newHashMap();
    private static final Map < String, Class <? extends CustomStructureComponent >> componentNameToClassMap = Maps. < String, Class <? extends CustomStructureComponent >> newHashMap();
    private static final Map < Class <? extends CustomStructureComponent > , String > componentClassToNameMap = Maps. < Class <? extends CustomStructureComponent > , String > newHashMap();

    public static void registerStructure(Class <? extends CustomStructureStart > startClass, String structureName)
    {
        startNameToClassMap.put(structureName, startClass);
        startClassToNameMap.put(startClass, structureName);
    }

    public static void registerStructureComponent(Class <? extends CustomStructureComponent > componentClass, String componentName)
    {
        componentNameToClassMap.put(componentName, componentClass);
        componentClassToNameMap.put(componentClass, componentName);
    }

    public static String getStructureStartName(CustomStructureStart start)
    {
        return (String)startClassToNameMap.get(start.getClass());
    }

    public static String getStructureComponentName(CustomStructureComponent component)
    {
        return (String)componentClassToNameMap.get(component.getClass());
    }

    @Nullable
    public static CustomStructureStart getStructureStart(NBTTagCompound tagCompound, World worldIn)
    {
    	CustomStructureStart structurestart = null;

        try
        {
            Class <? extends CustomStructureStart > oclass = (Class)startNameToClassMap.get(tagCompound.getString("id"));

            if (oclass != null)
            {
                structurestart = (CustomStructureStart)oclass.newInstance();
            }
        }
        catch (Exception exception)
        {
            LOGGER.warn("Failed Start with id {}", new Object[] {tagCompound.getString("id")});
            exception.printStackTrace();
        }

        if (structurestart != null)
        {
            structurestart.readStructureComponentsFromNBT(worldIn, tagCompound);
        }
        else
        {
            LOGGER.warn("Skipping Structure with id {}", new Object[] {tagCompound.getString("id")});
        }

        return structurestart;
    }

    public static CustomStructureComponent getStructureComponent(NBTTagCompound tagCompound, World worldIn)
    {
    	CustomStructureComponent structurecomponent = null;

        try
        {
            Class <? extends CustomStructureComponent > oclass = (Class)componentNameToClassMap.get(tagCompound.getString("id"));

            if (oclass != null)
            {
                structurecomponent = (CustomStructureComponent)oclass.newInstance();
            }
        }
        catch (Exception exception)
        {
            LOGGER.warn("Failed Piece with id {}", new Object[] {tagCompound.getString("id")});
            exception.printStackTrace();
        }

        if (structurecomponent != null)
        {
            structurecomponent.readStructureBaseNBT(worldIn, tagCompound);
        }
        else
        {
            LOGGER.warn("Skipping Piece with id {}", new Object[] {tagCompound.getString("id")});
        }

        return structurecomponent;
    }
}
