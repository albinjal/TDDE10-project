package powerups;

import ship.Ship;

public class BulletIntencity extends Powerup {

	public BulletIntencity() {
		super();
		this.setImg(this.loadImg("/assets/pwrUp.png"));
	}
	
	@Override
	public void usePwr(Ship ship) {
		ship.setBulletI(5, 10);
	}

}
