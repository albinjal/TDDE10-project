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
}
