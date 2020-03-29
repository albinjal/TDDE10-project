package utilites;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Used to display how many lives the player has left.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
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
