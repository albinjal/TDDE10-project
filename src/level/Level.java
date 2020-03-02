package level;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import constants.Constants;
import enemies.Asteroid;
import enemies.Enemy;
import utilites.MyPoint;

public class Level {
	private static Random generator = new Random();
	private int asteroids;
	private int rockets;
	public Level(int asteroids, int rockets) {
		this.asteroids = asteroids;
		this.rockets = rockets;
	}
	
	public ArrayList<Enemy> loadEnemies(double dificulty, Rectangle2D outline) {
		
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		for (int i = 0; i < this.asteroids * dificulty; i++) {
			Asteroid ast = new Asteroid();
			ast.setPos(generatePos(outline));
			MyPoint vel = generateVel(dificulty);
			ast.setVel(vel);
			ast.setDirection(vel);
			list.add(ast);
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
		double x = (generateDouble() - 0.5) * Constants.asteroid_speed * dif;
		double y = (generateDouble() - 0.5) * Constants.asteroid_speed * dif;
		return new MyPoint(x, y);
	}
}
