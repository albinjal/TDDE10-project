package enemies;

import java.util.concurrent.ThreadLocalRandom;
import data.Enemies;

/**
 * Represents the Asteroid enemy. The class is abstract since all asteroids are
 * either big or mini.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public abstract class Asteroid extends Enemy {

	private int randomNum;

	public Asteroid(Enemies type) {
		super(type);
		this.setAsteroid();
	}

	protected void setAsteroid() {
		randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		switch (randomNum) {
		case 0:
			this.setImg(this.loadImg("/assets/asteroid2.png"));
			break;
		case 1:
			this.setImg(this.loadImg("/assets/asteroid3.png"));
			break;
		}
	}
}
