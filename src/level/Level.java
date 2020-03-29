package level;

import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import data.Constants;
import data.Enemies;
import data.Powerups;
import enemies.Asteroid;
import enemies.Enemy;
import enemies.Rocket;
import powerups.Powerup;
import utilites.GameObject;
import utilites.MyPoint;
/** The level class is used as an interface for levels of the game.
 * It holds information about the initial conditions of the level such as distribution of enemies and powerups.
 * It also handles the logic of loading a new level where it generates the correct amount of enemies and powerup which is then inserted into the playmodel.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class Level {
	private static Random generator = new Random();
	private int asteroids;
	private int rockets;
	private int shields;
	private int bulletIntencity;
	public Level(int asteroids, int rockets, int shields, int bulletIntencity) {
		this.asteroids = asteroids;
		this.rockets = rockets;
		this.shields = shields;
		this.bulletIntencity = bulletIntencity;
	}
	
	public CopyOnWriteArrayList<Enemy> loadEnemies(double dificulty, Rectangle2D outline, GameObject follow) {
		CopyOnWriteArrayList<Enemy> list = new CopyOnWriteArrayList<Enemy>();
		list.addAll(loadEnemy(dificulty, outline, Enemies.BigAsteroid, this.asteroids * dificulty, follow));
		list.addAll(loadEnemy(dificulty, outline, Enemies.Rocket, this.rockets * dificulty, follow));
		return list;
	}
	
	private static ArrayList<Enemy> loadEnemy(double dificulty, Rectangle2D outline, Enemies type, double multiplier, GameObject follow) {
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		for (int i = 0; i < multiplier; i++) {
			Enemy base = Enemy.generate(type);
			base.setPos(generatePos(outline));
			MyPoint vel = generateVel(1);
			base.setVel(vel);
			base.setDirection(vel);
			list.add(base);
		}
		return list;
	}
	
	public ArrayList<Powerup> loadPowerups(double dificulty, Rectangle2D outline) {
		ArrayList<Powerup> list = new ArrayList<Powerup>();
		list.addAll(loadPowerUp(dificulty, outline, Powerups.Shield, this.shields));
		list.addAll(loadPowerUp(dificulty, outline, Powerups.BulletIntencity, this.bulletIntencity));
		return list;
	}
	
	private static ArrayList<Powerup> loadPowerUp(double dificulty, Rectangle2D outline, Powerups type, double multiplier) {
		ArrayList<Powerup> list = new ArrayList<Powerup>();
		for (int i = 0; i < multiplier; i++) {
			Powerup base = Powerup.generate(type);
			base.setPos(generatePos(outline));
			MyPoint vel = generateVel(1);
			base.setVel(vel);
			base.setDirection(vel);
			list.add(base);
		}
		return list;
	}
	
	private static double generateDouble() {
		// generator.setSeed(System.nanoTime());
		return generator.nextDouble();
	}
	
	private static MyPoint generatePos(Rectangle2D outline) {
		int side = (int) Math.floor(generateDouble() * 4);
		switch(side) {
		case 0: return new MyPoint(0, generateDouble() * outline.getMaxY());
		case 1: return new MyPoint(generateDouble() * outline.getMaxX(), 0);
		case 2: return new MyPoint(outline.getMaxX(), generateDouble() * outline.getMaxY());
		case 3: return new MyPoint(generateDouble() * outline.getMaxX(), outline.getMaxY());
		default: return null;
		}
	}
	
	private static MyPoint generateVel(double dif) {
		double x = (generateDouble() - 0.5) * Constants.enemy_maxspeed * dif;
		double y = (generateDouble() - 0.5) * Constants.enemy_maxspeed * dif;
		return new MyPoint(x, y);
	}
	
}
