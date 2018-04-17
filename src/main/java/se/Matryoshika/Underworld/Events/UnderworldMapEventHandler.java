package se.Matryoshika.Underworld.Events;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UnderworldMapEventHandler {

	@SubscribeEvent(priority = EventPriority.LOW)
	public void initializeMap(InitMapGenEvent event) {

		switch (event.getType()) {
		case OCEAN_MONUMENT: {
			// event.setNewGen(new DirtyOceanMonument());
			break;
		}
		case CAVE: {
			break;
		}
		case CUSTOM: {
			break;
		}
		case MINESHAFT: {
			break;
		}
		case NETHER_BRIDGE: {
			break;
		}
		case NETHER_CAVE: {
			break;
		}
		case RAVINE: {
			break;
		}
		case SCATTERED_FEATURE: {
			break;
		}
		case STRONGHOLD: {
			break;
		}
		case VILLAGE: {
			break;
		}
		default: {
			break;
		}
		}
	}

}
