package powerups;

import ship.Ship;

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
