package ship;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import asteroids.Asteroids;
import utilites.GameObject;

public class Bullet extends GameObject {
	private BufferedImage image;
	public Bullet() {
		this.image = Asteroids.loadImage(this.getClass().getResource("/assets/laser_bullet.png").getPath());
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0,0, 40, 10);
	}

	@Override
	public BufferedImage getImg() {
		return this.image;
	}

}
