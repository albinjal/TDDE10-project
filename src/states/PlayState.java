package states;

import java.awt.Graphics;
import java.util.Set;

import level.PlayModel;

/**
 * Represents the playing gamestate. This class is helped by PlayModel which
 * contains the state of play and how it is handled.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class PlayState extends GameState {
	private PlayModel data;
	private long lastUpdate = System.currentTimeMillis();

	public PlayState(GameModel model) {
		super(model);
		this.data = new PlayModel(this);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
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
