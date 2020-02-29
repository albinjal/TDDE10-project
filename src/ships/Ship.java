package ships;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import level.PlayModel;
import utilites.MyPoint;

public abstract class Ship {
	private MyPoint position;
	protected BufferedImage image;
	private Rectangle2D.Double hitBox = new Rectangle2D.Double();
	public Ship(MyPoint start) {
		this.setPos(start);
	}
	
	public abstract Dimension getSize();
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this.hitBox);
		g2d.drawImage(this.getImg(), (int) this.upperLeft().getX(), (int) this.upperLeft().getY(), (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null );
	}
	
	public void setPos(MyPoint pos) {
		this.position = pos;
		this.patchBox();
	}
	
	public void move(int x, int y) {
		this.position.move(x, y);
		this.patchBox();
	}
	
	public MyPoint getPos() {
		return this.position;
	}
	
	private void patchBox() {
		this.hitBox.setFrameFromCenter(this.position, this.position.relativePoint(this.getSize().getWidth() / 2, this.getSize().getHeight() / 2));
	}
	
	public BufferedImage getImg() {
		return this.image;
	}
	
	public MyPoint upperLeft() {
		return this.position.relativePoint(-this.getSize().getWidth() / 2., -this.getSize().getHeight() / 2);
	}
	
}
