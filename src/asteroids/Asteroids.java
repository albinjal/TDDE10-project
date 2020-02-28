package asteroids;

import java.awt.Toolkit;

import states.GameModel;

public class Asteroids {

	public static void main(String[] args) {
		GameModel model = new GameModel();
        GameFrame frame = new GameFrame(model);
        
        
        while(true) {
        	long lastTime = System.currentTimeMillis();

            model.update();
            frame.repaint();
    
            long timer = System.currentTimeMillis() - lastTime;

            Toolkit.getDefaultToolkit().sync();

        }
        
	}

}
