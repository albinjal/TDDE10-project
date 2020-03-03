package states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

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
	public void update(Set<Integer> keys) {
		long now = System.currentTimeMillis();
		double time = (now - this.lastUpdate) / 1000.0;
		this.data.update(time, keys);
		this.lastUpdate = now;
	}

	public static int calcFPS(long now, long last) {
		long time = now - last;
		return (int) (1000 / time);
	}

}
