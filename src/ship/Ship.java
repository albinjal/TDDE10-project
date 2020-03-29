package ship;

import java.awt.image.BufferedImage;
import java.util.Set;
import data.Constants;
import level.PlayModel;
import utilites.GameObject;
import utilites.MyPoint;

/** The ship class is intended to represent a generic ship with all the things that comes with it such as shooting, accelerating and turning.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public abstract class Ship extends GameObject {
	private PlayModel model;
	private Boolean shield = false;
	private double bulletIntencity = Constants.stdBulletIntencity;
	private double bulletTemp;
	private int bulletTimer = 0;
	private int shieldTimer = 0;
	private BufferedImage normal;
	private BufferedImage accelerating;
	private BufferedImage shielded;
	private BufferedImage acceleratedShield;
	private int lives = Constants.startLives;

	public Ship(PlayModel model) {
		super();
		this.model = model;
		this.setResistance(0.8);
		this.bulletTemp = this.bulletIntencity;
		this.normal = this.loadImg("/assets/spacecraft.png");
		this.accelerating = this.loadImg("/assets/spacecraft_thrust.png");
		this.shielded = this.loadImg("/assets/spacecraft_shield.png");
		this.acceleratedShield = this.loadImg("/assets/spacecraft_thrust_shield.png");
		this.setImg(this.normal);
	}

	@Override
	public void update(double time, GameObject follow) {
		super.update(time, follow);
		this.updatePowerUps(time);
		this.bulletTemp--;
	}

	public void updateAppearance(Set<Integer> keys) {
		if (keys.contains(87) && (this.shieldTimer > 0)) {
			this.setImg(this.acceleratedShield);
		} else if (keys.contains(87)) {
			this.setImg(this.accelerating);
		} else if (this.shieldTimer > 0) {
			this.setImg(this.shielded);
		} else {
			this.setImg(this.normal);
		}
	}

	public void accelerate() {
		this.addVel(this.getDirection().multiply(10 * 60 / Constants.fps));
	}

	public void turnRight() {
		this.rotate(300 / Constants.fps);
	}

	public void turnLeft() {
		this.rotate(-300 / Constants.fps);

	}

	public PlayModel getModel() {
		return this.model;
	}

	public void fire() {
		this.shoot();
	}

	public void setBulletI(int intencity, int duration) {
		this.bulletIntencity = intencity;
		this.bulletTimer = duration * 60;

	}

	public void setShield(int duration) {
		this.shield = true;
		this.shieldTimer = duration * 60;
	}

	public Boolean getShieldStatus() {
		return this.shield;
	}

	public void collide() {
		if (!this.getShieldStatus() && this.lives > 0) {
			this.setPos(new MyPoint(Constants.centerX, Constants.centerY));
			this.setVel(new MyPoint(0, 0));
			this.lives--;
		}
	}

	public void updatePowerUps(double time) {
		if (this.shieldTimer > 0) {
			this.shieldTimer = this.shieldTimer - 1;
			if (this.shieldTimer <= 0) {
				this.shieldTimer = 0;
				this.shield = false;
				this.setImg(this.normal);
			}
		}
		if (this.bulletTimer > 0) {
			this.bulletTimer = this.bulletTimer - 1;
			if (this.bulletTimer <= 0) {
				this.bulletTimer = 0;
				this.bulletIntencity = Constants.stdBulletIntencity;
			}
		}
	}

	public int getLives() {
		return this.lives;
	}

	private void shoot() {
		if (this.bulletTemp <= 0) {
			Bullet shot = new Bullet();
			shot.setPos(this.getPos().add(this.getDirection().multiply(50)));
			shot.setVel(this.getDirection().multiply(1000).add(this.getVel()));
			shot.setRotation(this.getRotation());
			this.getModel().addShot(shot);
			this.bulletTemp = this.bulletIntencity;
		}

	}

}