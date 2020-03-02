package level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import constants.Constants;
import enemies.Enemy;
import powerups.Powerup;
import ship.Bullet;
import ship.Ship;
import ship.StandardShip;
import utilites.MyPoint;

public class PlayModel {
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private int points = 1;
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>();
	private Rectangle2D.Double visableArea = new Rectangle2D.Double(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	
	public PlayModel() {
		this.ship = new StandardShip(this);
		this.ship.setPos(new MyPoint(100, 500));
		this.addActions();
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(this.visableArea);
		ship.draw(g2);
		for (Bullet shot : this.shots) {
			shot.draw(g2);
		}
	}
	
	public void update() {
		this.removeUnseen();
		System.out.println(this.shots);
		this.ship.updateKinematics(1 / Constants.fps);
		for (Bullet shot : this.shots) {
			shot.updateKinematics(1/ Constants.fps);
		}
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
		this.keyActions.put(KeyEvent.VK_SPACE, () -> this.ship.fire());
	}
	
	public void addShot(Bullet shot) {
		this.shots.add(shot);
	}
	
	private void removeUnseen() {
		this.shots.removeIf(shot -> !shot.getHitbox().intersects(this.visableArea));
	}
}
