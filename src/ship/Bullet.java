package ship;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import utilites.GameObject;

/**
 * Represents a shot comming out of the ship.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class Bullet extends GameObject {
	public Bullet() {
		this.setImg(this.loadImg("/assets/laser_bullet.png"));
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 40, 10);
	}

}
