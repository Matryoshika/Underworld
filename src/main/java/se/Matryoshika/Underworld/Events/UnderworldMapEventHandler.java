package se.Matryoshika.Underworld.Events;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Underworld.Underworld;
import se.Matryoshika.Underworld.WorldGen.WorldProviderCaves;
import se.Matryoshika.Underworld.WorldGen.WorldTypeCaves;
import se.Matryoshika.Underworld.WorldGen.Dirty.DirtyOceanMonument;

public class UnderworldMapEventHandler {
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public void initializeMap(InitMapGenEvent event){
		if(WorldProviderCaves.worldObject == null || WorldProviderCaves.worldObject.provider == null){
			return;
		}
		
		switch (event.getType()) {
		case OCEAN_MONUMENT:{
			//event.setNewGen(new DirtyOceanMonument());
			break;
		}
		case CAVE:{
			break;
		}
		case CUSTOM:{
			break;
		}
		case MINESHAFT:{
			break;
		}
		case NETHER_BRIDGE:{
			break;
		}
		case NETHER_CAVE:{
			break;
		}
		case RAVINE:{
			break;
		}
		case SCATTERED_FEATURE:{
			break;
		}
		case STRONGHOLD:{
			break;
		}
		case VILLAGE:{
			break;
		}
		default:{
			break;
		}
		}
	}
	
}
