package asteroids;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import data.Constants;
import states.GameModel;

/**
 * The Canvas is a JPanel filling the frame. It handles most of the IO such as
 * registering key / mouse clicks and painting the model inside itself.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class Canvas extends JPanel {

	private static final long serialVersionUID = 4490019152117443208L;
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
