package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

import utilites.Button;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

public abstract class GameState {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private GameModel model;
	
	public GameState(GameModel model) {
		this.model = model;
	}
	
	public void draw(Graphics g) {
		for (Button button : this.buttons) {
			button.draw((Graphics2D) g);
		}
	}
	
	public abstract void update(Set<Integer> keys);
	
	public void click(MouseEvent e) {
		for (Button button : this.buttons) {
			if (button.getHitbox().contains(e.getPoint())) {
				button.click();
			}
		}
	}
		
    public void drawBg(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
    
    protected void addButton(Button button) {
    	this.buttons.add(button);
    }
    
    public GameModel getModel() {
    	return this.model;
    }
}
