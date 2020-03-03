package states;

import java.awt.Graphics;
import java.util.ArrayList;

import constants.Constants;
import level.PlayModel;

public class PlayState extends GameState {
	private PlayModel data = new PlayModel();
	private long lastUpdate = System.currentTimeMillis();
	
	public PlayState(GameModel model) {
		super(model);
	}

	@Override
	public void draw(Graphics g) {	
		this.data.draw(g);
	}

	@Override
	public void update() {
		long now = System.currentTimeMillis();
		double time = (now - this.lastUpdate) / 1000.0;
		this.data.update(time);
		this.lastUpdate = now;
	}

	@Override
	public void keyPressed(ArrayList<Integer> keys) {
		this.data.keyPressed(keys);
	}
	
	public static int calcFPS(long now, long last) {
		long time = now - last;
		return (int) (1000 / time);
	}

}
