package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Set;

import data.Constants;
import data.GameStates;
import utilites.Button;
import utilites.MyPoint;

public class MenuState extends GameState {
	private static int buttons = 3;
	public MenuState(GameModel model) {
		super(model);
		this.addButton(new Button(new MyPoint(Constants.centerX,  Constants.SCREEN_HEIGHT / (buttons + 1)), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> this.play()));
		this.addButton(new Button(new MyPoint(Constants.centerX, 2 * Constants.SCREEN_HEIGHT /( buttons + 1)), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> this.highScores()));
		this.addButton(new Button(new MyPoint(Constants.centerX, 3 * Constants.SCREEN_HEIGHT /( buttons + 1)), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> System.exit(0)));

	}

	@Override
	public void update(Set<Integer> keys) {

	}
	
	public void draw(Graphics g) {
		this.drawBg(g, Color.black);
		super.draw(g);
	}
	
	private void play() {
		this.getModel().swtichState(GameStates.Play);
	}
	
	private void highScores() {
		this.getModel().swtichState(GameStates.HighScores);

	}
	

}
