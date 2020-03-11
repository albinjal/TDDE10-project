package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.Constants;
import data.GameStates;

public class GameModel {
	private GameState activeState;
	private Set<Integer> keys = new HashSet<Integer>();
	private boolean reactedToKeys = true;
	private boolean updating = false;

	public GameModel() {
		this.swtichState(Constants.entryState);
	}

	public void swtichState(GameStates state) {
		this.activeState = this.newState(state);
	}

	public GameState newState(GameStates state) {
		switch (state) {
		case Play:
			return new PlayState(this);
		case Menu:
			return new MenuState(this);
		case HighScores:
			return new HighScores(this);
		}
		return null;
	}

	public void update() {
		this.updating = true;
		activeState.update(this.keys);
		this.updating = false;
		this.reactedToKeys = true;
	}

	public void setKeys(Set<Integer> keys) {
		if (this.reactedToKeys && !this.updating) {
			this.keys = keys;
			this.reactedToKeys = false;
		}

	}

	public void click(MouseEvent e) {
		this.activeState.click(e);
	}

	public void draw(Graphics g) {
		activeState.draw(g);
	}

}
