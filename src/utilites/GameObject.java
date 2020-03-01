package utilites;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class GameObject {
	private int rotation;
	private MyPoint position;
	
	public GameObject() {
		
	}
	
	public void move(MyPoint cords) {
		this.position = this.position.add(cords);
	}
	
	public void setPos(MyPoint pos) {
		this.position = pos;
	}
		
	public MyPoint getPos() {
		return this.position;
	}
	
	public AffineTransform getTransform() {
		AffineTransform transform = new AffineTransform();
		transform.translate(this.getPos().getX(), this.getPos().getY());
		transform.rotate(Math.toRadians(this.rotation));
		transform.translate(- this.getWidth() / 2, - this.getHeight() / 2);
		return transform;
	}
	
	public void rotate(int degrees) {
		this.rotation += degrees;
	}
	
	public double getWidth() {
		return this.getBounds().getWidth();
	}
	
	public double getHeight() {
		return this.getBounds().getHeight();
	}
	
	public Rectangle2D getBounds() {
		return this.getHitboxShape().getBounds2D();
	}
	
	public Shape getHitbox() {
		return this.getTransform().createTransformedShape(this.getHitboxShape());
	}
	
	public void drawHitbox(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this.getHitbox());
	}
	
	public void drawImage(Graphics g) {
		AffineTransform trans = this.getTransform();
		trans.concatenate(this.scaleImg());
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(getImg(), trans, null);
	}
	
	public AffineTransform scaleImg() {
		AffineTransform trans = new AffineTransform();
		trans.setToScale(this.getWidth() / this.getImg().getWidth(), this.getHeight() / this.getImg().getHeight());
		return trans;
	}
	
	public abstract Shape getHitboxShape();
	
	public abstract BufferedImage getImg();
	

}
