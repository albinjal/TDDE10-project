package utilites;

import java.awt.geom.Point2D;

public class MyPoint extends Point2D.Double {

	public MyPoint() {
		super();
	}
	
	public MyPoint(double x, double y) {
		super(x, y);
	}
	
	public void move(double x, double y) {
		this.setLocation(this.getX() + x, this.getY() + y);
	}
	
	public MyPoint relativePoint(double x, double y) {
		return new MyPoint(x + this.getX(), y + this.getY());
	}
	
	public MyPoint add(MyPoint point) {
		return point.relativePoint(this.getX(), this.getY());
	}
	
	public MyPoint subtract(MyPoint point) {
		return this.add(point.multiply(-1));
	}
	
	public MyPoint multiply(double k) {
		return new MyPoint(k * this.getX(), k * this.getY());
	}
	
	public double amount() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}
}
