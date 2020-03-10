package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class Asteroid extends Enemy {
	
	public Asteroid() {
		super();
		this.setImg(this.loadImg("/assets/asteroid.png"));
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 100);
	}
}
