package level;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import enemies.Enemy;
import powerups.Powerup;
import ships.Ship;
import ships.StandardShip;
import utilites.MyPoint;

public class PlayModel {
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Powerup> powerups;
	private int points = 1;
	public PlayModel() {
		this.ship = new StandardShip();
		this.ship.setPos(new MyPoint(100, 500));
		
	}
	
	public void draw(Graphics g) {
		ship.draw(g);
	}
	
	public void update() {
		this.ship.move(new MyPoint(1, 0));
		this.ship.rotate(1);

		
	}
}
