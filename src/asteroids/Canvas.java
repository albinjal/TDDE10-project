package asteroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import states.GameModel;
import constants.Constants;

public class Canvas extends JPanel {
	private Set<Integer> keys = new HashSet<Integer>();
	private GameModel model;

	public Canvas(final GameModel model) {
		this.model = model;

		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {


			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				keys.add(e.getKeyCode());
				model.setKeys(keys);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				keys.remove(e.getKeyCode());
				model.setKeys(keys);
			}

		});
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				model.click(e);
			}
		});

	}

	public void paintComponent(Graphics g) {
		model.draw(g);
	}

}
