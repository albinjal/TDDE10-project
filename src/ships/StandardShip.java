package ships;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import asteroids.Asteroids;
import utilites.MyPoint;

public class StandardShip extends Ship {
	private static Dimension size = new Dimension(100, 100);
	public StandardShip(MyPoint start) {
		super(start);
		this.image = Asteroids.loadImage(this.getClass().getResource("/assets/triangle.png").getPath());
	}
	@Override
	public Dimension getSize() {
		return size;
	}

}
