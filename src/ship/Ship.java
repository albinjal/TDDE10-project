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

import constants.Constants;
import level.PlayModel;
import utilites.GameObject;
import utilites.MyPoint;

public abstract class Ship extends GameObject {
	private PlayModel model;
	private int bulletIntencity = 10;
	private int bulletTemp;
	private int pwrUpDur = 0;
	private Boolean shield = false;


	public Ship(PlayModel model) {
		super();
		this.model = model;
		this.setResistance(0.3);
		this.bulletTemp = this.bulletIntencity;
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
		if (this.bulletTemp == bulletIntencity) {
			Bullet shot = new Bullet();
			shot.setPos(this.getPos().add(this.getDirection().multiply(20)));
			shot.setVel(this.getDirection().multiply(1000).add(this.getVel()));
			shot.setRotation(this.getRotation());
			this.getModel().addShot(shot);
			this.bulletTemp = 0;
		}
		this.bulletTemp ++;
	}
	
	public void setBulletI(int intencity, int duration) {
		this.bulletIntencity =  intencity;
		this.pwrUpDur = duration * 60;

	}
	
	public void setShield(int duration) {
		this.shield = true;
		this.pwrUpDur = duration * 60;
	}
	
	public Boolean getShieldStatus() {
		return this.shield;
	}
	
	public void updatePwrUpDur() {
		if (this.pwrUpDur > 0) {
			this.pwrUpDur --;
			if (this.pwrUpDur == 0) {
				this.bulletIntencity = 10;
				this.shield = false;
			}
		}
	}

}
