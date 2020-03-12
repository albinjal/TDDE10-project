package enemies;

import data.Enemies;
import utilites.GameObject;

public abstract class Enemy extends GameObject {
	public static int killPoint = 30;
	public Enemies type;
	public Enemy(Enemies type) {
		super();
		setType(type);
	}
	

	public static Enemy generate(Enemies e) {
		switch(e) {
		case Asteroid: return new Asteroid();
		case Rocket: return new Rocket();
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
