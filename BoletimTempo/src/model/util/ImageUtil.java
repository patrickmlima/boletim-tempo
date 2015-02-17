package model.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Class which contains useful methods to manipulate images
 * @author Patrick M Lima
 *
 */
public class ImageUtil {
	
	/**
	 * Resize a image 
	 * @param im the Image object to be resized
	 * @param w an integer which represents the new width
	 * @param h an integer which represents the new height
	 * @return a BufferedImage object (a Image resized)
	 */
	public static BufferedImage resize(Image im, int w, int h) {
		BufferedImage bfi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) bfi.createGraphics();
		g2.drawImage(im, 0, 0, w, h, null);
		
		return bfi;
	}
	
	public static ImageIcon resize(int w, int h, Image im) {
		BufferedImage bfi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) bfi.createGraphics();
		g2.drawImage(im, 0, 0, w, h, null);
		
		return (new ImageIcon(bfi));
	}
}
