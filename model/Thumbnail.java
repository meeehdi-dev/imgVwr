package model;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;

public class Thumbnail extends Observable
{
	private BufferedImage original;
	private ImageIcon image;
	private String name;
	private String path;

	public Thumbnail(String path, int maxWidth, int maxHeight)
	{
		this.path = path;
		Path p = Paths.get(path);
		name = p.getFileName().toString();

		try {
			original = ImageIO.read(new File(path));

			int imgWidth = original.getWidth();
			int imgHeight = original.getHeight();

			double maxRatio = (double)maxWidth / maxHeight;
			double imgRatio = (double)imgWidth / imgHeight;
			double scale = (imgRatio > maxRatio) ?  (double)maxWidth / imgWidth : (double)maxHeight / imgHeight;

			int newWidth = (int)(imgWidth * scale);
			int newHeight = (int)(imgHeight * scale);

			image = new ImageIcon(original.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_FAST));
		}
		catch (Exception e) {}
	}

	public String getName()
	{
		return name;
	}

	public String getPath() {
		return path;
	}

	public ImageIcon getImage()
	{
		return image;
	}

	public BufferedImage getOriginalImage()
	{
		return original;
	}

	public void selected()
	{
		notifyObservers();
	}
}
