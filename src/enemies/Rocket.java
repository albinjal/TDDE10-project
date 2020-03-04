package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import utilites.GameObject;
import utilites.MyPoint;

public class Rocket extends Enemy {
	public Rocket() {
		this.setImg("/assets/rocket.png");
	}
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 30);
	}
	
	@Override
	public void updateKinematics(double time, GameObject follow) {
		super.updateKinematics(time, follow);
		this.follow(time, follow);
	}
	
	private void follow(double time, GameObject object) {
		MyPoint goal = object.getPos().subtract(this.getPos()).normalize();
		this.setDirection(goal);
		this.setVel(goal.multiply(this.getVel().amount() + time ));
	}

}
