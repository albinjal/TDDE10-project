package powerups;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import utilites.GameObject;

public class Powerup extends GameObject {

	public Powerup() {
		super();
		this.setImg("/assets/bulletPwr.png");
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 50, 50);
	}

}
