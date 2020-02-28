package ships;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import utilites.MyPoint;

public class StandardShip extends Ship {
	private static Dimension size = new Dimension(100, 100);
	public StandardShip(MyPoint start) {
		super(start);
	}
	@Override
	public Dimension getSize() {
		return size;
	}

}
