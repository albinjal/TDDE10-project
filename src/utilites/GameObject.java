package utilites;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import asteroids.Asteroids;
import constants.Constants;

public abstract class GameObject {
	private double rotation = 0;
	private MyPoint position;
	private MyPoint velocity;
	private double resistance = 0;
	private BufferedImage img;

	public GameObject() {
		this.setPos(new MyPoint());
		this.velocity = new MyPoint();
	}
	
	public GameObject(MyPoint pos) {
		this.setPos(pos);
		this.velocity = new MyPoint();
	}
	
	public GameObject(MyPoint pos, MyPoint vel) {
		this.setPos(pos);
		this.setVel(vel);
	}

	public void move(MyPoint cords) {
		this.setPos(this.position.add(cords));
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
		transform.rotate(this.rotation);
		transform.translate(-this.getWidth() / 2, -this.getHeight() / 2);
		return transform;
	}

	public void rotate(double degrees) {
		this.rotation += Math.toRadians(degrees);
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

	public void setVel(MyPoint vel) {
		this.velocity = vel;
	}

	public MyPoint getDirection() {
		return new MyPoint(Math.cos(this.rotation), Math.sin(this.rotation));
	}

	public void updateKinematics(double time) {
		this.patchPos(time);
		this.resist(time);
	}

	public void patchPos(double time) {
		this.setPos(this.position.add(this.velocity.multiply(time)));
	}

	public void addVel(MyPoint vel) {
		this.velocity = this.velocity.add(vel);
	}

	public void showDirection(Graphics2D g) {
		g.draw(this.vectorFromCenter(this.getDirection().multiply(100)));
	}

	public void showVelocity(Graphics2D g) {
		g.draw(this.vectorFromCenter(this.velocity));
	}

	public void setRotation(double rot) {
		this.rotation = rot;
	}

	public double getRotation() {
		return this.rotation;
	}

	public void draw(Graphics2D g2d) {
		this.drawImage(g2d);
		if (Constants.developer_mode) {
			this.showVelocity(g2d);
			this.showDirection(g2d);
			this.drawHitbox(g2d);
		}
	}
	
	public MyPoint getVel() {
		return this.velocity;
	}
	
	public void setDirection(MyPoint dir) {
		dir = dir.normalize();
		double quad = Math.asin(dir.getY());
		double angle = Math.acos(dir.getX());
		if (quad < 0) {
			angle *= -1;
		}
		this.setRotation(angle);
	}

	private Shape vectorFromCenter(MyPoint point) {
		MyPoint center = this.getPos();
		MyPoint edge = center.relativePoint(point.getX(), point.getY());
		return edge.asVector(center);
	}

	private void resist(double time) {
		this.velocity = this.velocity.subtract(this.velocity.multiply(time * this.resistance));
	}

	public abstract Shape getHitboxShape();

	public BufferedImage getImg() {
		return this.img;
	}

	public void setResistance(double res) {
		this.resistance = res;
	}
	
	public void setImg(String path) {
		this.img = Asteroids.loadImage(this.getClass().getResource(path).getPath());
	}
}
