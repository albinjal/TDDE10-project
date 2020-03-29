package enemies;
import data.Enemies;
import utilites.GameObject;
/** Represents an enemy. The type is used to determine if the enemy should split on death.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public abstract class Enemy extends GameObject {
	public static int killPoint = 30;
	public Enemies type;
	public Enemy(Enemies type) {
		super();
		setType(type);
	}
	

	public static Enemy generate(Enemies e) {
		switch(e) {
		case BigAsteroid: return new BigAsteroid();
		case Rocket: return new Rocket();
		default: System.out.print("Critical error");
			break;
		}
		return null;
	}
	
	public void setType(Enemies type) {
		this.type = type;
	}
	
	public Enemies getType() {
		return this.type;
	}
	
}
