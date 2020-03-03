package powerups;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;

import ship.Ship;
import utilites.GameObject;

public class Powerup extends GameObject {

	public Powerup() {
		super();
		this.setImg("/assets/pwrUp.png");
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 50, 50);
	}
	
	public void usePwr(Ship ship) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		
		switch (randomNum) {
		case 1: ship.setBulletI(5, 10);
		}
	}

}
