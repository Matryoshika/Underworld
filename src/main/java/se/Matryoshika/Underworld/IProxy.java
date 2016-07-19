package se.Matryoshika.Underworld;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy {
	void preInit();

	void init();

	void postInit();
}
