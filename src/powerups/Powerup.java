package powerups;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import data.Powerups;
import ship.Ship;
import utilites.GameObject;

/** Contains the common things for powerups.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public abstract class Powerup extends GameObject {

	public Powerup() {
		super();
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
