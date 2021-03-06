package level;

import static data.Constants.SCREEN_HEIGHT;
import static data.Constants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import data.Constants;
import data.Enemies;
import data.GameStates;
import enemies.Enemy;
import enemies.MiniAsteroid;
import powerups.Powerup;
import ship.Bullet;
import ship.Ship;
import ship.StandardShip;
import states.PlayState;
import utilites.GameObject;
import utilites.MyPoint;
import utilites.ShipLife;

/**
 * The Playmodel is a very important class used to represent the current state
 * of the game when playing. It holds all relevant data such as the ship and
 * remaining enemies. It also takes care of making sure everything is drawn and
 * updated in the right way via the draw and update methods.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class PlayModel {
	private Ship ship;
	private PlayState stateRef;
	private CopyOnWriteArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private int points = 0;
	private int currentLevelType = 0;
	private int currentLevel = 1;
	private double currentDif = 1;
	private Map<Integer, Runnable> keyActions = new HashMap<Integer, Runnable>();
	private Rectangle2D.Double visableArea = new Rectangle2D.Double(0, 0, Constants.SCREEN_WIDTH,
			Constants.SCREEN_HEIGHT);
	private static Level[] levels = { new Level(2, 0, 1, 1), new Level(1, 1, 1, 1), new Level(0, 2, 1, 1) };
	private ArrayList<ShipLife> shipLives = new ArrayList<ShipLife>();
	private double earnableLvlPoints = 0;

	public PlayModel(PlayState state) {
		this.loadNextLevel();
		this.stateRef = state;
		this.ship = new StandardShip(this);
		this.ship.setPos(new MyPoint(Constants.centerX, Constants.centerY));
		this.addActions();
		for (int i = 0; i < Constants.startLives; i++) {
			this.shipLives.add(new ShipLife(new MyPoint(120 + 50 * i, 100)));
		}

	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.drawBg(g);
		this.drawGameObjects(g2);
		this.drawUI(g2, g);
	}

	public void update(double time, Set<Integer> keys) {
		this.checkForNewLevel();
		this.updateActions(keys);
		this.updateGameObjects(time, keys);
		if (this.earnableLvlPoints > 0) {
			this.earnableLvlPoints -= this.currentDif / Constants.fps;
		}
	}

	public void addShot(Bullet shot) {
		this.shots.add(shot);
	}

	private void updateGameObjects(double time, Set<Integer> keys) {
		this.updateShip(time, keys);
		this.updateEnemies(time);
		this.updatePowerUps(time);
	}

	private void updateShip(double time, Set<Integer> keys) {
		this.ship.update(time, this.ship);
		this.ship.updateAppearance(keys);
		this.checkForWarp(this.ship);
		updateCol(this.shots, time, this.ship);
		this.removeUnseen(this.shots);
	}

	private void updateEnemies(double time) {
		this.checkForEnemyCollisions();
		for (Enemy enemy : this.enemies) {
			this.checkForWarp(enemy);
		}
		updateCol(this.enemies, time, this.ship);
	}

	private void updatePowerUps(double time) {
		this.checkForPowerUpCollisions();
		for (Powerup pwrUp : this.powerups) {
			this.checkForWarp(pwrUp);
		}
		updateCol(this.powerups, time, this.ship);
	}

	private void updateActions(Set<Integer> keys) {
		Set<Runnable> actions = new HashSet<Runnable>();
		keys.forEach(key -> {
			Runnable action = this.keyActions.get(key);
			if (action != null) {
				actions.add(action);
			}

		});
		actions.forEach(action -> action.run());
	}

	private void addActions() {
		this.keyActions.put(KeyEvent.VK_W, () -> this.ship.accelerate());
		this.keyActions.put(KeyEvent.VK_A, () -> this.ship.turnLeft());
		this.keyActions.put(KeyEvent.VK_D, () -> this.ship.turnRight());
		this.keyActions.put(KeyEvent.VK_SPACE, () -> this.ship.fire());
		this.keyActions.put(KeyEvent.VK_ESCAPE, () -> this.menu());
	}

	private void checkForNewLevel() {
		if (this.enemies.size() <= 0) {
			this.loadNextLevel();
			this.currentLevel++;
			this.points += (int) this.earnableLvlPoints;
		}
	}

	private void loadNextLevel() {
		this.incrementLevel();
		this.currentDif++;
		this.loadLevel(levels[this.currentLevelType], this.currentDif);
		this.earnableLvlPoints = this.currentDif * 10;
	}

	private void incrementLevel() {
		if (this.currentLevelType < levels.length - 1) {
			this.currentLevelType++;
		} else {
			this.currentLevelType = 0;
		}
	}

	private void menu() {
		this.stateRef.getModel().swtichState(GameStates.Menu);
	}

	private void endLevel() {
		this.loose();
	}

	private void loose() {
		this.menu();
		ArrayList<Integer> scores = new ArrayList<Integer>();
		try {
			FileInputStream file = new FileInputStream("data/.highscores.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			scores = (ArrayList<Integer>) in.readObject();
			in.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

		scores.add(this.points);
		Collections.sort(scores, Collections.reverseOrder());
		if (scores.size() > 10) {
			for (int i = 0; i < scores.size() - 10; i++) {
				scores.remove(scores.size() - 1);
			}
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("data/.highscores.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(scores);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	private void removeUnseen(Collection<? extends GameObject> col) {
		col.removeIf(shot -> !shot.getHitbox().intersects(this.visableArea));
	}

	private static void updateCol(Collection<? extends GameObject> col, double time, GameObject follow) {
		for (GameObject element : col) {
			element.update(time, follow);
		}
	}

	private static void drawCol(Collection<? extends GameObject> col, Graphics2D g) {
		col.forEach((GameObject obj) -> {
			obj.draw(g);
		});
	}

	private void checkForWarp(GameObject obj) {
		final double visWidth = this.visableArea.getMaxX();
		final double visHeight = this.visableArea.getMaxY();
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
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		for (Enemy enemy : this.enemies) {
			if (enemy.getHitbox().intersects(this.ship.getHitbox().getBounds2D())) {
				this.ship.collide();
				if (this.shipLives.size() > 0 && !this.ship.getShieldStatus()) {
					this.shipLives.remove(this.shipLives.size() - 1);
				}
				if (this.ship.getLives() <= 0) {
					this.endLevel();
				}
				remove.add(i);
			}
			int k = 0;
			for (Bullet shot : this.shots) {

				if (shot.getHitbox().intersects(enemy.getHitbox().getBounds2D())) {
					remove.add(i);
					removeS.add(k);
					this.points += 5;
					if (enemy.getType() == Enemies.BigAsteroid) {
						for (int y = 0; y < Constants.MiniAsteroidSpawn; y++) {
							Enemy mini = new MiniAsteroid();
							mini.setPos(enemy.getPos());
							list.add(mini);
						}
					}
				}
				k++;
			}
			i++;
		}

		this.enemies.addAll(list);
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
		for (Powerup pwrUp : this.powerups) {
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

	private void drawGameObjects(Graphics2D g2) {
		ship.draw(g2);
		drawCol(this.enemies, g2);
		drawCol(this.shots, g2);
		drawCol(this.powerups, g2);
	}

	private void drawBg(Graphics g) {
		g.setColor(Constants.playBackground);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private void drawUI(Graphics2D g2, Graphics g) {
		g2.setFont(new Font(Constants.font, Font.PLAIN, 40));
		g2.setColor(Color.white);
		g2.draw(this.visableArea);
		g2.drawString("Level " + String.valueOf(this.currentLevel), 100, 50);
		g2.drawString("Points " + String.valueOf(this.points), 300, 50);
		for (ShipLife life : this.shipLives) {
			life.drawImage(g);
		}
	}
}
