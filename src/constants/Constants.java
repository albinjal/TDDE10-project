package constants;

import java.awt.Color;

import states.GameState;
import states.PlayState;


public interface Constants {
	final double fps = 2;
	GameStates entryState = GameStates.Play;
    int SCREEN_WIDTH = 1000;
    int SCREEN_HEIGHT = 800;
    Color playBackground = Color.cyan;
}