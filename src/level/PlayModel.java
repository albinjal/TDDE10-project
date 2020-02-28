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
	private int points = 0;
	public PlayModel() {
		this.ship = new StandardShip(new MyPoint(100, 500));
		this.ship.move(50, 50);
	}
	
	public void draw(Graphics g) {
		ship.draw(g);
	}
	
	public void update() {
		this.ship.move(1, 0);
	}
}
