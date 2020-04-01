package asteroids;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import data.Constants;
import states.GameModel;

/**
 * Used as an entry point to the game. Contains the game loop logic as well as
 * some utility static methods.
 * 
 * @author Albin
 * @version 1.0
 * @since 1.0
 */
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
		Font font = new Font(Constants.font, Font.PLAIN, size);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = xPos - (metrics.stringWidth(text) / 2);
		int y = yPos - (metrics.getHeight() / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
