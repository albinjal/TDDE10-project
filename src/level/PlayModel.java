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
import constants.GameStates;
import enemies.Enemy;
import powerups.Powerup;
import ship.Bullet;
import ship.Ship;
import ship.StandardShip;
import states.PlayState;
import utilites.GameObject;
import utilites.MyPoint;

public class PlayModel {
	private Ship ship;
	private PlayState stateRef;
	private ArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private int points = 1;
	private int currentLevel = 0;
	private double currentDif = 1;
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>();
	private Rectangle2D.Double visableArea = new Rectangle2D.Double(0, 0, Constants.SCREEN_WIDTH,
			Constants.SCREEN_HEIGHT);
	private static Level[] levels = { new Level(2, 0, 1, 1), new Level(1, 1, 1, 1), new Level(0, 2, 1, 1) };

	public PlayModel(PlayState state) {
		this.loadNextLevel();
		this.stateRef = state;
		this.ship = new StandardShip(this);
		this.ship.setPos(new MyPoint(Constants.centerX, Constants.centerY));
		this.addActions();
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(this.visableArea);
		ship.draw(g2);
		drawCol(this.enemies, g2);
		drawCol(this.shots, g2);
		drawCol(this.powerups, g2);
	}

	public void update(double time, Set<Integer> keys) {
		this.checkForNewLevel();
		Set<Runnable> actions = new HashSet<Runnable>();
		keys.forEach(key -> {
			Runnable action = this.keyActions.get(key);
			if (action != null) {
				actions.add(action);
			}
			
		});
		actions.forEach(action -> action.run());
		this.checkForEnemyCollisions();
		this.checkForPowerUpCollisions();
		this.ship.updatePowerUps(time);
		this.removeUnseen(this.shots);
		this.ship.updateKinematics(time, this.ship);
		this.checkForWarp(this.ship);
		for (Enemy enemy : this.enemies) {
			this.checkForWarp(enemy);
		}
		for (Powerup pwrUp: this.powerups) {
			this.checkForWarp(pwrUp);
		}
		updateCol(this.shots, time, this.ship);
		updateCol(this.enemies, time, this.ship);
		updateCol(this.powerups, time, this.ship);

	}

	private void addActions() {
		this.keyActions.put(KeyEvent.VK_W, () -> this.ship.accelerate());
		this.keyActions.put(KeyEvent.VK_A, () -> this.ship.turnLeft());
		this.keyActions.put(KeyEvent.VK_D, () -> this.ship.turnRight());
		this.keyActions.put(KeyEvent.VK_SPACE, () -> this.ship.fire());
	}
	
	private void checkForNewLevel() {
		if (this.enemies.size() <= 0) {
			this.loadNextLevel();
		}
	}
	
	private void loadNextLevel() {
		this.incrementLevel();
		this.currentDif++;
		this.loadLevel(levels[this.currentLevel], this.currentDif);
	}
	
	private void incrementLevel() {
		if (this.currentLevel < this.levels.length - 1) {
			this.currentLevel++;
		} else {
			this.currentLevel = 0;
		}
	}
	
	private void menu() {
		this.stateRef.getModel().swtichState(GameStates.Menu);
	}
	
	private void die() {
		this.loose();
	}
	
	private void loose() {
		this.menu();
	}

	public void addShot(Bullet shot) {
		this.shots.add(shot);
	}

	private void removeUnseen(Collection<? extends GameObject> col) {
		col.removeIf(shot -> !shot.getHitbox().intersects(this.visableArea));
	}

	private static void updateCol(Collection<? extends GameObject> col, double time, GameObject follow) {
		for (GameObject element : col) {
			element.updateKinematics(time, follow);
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
		this.enemies = level.loadEnemies(dificulty, this.visableArea, this.ship);
		this.powerups = level.loadPowerups(dificulty, this.visableArea);
	}

	private void checkForEnemyCollisions() {
		int i = 0;
		Set<Integer> remove = new HashSet<Integer>();
		Set<Integer> removeS = new HashSet<Integer>();
		for (Enemy enemy : this.enemies) {
			if (enemy.getHitbox().intersects(this.ship.getHitbox().getBounds2D())) {
				if (!this.ship.getShieldStatus()) {
					this.die();
				} else {
					remove.add(i);
				}
			}
			int k = 0;
			
			for (Bullet shot : this.shots) {

				if (shot.getHitbox().intersects(enemy.getHitbox().getBounds2D())) {
					remove.add(i);
					removeS.add(k);
				}
				k++;
			}
			
			i++;
		}
		for (int del : removeS) {
			if (del < this.shots.size()) {
			this.shots.remove(del);
			}
		}
		for (int del : remove) {
			if (del < this.enemies.size()) {
			this.enemies.remove(del);
			}
		}
	}
	
	private void checkForPowerUpCollisions() {
		int i = 0;
		Set<Integer> remove = new HashSet<Integer>();
		for (Powerup pwrUp: this.powerups) {
			if (pwrUp.getHitbox().intersects(this.ship.getHitbox().getBounds2D())) {
				pwrUp.usePwr(this.ship);
				remove.add(i);
			}			
			i++;
		}
		for (int del : remove) {
			if (del < this.powerups.size()) {
			this.powerups.remove(del);
			}
		}
	}

}
