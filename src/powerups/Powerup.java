package powerups;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import constants.Powerups;
import ship.Ship;
import utilites.GameObject;


public abstract class Powerup extends GameObject {

	public Powerup() {
		super();
		this.setImg("/assets/pwrUp.png");
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 50, 50);
	}
	
	public static Powerup generate(Powerups e) {
		switch(e) {
		case Shield: return new Shield();
		case BulletIntencity: return new BulletIntencity();
		}
		return null;
	}
	
	public abstract void usePwr(Ship ship);
}
