package states;

import java.awt.Color;
import java.awt.Graphics;

import utilites.KeyMap;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

public abstract class GameState {
	
	protected GameModel model;
	
	public GameState(GameModel model) {
		this.model = model;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public abstract void keyPressed(KeyMap keysPressed);
	
    public void drawBg(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

}
