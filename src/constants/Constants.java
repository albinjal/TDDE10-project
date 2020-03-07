package constants;

import java.awt.Color;

import states.GameState;
import states.PlayState;


public interface Constants {
	final double fps = 60;
	GameStates entryState = GameStates.Menu;
    int SCREEN_WIDTH = 1000;
    int SCREEN_HEIGHT = 800;
    Color playBackground = Color.cyan;
    boolean developer_mode = false;
    double enemy_maxspeed = 100;
    int stdBulletIntencity = 10;
    
    
    int centerX = SCREEN_WIDTH / 2;
    int centerY = SCREEN_HEIGHT/ 2;
}