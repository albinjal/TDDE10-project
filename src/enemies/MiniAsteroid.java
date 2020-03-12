package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import data.Constants;
import data.Enemies;
import utilites.MyPoint;

public class MiniAsteroid extends Enemy {
	private static Random generator = new Random();
	int randomNum;

	public MiniAsteroid() {
		super(Enemies.MiniAsteroid);
		this.setMiniAsteroid();
		MyPoint vel = this.generateVel(1);
		this.setVel(vel);
		this.setDirection(vel);
	}
	
	private void setMiniAsteroid() {
		randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		switch (randomNum) {
		case 0:
			this.setImg(this.loadImg("/assets/asteroid2.png"));
			break;
		case 1:
			this.setImg(this.loadImg("/assets/asteroid3.png"));
			break;
		}
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

