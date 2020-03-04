package powerups;

import ship.Ship;

public class Shield extends Powerup {

	public void shield() {
		this.setImg("/assets/PwrUp.png");
	}

	@Override
	public void usePwr(Ship ship) {
		ship.setShield(10);
	}
}
