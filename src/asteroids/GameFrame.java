package asteroids;

import javax.swing.JFrame;
import states.GameModel;

/** The Gameframe creates a frame that the canvas lives inside.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
public class GameFrame extends JFrame {
	
	public GameFrame(GameModel model) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.add(new Canvas(model));
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
	}

}
