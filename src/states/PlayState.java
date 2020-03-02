package states;

import java.awt.Graphics;

import constants.Constants;
import level.PlayModel;
import utilites.KeyMap;

public class PlayState extends GameState {
	private PlayModel data = new PlayModel();
	private long lastUpdate = System.currentTimeMillis();
	
	public PlayState(GameModel model) {
		super(model);
	}

	@Override
	public void draw(Graphics g) {
		/*
		long now = System.currentTimeMillis();
		double fps = calcFPS(now, this.lastUpdate);
		this.lastUpdate = now;
		g.drawString(String.format("FPS: %s", fps), (Constants.SCREEN_WIDTH / 2) - 200, Constants.SCREEN_HEIGHT / 2);
		this.lastUpdate = System.currentTimeMillis();
		*/
		
		this.data.draw(g);
	}

	@Override
	public void update() {
		this.data.update();
	}

	@Override
	public void keyPressed(KeyMap keysPressed) {
		this.data.keyPressed(keysPressed);
	}
	
	public static int calcFPS(long now, long last) {
		long time = now - last;
		return (int) (1000 / time);
	}

}
