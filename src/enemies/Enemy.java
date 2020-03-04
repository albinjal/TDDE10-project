package enemies;

import constants.Enemies;
import utilites.GameObject;

public abstract class Enemy extends GameObject {

	public Enemy() {
		super();
	}
	
	
	public static Enemy generate(Enemies e) {
		switch(e) {
		case Asteroid: return new Asteroid();
		case Rocket: return new Rocket();
		}
		return null;
	}
}
