package ship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import constants.Constants;
import level.PlayModel;
import utilites.GameObject;
import utilites.MyPoint;

public abstract class Ship extends GameObject {
	private PlayModel model;
	private Boolean shield = false;
	private int bulletIntencity = Constants.stdBulletIntencity;
	private int bulletTemp = Constants.stdBulletIntencity;
	private int bulletTimer = 0;
	private int shieldTimer = 0;

	public Ship(PlayModel model) {
		super();
		this.model = model;
		this.setResistance(0.8);
	}

	public void accelerate() {
		this.addVel(this.getDirection().multiply(10));
	}

	public void turnRight() {
		this.rotate(600 / Constants.fps);
	}

	public void turnLeft() {
		this.rotate(-600 / Constants.fps);

	}

	public PlayModel getModel() {
		return this.model;
	}

	public void fire() {
		this.shoot();
	}

	public void setBulletI(int intencity, int duration) {
		this.bulletIntencity = intencity;
		this.bulletTimer = duration*60;

	}

	public void setShield(int duration) {
		this.shield = true;
		this.shieldTimer = duration*60;

	}

	public Boolean getShieldStatus() {
		return this.shield;
	}

	public void updatePowerUps(double time) {
		if (this.shieldTimer > 0) {
			this.shieldTimer = this.shieldTimer - 1;
			if (this.shieldTimer <= 0) {
				this.shieldTimer = 0;
				this.shield = false;
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

	private void shoot() {
		this.bulletTemp --;
		if (this.bulletTemp <= 0) {
			Bullet shot = new Bullet();
			shot.setPos(this.getPos().add(this.getDirection().multiply(20)));
			shot.setVel(this.getDirection().multiply(1000).add(this.getVel()));
			shot.setRotation(this.getRotation());
			this.getModel().addShot(shot);
			this.bulletTemp = this.bulletIntencity;
		}
		
	}
}