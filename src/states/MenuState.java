package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Set;

import constants.Constants;
import constants.GameStates;
import utilites.Button;
import utilites.MyPoint;

public class MenuState extends GameState {

	public MenuState(GameModel model) {
		super(model);
		this.addButton(new Button(new MyPoint(Constants.centerX, 200), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> this.play()));
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
	
	

}
