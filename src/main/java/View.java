import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// the actual GUI
public class View extends JTextPane {
	private BufferedImage image;
	private TexturePaint texturePaint;
	
	public View(String imgPath) throws IOException {
		super();
		image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imgPath));
		Rectangle rectangle = new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT);
		texturePaint = new TexturePaint(image, rectangle);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(texturePaint);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}