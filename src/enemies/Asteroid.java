package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Asteroid extends Enemy {
	
	int randomNum;
	
	public Asteroid() {
		super();
		this.setAsteroid();
	}

	private void setAsteroid() {
		randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		switch (randomNum) {
		case 0:
			this.setImg(this.loadImg("/assets/asteroid2.png"));
			System.out.print(0);
			break;
		case 1:
			this.setImg(this.loadImg("/assets/asteroid3.png"));
			System.out.print(1);

			break;
		}
	}
	
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 100);
	}
}
