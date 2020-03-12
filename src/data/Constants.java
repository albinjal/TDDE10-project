package data;

import java.awt.Color;
import java.awt.Font;

import states.GameState;
import states.PlayState;


public interface Constants {
	final double fps = 120;
	GameStates entryState = GameStates.Menu;
    int SCREEN_WIDTH = 1000;
    int SCREEN_HEIGHT = 800;
    Color playBackground = Color.black;
    boolean developer_mode = false;
    double enemy_maxspeed = 100;
    String hs_savepath = "test.ser";
    double stdBulletIntencity = 20 *  fps / 60;
    int startLives = 3;
    String font = "Agency FB";
    
    
    
    int centerX = SCREEN_WIDTH / 2;
    int centerY = SCREEN_HEIGHT/ 2;
}