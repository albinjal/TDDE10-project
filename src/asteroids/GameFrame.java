package asteroids;

import java.util.ArrayList;

import javax.swing.JFrame;

import states.GameModel;

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
