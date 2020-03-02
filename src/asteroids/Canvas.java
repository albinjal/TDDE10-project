package asteroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import states.GameModel;
import utilites.KeyMap;
import constants.Constants;

public class Canvas extends JPanel {

	private KeyMap keysPressed = new KeyMap();
	private GameModel model;

	public Canvas(final GameModel model) {
		this.model = model;

		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {

			/*
			 * @Override public void keyPressed(KeyEvent e) { 
			 * super.keyPressed(e);
			 * model.keyPressed(e.getKeyCode()); }
			 */
			
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_W)
					keysPressed.replace(e.getKeyCode(), true);
				if (e.getKeyCode() == KeyEvent.VK_A)
					keysPressed.replace(e.getKeyCode(), true);
				if (e.getKeyCode() == KeyEvent.VK_D)
					keysPressed.replace(e.getKeyCode(), true);
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					keysPressed.replace(e.getKeyCode(), true);
				model.keyPressed(keysPressed);
				System.out.println(keysPressed);

			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_W)
					keysPressed.replace(e.getKeyCode(), false);
				if (e.getKeyCode() == KeyEvent.VK_A)
					keysPressed.replace(e.getKeyCode(), false);
				if (e.getKeyCode() == KeyEvent.VK_D)
					keysPressed.replace(e.getKeyCode(), false);
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					keysPressed.replace(e.getKeyCode(), false);
				model.keyPressed(keysPressed);
			}
			
		});

	}

	public void paintComponent(Graphics g) {
		model.draw(g);
	}

}
