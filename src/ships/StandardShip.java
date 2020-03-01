package ships;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import asteroids.Asteroids;
import utilites.MyPoint;

public class StandardShip extends Ship {
	private BufferedImage image;
	public StandardShip() {
		super();
		this.image = Asteroids.loadImage(this.getClass().getResource("/assets/ship.png").getPath());
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 150);
	}

	@Override
	public BufferedImage getImg() {
		return this.image;
	}
	

}
