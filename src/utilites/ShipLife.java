package utilites;

import java.awt.Shape;

public class ShipLife extends GameObject {
	

	public ShipLife(MyPoint pos) {
		super(pos);
		this.setImg(this.loadImg("/assets/spacecraft.png"));

	}
	
	@Override
	public Shape getHitboxShape() {
		// TODO Auto-generated method stub
		return null;
	}

}
