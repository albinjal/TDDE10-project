package ship;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import level.PlayModel;

/**
 * The StandardShip is the most basic type of ship which mostly is used just as
 * way of implementing Ship.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class StandardShip extends Ship {

	public StandardShip(PlayModel model) {
		super(model);
		this.setImg(this.loadImg("/assets/spacecraft.png"));
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 100);
	}

}
