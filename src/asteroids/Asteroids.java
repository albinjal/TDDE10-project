package asteroids;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import constants.Constants;
import states.GameModel;

public class Asteroids {

	public static void main(String[] args) {
		GameModel model = new GameModel();
        GameFrame frame = new GameFrame(model);
        
        
        while(true) {
        	long lastTime = System.currentTimeMillis();
        	double ms = 1000.0 / Constants.fps;
            model.update();
            frame.repaint();
    
            long timer = System.currentTimeMillis() - lastTime;

            Toolkit.getDefaultToolkit().sync();

            try {
                Thread.sleep((long) Math.max(ms - timer, 0));
            } catch (InterruptedException e) {
            }


        }
        
	}
	
	 public static BufferedImage loadImage(String imgPath) {
	        try {
	            return ImageIO.read(new File(imgPath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


}
