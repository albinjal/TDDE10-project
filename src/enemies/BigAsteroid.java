package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import data.Enemies;
/** Represents a big asteroid.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
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
