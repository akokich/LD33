package com.akx2.LD33.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.akx2.LD33.LD33;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1280;
		config.height = 720;

		//config.width = 1920;
		//config.height = 1080;

		config.vSyncEnabled = false; // Setting to false disables vertical sync

		config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
		config.backgroundFPS = 0; // Setting to 0 disables background fps throttling

		//config.foregroundFPS = 60; // Setting to 0 disables foreground fps throttling
		//config.backgroundFPS = 60; // Setting to 0 disables background fps throttling

		config.samples = 16;

		new LwjglApplication(new LD33(), config);
	}
}
