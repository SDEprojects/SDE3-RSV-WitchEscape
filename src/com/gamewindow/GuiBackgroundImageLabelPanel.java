package com.gamewindow;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiBackgroundImageLabelPanel extends JPanel {

	private Gui gui;
	private JLabel backgroundLabel;

	public GuiBackgroundImageLabelPanel(Gui gui) {

		this.gui = gui;
		// //container
//        Container container = gameWindow.getContentPane();
//        
		// background panel design
		JPanel backgroundPanel = createBackgroundPanel(gui.getMainWindow());
//        // add to the container
//        container.add(backgroundPanel);

		this.add(backgroundPanel);

	}

	private JPanel createBackgroundPanel(JFrame gameWindow) {
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, gameWindow.getWidth(), gameWindow.getHeight());

		// background label design
		backgroundLabel = new JLabel();
		backgroundLabel.setSize(backgroundPanel.getWidth(), backgroundPanel.getHeight());

		// setting background image
		ImageIcon backgroundImage = new ImageIcon(new ImageIcon(Gui.BACKGROUND_IMAGE_FILE_PATH).getImage()
				.getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_SMOOTH));
		backgroundLabel.setIcon(backgroundImage);
		backgroundPanel.add(backgroundLabel);
		return backgroundPanel;
	}

	protected Gui getGui() {
		return gui;
	}

	protected void setToBackgroundLabel(Component comp) {
		backgroundLabel.removeAll();
		backgroundLabel.setLayout(new GridBagLayout());
		backgroundLabel.add(comp);
	}

}
