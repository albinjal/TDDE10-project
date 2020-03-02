package ships;

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
	public Ship() {
		this.setResistance(0.3);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.drawHitbox(g2d);
		this.drawImage(g2d);
	}
	
	public void accelerate() {
		
		this.addVel(this.getDirection().multiply(10));
	}
	
	public void turnRight() {
		this.rotate(500 / Constants.fps);
	}
	
	public void turnLeft() {
		this.rotate(-500 / Constants.fps);

	}
	
}
