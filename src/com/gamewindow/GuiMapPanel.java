package com.gamewindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GuiMapPanel extends GuiBackgroundImageLabelPanel {

	// panels to display current location and current items collected details.
	public JLabel displayCurrentLocation, currentItemsCollected;


	 //Constructor

	public GuiMapPanel(Gui gui) {
		super(gui);

		// play panel design
		JPanel playPanel = createPlayPanel();
		setToBackgroundLabel(playPanel);
	}


	//Creates the panel with all the button and display components.

	private JPanel createPlayPanel() {
		JPanel playPanel = new JPanel();
		playPanel.setLayout(new BorderLayout());
		playPanel.setOpaque(false);

		JPanel westPanel = createWestPanel();
		JPanel southPanel = createSouthPanel();
		JPanel centerPanel = createCenterPanel();

		playPanel.add(westPanel, BorderLayout.WEST);
		playPanel.add(southPanel, BorderLayout.SOUTH);
		playPanel.add(centerPanel, BorderLayout.CENTER);
//		startButton.setFocusPainted(false);

		return playPanel;
	}

	 //Creates and returns the center panel with display display.

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		Border line = BorderFactory.createLineBorder(Color.RED);
		centerPanel.setBorder(line);

		// add to panel
		centerPanel.add(getGui().getDisplayTextArea());

		return centerPanel;
	}

	private JPanel createSouthPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));

		inputPanel = new JPanel();
		inputPanel.setBounds(100, 650, 800, 50);
		inputPanel.setLayout(new GridLayout(1, 3));

		JLabel inputCommandLabel = new JLabel("    Go Where");
		inputCommandLabel.setBounds(100, 650, 50, 50);
		inputCommandLabel.setFont(Gui.displayNumberFont);
		inputCommandLabel.setBackground(Color.GRAY);
		inputCommandLabel.setForeground(Color.BLACK);
		inputCommandLabel.setBorder(Gui.blueBorder);
		inputPanel.add(inputCommandLabel);

		Gui gui = getGui();
		inputPanel.add(gui.getInputText());
		inputPanel.add(gui.getEnterButton());
		inputPanel.add(gui.getMapButton());
		inputPanel.add(gui.getHelpButton());

		return inputPanel;
	}

	/**
	 * Creates and returns the west panel with current location and item information
	 * display.
	 */
	private JPanel createWestPanel() {
		Gui gui = getGui();

		//Create location details panel
		JPanel locationP = new JPanel();
		locationP.setLayout(new BoxLayout(locationP, BoxLayout.PAGE_AXIS));
		locationP.add(gui.getCurrentLocationLabel());
		locationP.add(gui.getDisplayCurrentLocation());

		//Create items collected details panel
		JPanel itemsP = new JPanel();
		itemsP.setLayout(new BoxLayout(itemsP, BoxLayout.PAGE_AXIS));
		itemsP.add(gui.getItemsCollectedLabel());
		itemsP.add(gui.getCurrentItemsCollected());

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
//		westPanel.setLayout(new BorderLayout());

		westPanel.add(GuiUtil.getBorderedPanel(locationP), BorderLayout.NORTH);
		westPanel.add(GuiUtil.getBorderedPanel(itemsP), BorderLayout.SOUTH);
		return westPanel;
	}

}
