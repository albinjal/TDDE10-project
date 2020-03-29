package utilites;

import java.awt.Shape;
/** Represents a button.
 * A button is created by inserting a image (of the button), its position, its size/hitbox and the action that should be taken when it is pressed.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class Button extends GameObject {
	private Shape hitbox;
	private Runnable action;

	public Button(MyPoint pos, Shape hitbox, String img, Runnable action) {
		super(pos);
		this.hitbox = hitbox;
		this.setImg(this.loadImg(img));
		this.action = action;
	}

	@Override
	public Shape getHitboxShape() {
		return this.hitbox;
	}
	
	public void click() {
		this.action.run();
	}

}
