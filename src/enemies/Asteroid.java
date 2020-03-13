package enemies;

import java.util.concurrent.ThreadLocalRandom;

import data.Enemies;

public abstract class Asteroid extends Enemy {

	int randomNum;

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
