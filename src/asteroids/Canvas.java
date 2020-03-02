package asteroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import states.GameModel;
import constants.Constants;

public class Canvas extends JPanel {
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private GameModel model;

	public Canvas(final GameModel model) {
		this.model = model;

		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {


			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_W && !keys.contains(e.getKeyCode())) {
					keys.add(e.getKeyCode());
				}
				if (e.getKeyCode() == KeyEvent.VK_A && !keys.contains(e.getKeyCode())) {
					keys.add(e.getKeyCode());
				}
				if (e.getKeyCode() == KeyEvent.VK_D && !keys.contains(e.getKeyCode())) {
					keys.add(e.getKeyCode());
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !keys.contains(e.getKeyCode())) {
					keys.add(e.getKeyCode());
				}
				model.keyPressed(keys);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_W && keys.contains(e.getKeyCode())) {
					keys.remove(new Integer(e.getKeyCode()));
				}
				if (e.getKeyCode() == KeyEvent.VK_A && keys.contains(e.getKeyCode())) {
					keys.remove(new Integer(e.getKeyCode()));
				}
				if (e.getKeyCode() == KeyEvent.VK_D && keys.contains(e.getKeyCode())) {
					keys.remove(new Integer(e.getKeyCode()));
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE && keys.contains(e.getKeyCode())) {
					keys.remove(new Integer(e.getKeyCode()));
				}
				model.keyPressed(keys);
			}

		});

	}

	public void paintComponent(Graphics g) {
		model.draw(g);
	}

}
