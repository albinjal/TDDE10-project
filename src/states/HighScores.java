package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Set;

import asteroids.Asteroids;
import data.Constants;
import data.GameStates;
import utilites.Button;
import utilites.MyPoint;
import utilites.ShipLife;

public class HighScores extends GameState {
	private static int buttons = 2;
	private ArrayList<Integer> scores = new ArrayList<Integer>();

	public HighScores(GameModel model) {
		super(model);
		this.addButton(new Button(new MyPoint(180, Constants.SCREEN_HEIGHT - 60), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> this.menu()));
		this.addButton(new Button(new MyPoint(Constants.SCREEN_WIDTH-180, Constants.SCREEN_HEIGHT - 60), new Rectangle2D.Double(0, 0, 300, 100), "/assets/pButton.png", () -> this.play()));
		try{    
            FileInputStream file = new FileInputStream("data/.highscores.ser"); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            this.scores = (ArrayList<Integer>)in.readObject(); 
              
            in.close(); 
            file.close(); 
		} catch(IOException ex) { 
            System.out.println("IOException is caught"); 
        } catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
	}

	@Override
	public void update(Set<Integer> keys) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		this.drawBg(g, Color.black);
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Roboto", Font.PLAIN, 40));
		g2.setColor(Color.white);
		Asteroids.drawCenteredString(g, "Highscores", 60, Constants.centerX, 100);
		int i=0;
		for (int score : this.scores) {
			Asteroids.drawCenteredString(g, new Integer(score).toString(), 40, Constants.centerX, 200 + 50*i);
			i++;
		}

	}
	
	private void play() {
		this.getModel().swtichState(GameStates.Play);
	}

	private void menu() {
		this.getModel().swtichState(GameStates.Menu);

	}

	public static void saveScore(int score) {

	}

}
