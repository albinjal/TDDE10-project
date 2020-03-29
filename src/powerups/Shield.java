package powerups;

import ship.Ship;
/** Represents a powerup which gives the ship a shield for a short duration.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class Shield extends Powerup {

	public Shield() {
		super();
		this.setImg(this.loadImg("/assets/shieldpowerup.png"));
	}

	@Override
	public void usePwr(Ship ship) {
		ship.setShield(10);
	}
}
