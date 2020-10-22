package com.gamewindow;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

/**
 * Utility methods for Java Swing GUI.
 *
 */
public class GuiUtil {

	/**
	 * Adds the given component to a JPanel with border.
	 *
	 */
	public static JPanel getBorderedPanel(JComponent comp) {
		JPanel p = new JPanel();
		Border blueLine = BorderFactory.createLineBorder(Color.blue);
		Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		CompoundBorder compound = BorderFactory.createCompoundBorder(blueLine, empty);
		p.setBorder(compound);
		// Add component to the panel
		p.add(comp);
		return p;
	}

	/**
	 * Create image label with the image loaded from the given path.
	 * 
	 * @param imagePath
	 * @param width
	 * @param height
	 * @return
	 */
	public static JLabel getImageLabel(String imagePath, int width, int height) {
		Image img = transformImage(createImageIcon(imagePath, ""), width, height);
		ImageIcon icon = new ImageIcon(img); // transform it back
		JLabel imageLabel = new JLabel("", icon, JLabel.CENTER);
		return imageLabel;
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 */
	public static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = GuiUtil.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Transforms the given icon's image to scaled instance based on the given width
	 * and height.
	 * 
	 * @param icon
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image transformImage(ImageIcon icon, int width, int height) {
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return newimg;
	}

	/**
	 * Creates BufferedImage based on the given path.
	 * 
	 * @param path
	 * @return
	 */
	public static BufferedImage getBufferedImage(String path) {
		try {
			java.net.URL imgURL = GuiUtil.class.getResource(path);
			if (imgURL != null) {
				return ImageIO.read(imgURL);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error Loading Image");
			e.printStackTrace();
		}
		return null;
	}
}
