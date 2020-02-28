package asteroids;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import states.GameModel;
import constants.Constants;

public class Canvas extends JPanel {
	
	private GameModel model;
	
	public Canvas(final GameModel model) {
		this.model = model;
		
		this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setFocusable(true);
        
        this.addKeyListener(new KeyAdapter() {
        	
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                model.keyPressed(e.getKeyCode());
            }
        });

	}
	
	public void paintComponent(Graphics g) {
        model.draw(g);
    }

}
