package data;

import java.awt.Color;

/**
 * Holds most of the constants game settings such as screen resolution and fps.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */

public interface Constants {
	final double fps = 120;
	GameStates entryState = GameStates.Menu;
	int SCREEN_WIDTH = 800;
	int SCREEN_HEIGHT = 800;
	Color playBackground = Color.black;
	boolean developer_mode = false;
	double enemy_maxspeed = 100;
	double stdBulletIntencity = 20 * fps / 60;
	int startLives = 3;
	String font = "Agency FB";
	int MiniAsteroidSpawn = 3;

	int centerX = SCREEN_WIDTH / 2;
	int centerY = SCREEN_HEIGHT / 2;
}