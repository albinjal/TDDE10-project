package ship;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import asteroids.Asteroids;
import level.PlayModel;
import utilites.MyPoint;
/** The StandardShip is the most basic type of ship which mostly is used just as way of implementing Ship.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class StandardShip extends Ship {
	private BufferedImage image;
	public StandardShip(PlayModel model) {
		super(model);
		this.setImg(this.loadImg("/assets/spacecraft.png"));
	}

	@Override
	public Shape getHitboxShape() {
		return new Rectangle2D.Double(0, 0, 100, 100);
	}
	

}
