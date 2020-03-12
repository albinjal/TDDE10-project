package enemies;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import data.Enemies;
import utilites.GameObject;
import utilites.MyPoint;

public class Rocket extends Enemy {
	public Rocket() {
		super(Enemies.Rocket);
		this.setImg(this.loadImg("/assets/rocket2.png"));
	}
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 30);
	}
	
	@Override
	public void update(double time, GameObject follow) {
		super.update(time, follow);
		this.follow(time, follow);
	}
	
	private void follow(double time, GameObject object) {
		MyPoint goal = object.getPos().subtract(this.getPos()).normalize();
		this.setDirection(goal);
		this.setVel(goal.multiply(this.getVel().amount() + time ));
	}

}
