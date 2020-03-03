package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

public abstract class GameState {
	
	protected GameModel model;
	
	public GameState(GameModel model) {
		this.model = model;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update(Set<Integer> keys);
		
    public void drawBg(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

}
