package level;

import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.Random;

import constants.Constants;
import constants.Enemies;
import enemies.Asteroid;
import enemies.Enemy;
import enemies.Rocket;
import powerups.Powerup;
import utilites.GameObject;
import utilites.MyPoint;

public class Level {
	private static Random generator = new Random();
	private int asteroids;
	private int rockets;
	private int powerups;
	public Level(int asteroids, int rockets, int powerups) {
		this.asteroids = asteroids;
		this.rockets = rockets;
		this.powerups = powerups;
	}
	
	public ArrayList<Enemy> loadEnemies(double dificulty, Rectangle2D outline, GameObject follow) {
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		list.addAll(loadEnemy(dificulty, outline, Enemies.Asteroid, this.asteroids * dificulty, follow));
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
		for (int i = 0; i < this.powerups; i++) {
			Powerup pwrUp = new Powerup();
			pwrUp.setPos(generatePos(outline));
			MyPoint vel = generateVel(dificulty);
			pwrUp.setVel(vel);
			pwrUp.setDirection(vel);
			list.add(pwrUp);
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
