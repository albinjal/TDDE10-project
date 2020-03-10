package ship;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import asteroids.Asteroids;
import utilites.GameObject;

public class Bullet extends GameObject {
	public Bullet() {
		this.setImg(this.loadImg("/assets/laser_bullet.png"));
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0,0, 40, 10);
	}

}
