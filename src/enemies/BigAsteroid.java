package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import data.Enemies;

public class BigAsteroid extends Asteroid {
int randomNum;
	
	public BigAsteroid() {
		super(Enemies.BigAsteroid);
		this.setAsteroid();
	}
	
	
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 100);
	}
}
