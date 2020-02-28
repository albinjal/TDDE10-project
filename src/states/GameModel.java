package states;

import constants.GameStates;

public class GameModel {
	private GameState activeState;
	public GameModel() {
		
	}
	
	public void swtichState(GameState state) {
		this.activeState = state;
	}
	
	public GameState newState(GameStates state) {
		switch(state) {
			case Play: return new PlayState(this); 
		}
		return null;
	}
}
