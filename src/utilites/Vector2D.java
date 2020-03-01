package utilites;

import java.util.Collection;
import java.util.Vector;

public class Vector2D extends Vector<Double> {

	public Vector2D() {
		super(2);
	}

	public Vector2D(double x, double y) {
		super(2);
		this.addElement(x);
		this.addElement(y);
	}
	
	public Double getX() {
		return this.get(0);
	}
	
	public Double getY() {
		return this.get(1);
	}
	
}
