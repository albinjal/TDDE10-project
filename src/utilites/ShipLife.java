package utilites;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class ShipLife extends GameObject {
	

	public ShipLife(MyPoint pos) {
		super(pos);
		this.setImg(this.loadImg("/assets/spacecraft.png"));
		this.setRotation(Math.toRadians(-90));

	}
	
	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 50, 50);
	}

}
