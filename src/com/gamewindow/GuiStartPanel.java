package com.gamewindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuiStartPanel extends GuiBackgroundImageLabelPanel {

	public GuiStartPanel(Gui gui) {
		super(gui);

		// start panel design
		JPanel startPanel = createStartPanel();
//		this.add(startPanel);
		setToBackgroundLabel(startPanel);
	}

	private JPanel createStartPanel() {
		JPanel startPanel = new JPanel();
//		startPanel.setBounds(400, 400, 200, 300);
		startPanel.setOpaque(false);

		Dimension dimension = new Dimension(200, 300);
		JButton startButton = createStartButton(dimension);
		startPanel.add(startButton);
		startButton.setFocusPainted(false);

		return startPanel;
	}

	private JButton createStartButton(Dimension dimension) {
		JButton startButton = new JButton(" START ");

		startButton.setSize(dimension);
		startButton.setFont(Gui.btnFont);
		startButton.setOpaque(false);
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.GRAY);

		// adding event listener
		TitleScreen startScreen = new TitleScreen();
		startButton.addActionListener(startScreen);
		return startButton;
	}

	// Class to execute when start button is clicked
	public class TitleScreen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("startScreen..... pressed.");
			// create startScreen
			getGui().createGameScreen();
		}
	}

}
