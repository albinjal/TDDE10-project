package states;

import java.awt.Graphics;
import java.util.ArrayList;

import constants.GameStates;

public class GameModel {
	private GameState activeState;
	public GameModel() {
		this.swtichState(GameStates.Play);
	}
	
	public void swtichState(GameStates state) {
		this.activeState = this.newState(state);
	}
	
	public GameState newState(GameStates state) {
		switch(state) {
			case Play: return new PlayState(this); 
		}
		return null;
	}
	
	public void keyPressed(ArrayList<Integer> keys) {
        activeState.keyPressed(keys);
    }

    
    public void update() {
    	activeState.update();
    }

    
    public void draw(Graphics g) {
    	activeState.draw(g);
    }

}
