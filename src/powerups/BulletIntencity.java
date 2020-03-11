package powerups;


import data.Constants;
import ship.Ship;

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
