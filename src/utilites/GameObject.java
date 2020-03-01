package utilites;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class GameObject {
	private AffineTransform transform = new AffineTransform();
	public GameObject() {
		
	}
	
	public void move(MyPoint distance) {
		this.transform.translate(distance.getX(), distance.getY());
	}
	
	public void setPos(MyPoint pos) {
		this.move(pos.subtract(this.getPos()));
	}
		
	public MyPoint getPos() {
		return new MyPoint(this.transform.getTranslateX(), this.transform.getTranslateY());
	}
	
	public AffineTransform getTransform() {
		return this.transform;
	}
}
