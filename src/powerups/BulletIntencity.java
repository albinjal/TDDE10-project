package powerups;

import ship.Ship;

public class BulletIntencity extends Powerup {

	public void BulletIntencity() {
		this.setImg("assets/PweUp.png");
	}
	
	@Override
	public void usePwr(Ship ship) {
		ship.setBulletI(5, 10);
	}

}
