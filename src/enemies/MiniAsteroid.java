package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import data.Constants;
import data.Enemies;
import utilites.MyPoint;

public class MiniAsteroid extends Asteroid {
	private static Random generator = new Random();
	int randomNum;

	public MiniAsteroid() {
		super(Enemies.MiniAsteroid);
		this.setAsteroid();
		MyPoint vel = MiniAsteroid.generateVel(1);
		this.setVel(vel);
		this.setDirection(vel);
	}
	
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 30, 30);
	}
	
	private static MyPoint generateVel(double dif) {
		double x = (generateDouble() - 0.5) * Constants.enemy_maxspeed * dif;
		double y = (generateDouble() - 0.5) * Constants.enemy_maxspeed * dif;
		return new MyPoint(x, y);
	}
	
	private static double generateDouble() {
		return generator.nextDouble();
	}

}

