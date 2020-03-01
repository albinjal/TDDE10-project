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

import level.PlayModel;
import utilites.MyPoint;

public abstract class Ship {
	protected BufferedImage image;
	private Shape hitbox;
	public Ship() {
	}
	
	public abstract Dimension getSize();
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this.hitbox);
	}
	
	public void setPos(MyPoint pos) {
		this.move(pos.getX() + this.getPos().getX(), pos.getY() + this.getPos().getY());
	}
	
	public void setCenter(MyPoint pos) {
		this.move(pos.getX() + this.getCenter().getX(), pos.getY() + this.getCenter().getY());

	}
	
	
	public void move(double x, double y) {
		AffineTransform trans = new AffineTransform();
		trans.setToTranslation(x, y);
		this.hitbox = trans.createTransformedShape(this.hitbox);
	}
	
	public MyPoint getPos() {
		return new MyPoint(this.hitbox.getBounds2D().getX(), this.hitbox.getBounds2D().getY());
	}
	
	public MyPoint getCenter() {
		return new MyPoint(this.hitbox.getBounds2D().getCenterX(), this.hitbox.getBounds2D().getCenterY());
	}

	
	
	public BufferedImage getImg() {
		return this.image;
	}
	
	public void rotate(int degrees) {
		AffineTransform trans = new AffineTransform();
		trans.setToRotation( Math.toRadians(45), this.getCenter().getX(), this.getCenter().getY());
		this.hitbox = trans.createTransformedShape(this.hitbox);
	}
	
	protected void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}
	
	public Shape getHitbox() {
		return this.hitbox;
	}
	
	
}
