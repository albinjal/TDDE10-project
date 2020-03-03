package level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import constants.Constants;
import enemies.Enemy;
import powerups.Powerup;
import ship.Bullet;
import ship.Ship;
import ship.StandardShip;
import utilites.GameObject;
import utilites.MyPoint;

public class PlayModel {
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private int points = 1;
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>();
	private Rectangle2D.Double visableArea = new Rectangle2D.Double(0, 0, Constants.SCREEN_WIDTH,
			Constants.SCREEN_HEIGHT);
	private static Level[] levels = { new Level(2, 0, 1) };

	private ArrayList<Integer> keys = new ArrayList<Integer>();

	public PlayModel() {
		this.ship = new StandardShip(this);
		this.ship.setPos(new MyPoint(100, 500));
		this.addActions();
		this.loadLevel(levels[0], 10);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(this.visableArea);
		ship.draw(g2);
		drawCol(this.enemies, g2);
		drawCol(this.shots, g2);
		drawCol(this.powerups, g2);
	}

	public void update(double time) {
		this.checkForAsteroidCollisions();
		this.checkForPwrUpCollisions();
		this.removeUnseen(this.shots);
		this.checkForWarp(this.ship);
		this.ship.updateKinematics(time);
		for (Enemy enemy : this.enemies) {
			this.checkForWarp(enemy);
		}
		for (Powerup pwrUp : this.powerups) {
			this.checkForWarp(pwrUp);
		}
		updateCol(this.shots, time);
		updateCol(this.enemies, time);
		updateCol(this.powerups, time);
		for (int key : keys) {
			Runnable action = this.keyActions.get(key);
			action.run();
		}

	}

	public void keyPressed(ArrayList<Integer> keys) {
		this.keys = keys;
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

	private void removeUnseen(Collection<? extends GameObject> col) {
		col.removeIf(shot -> !shot.getHitbox().intersects(this.visableArea));
	}

	private static void updateCol(Collection<? extends GameObject> col, double time) {
		for (GameObject element : col) {
			element.updateKinematics(time);
		}
	}

	private static void drawCol(Collection<? extends GameObject> col, Graphics2D g) {
		for (GameObject obj : col) {
			obj.draw(g);
		}
	}

	private void checkForWarp(GameObject obj) {
		double visWidth = this.visableArea.getMaxX();
		double visHeight = this.visableArea.getMaxY();
		double objX = obj.getPos().getX();
		double objY = obj.getPos().getY();
		if (!obj.getHitbox().intersects(this.visableArea)) {
			if (objX > visWidth) {
				objX = 0;
			}
			if (objX < 0) {
				objX = visWidth;
			}
			if (objY > visHeight) {
				objY = 0;
			}
			if (objY < 0) {
				objY = visHeight;
			}
			obj.setPos(new MyPoint(objX, objY));
		}
	}

	private void loadLevel(Level level, double dificulty) {
		this.enemies = level.loadEnemies(dificulty, this.visableArea);
		this.powerups = level.loadPowerups(dificulty, this.visableArea);
	}

	private void checkForAsteroidCollisions() {
		int i = 0;
		Set<Integer> remove = new HashSet<Integer>();
		for (Enemy enemy : this.enemies) {
			if (enemy.getHitbox().intersects(this.ship.getHitbox().getBounds2D())) {
				this.ship = new StandardShip(this);
			}
			int k = 0;
			Set<Integer> removeS = new HashSet<Integer>();
			for (Bullet shot : this.shots) {
				//TODO: getBounds2D on both? wasn't before..
				// Nej ^^
				if (shot.getHitbox().getBounds2D().intersects(enemy.getHitbox().getBounds2D())) {
					remove.add(i);
					removeS.add(k);
				}
				k++;
			}
			for (int del : removeS) {
				this.shots.remove(del);
			}
			i++;
		}
		for (int del : remove) {
			this.enemies.remove(del);
		}
	}
	
	private void checkForPwrUpCollisions() {
		int i = 0;
		for (Powerup pwrUp : this.powerups) {
			if (pwrUp.getHitbox().getBounds2D().intersects(this.ship.getHitbox().getBounds2D())) {
				pwrUp.usePwr(this.ship);
			}
			i++;
		}
	}

}
