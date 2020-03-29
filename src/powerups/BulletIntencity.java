package powerups;

import data.Constants;
import ship.Ship;

/**
 * Represents a powerup which makes the ship shoot faster.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class BulletIntencity extends Powerup {

	public BulletIntencity() {
		super();
		this.setImg(this.loadImg("/assets/bulletpowerup.png"));
	}

	@Override
	public void usePwr(Ship ship) {
		ship.setBulletI(10 * (int) Constants.fps / 60, 10);
	}

}
