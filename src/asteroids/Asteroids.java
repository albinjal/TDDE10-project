package asteroids;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.Constants;
import states.GameModel;

public class Asteroids {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameFrame frame = new GameFrame(model);

		while (true) {
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
	
	public static void drawCenteredString(Graphics g, String text, int size, int xPos, int yPos) {
	    // Get the FontMetrics
		Font font = new Font (Constants.font, Font.PLAIN, size);
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = xPos - (metrics.stringWidth(text) / 2);
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = yPos - (metrics.getHeight() / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}

}
