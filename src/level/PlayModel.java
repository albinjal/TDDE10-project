package level;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import constants.Constants;
import enemies.Enemy;
import powerups.Powerup;
import ships.Ship;
import ships.StandardShip;
import utilites.MyPoint;

public class PlayModel {
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private int points = 1;
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>();
	public PlayModel() {
		this.ship = new StandardShip();
		this.ship.setPos(new MyPoint(100, 500));
		this.addActions();
		
	}
	
	public void draw(Graphics g) {
		ship.draw(g);
	}
	
	public void update() {
		this.ship.updateKinematics(1 / Constants.fps);
	}
	
	
	public void keyPressed(int key) {
		Runnable action = this.keyActions.get(key);
		if (action != null) {
			action.run();
		}
	}
	
	private void addActions() {
		this.keyActions.put(KeyEvent.VK_W, () -> this.ship.accelerate());
		this.keyActions.put(KeyEvent.VK_A, () -> this.ship.turnLeft());
		this.keyActions.put(KeyEvent.VK_D, () -> this.ship.turnRight());

		
	}
}
