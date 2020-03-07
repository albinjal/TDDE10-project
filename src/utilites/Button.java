package utilites;

import java.awt.Shape;

public class Button extends GameObject {
	private Shape hitbox;
	private Runnable action;

	public Button(MyPoint pos, Shape hitbox, String img, Runnable action) {
		super(pos);
		this.hitbox = hitbox;
		this.setImg(img);
		this.action = action;
	}

	@Override
	public Shape getHitboxShape() {
		return this.hitbox;
	}
	
	public void click() {
		System.out.println("lul");
		this.action.run();
	}

}
