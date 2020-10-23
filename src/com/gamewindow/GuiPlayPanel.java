package com.gamewindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GuiPlayPanel extends GuiBackgroundImageLabelPanel {

	private static final String MAP_VIEW = "MAP_VIEW";
	private static final String DISPLAY_TEXT_AREA_VIEW = "DISPLAY_TEXT_AREA_VIEW";
	// panels to display current location and current items collected details.
	public JLabel displayCurrentLocation, currentItemsCollected;
	private JPanel mapPanel;
	private JPanel mainPanel;
	private JPanel centerPanel;
	private JPanel displayTextAreaPanel;

	/**
	 * Constructor
	 */
	public GuiPlayPanel(Gui gui) {
		super(gui);

		// main panel design
		setToBackgroundLabel(createMainPanel());
	}

	/**
	 * Creates the panel with all the button and display components.
	 * 
	 * @return mainPanel
	 */
	private JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);

		displayTextArea();

		return mainPanel;
	}

	private void addMainPanelComponents(String view) {
		JPanel westP = getWestPanel();
		JPanel southP = getSouthPanel();
		JPanel centerPanel = getCenterPanel();
		
		centerPanel.removeAll();
		if(MAP_VIEW.equals(view)) {
			centerPanel.add(getMapPanel());
		}else if(DISPLAY_TEXT_AREA_VIEW.equals(view)) {
			centerPanel.add(getDisplayTextAreaPanel());
		}
		
		mainPanel.removeAll();
		mainPanel.add(westP, BorderLayout.WEST);
		mainPanel.add(southP, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		getGui().revalidate();
	}

	/**
	 * Creates and returns the center panel with display display.
	 * 
	 * @return centerPanel
	 */
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setSize(Gui.MAP_IMAGE_WIDTH + 10, Gui.MAP_IMAGE_HEIGHT + 10);
			Border line = BorderFactory.createLineBorder(Color.RED);
			centerPanel.setBorder(line);
		}

		return centerPanel;
	}

	/**
	 * Creates and returns a panel with DisplayTextArea add to a background image
	 * label containing a dummy image that is of size map.
	 * 
	 * @return
	 */
	private JPanel getDisplayTextAreaPanel() {

		if (displayTextAreaPanel == null) {
			displayTextAreaPanel = new JPanel();
			displayTextAreaPanel.setSize(Gui.MAP_IMAGE_WIDTH, Gui.MAP_IMAGE_HEIGHT);

			// image label design
			JLabel imageLabel = new JLabel();

			// setting image image
			ImageIcon imageImage = new ImageIcon(new ImageIcon(Gui.MAP_SIZED_IMAGE_FILE_PATH).getImage()
					.getScaledInstance(Gui.MAP_IMAGE_WIDTH, Gui.MAP_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
			imageLabel.setIcon(imageImage);
			displayTextAreaPanel.add(imageLabel);
			// Add DisplayTexArea to the image label
			imageLabel.setLayout(new GridBagLayout());
			imageLabel.add(getGui().getDisplayTextArea());
		}
		return displayTextAreaPanel;
	}

	/**
	 * Adds the display text area to the center panel.
	 */
	private void displayTextArea() {
		addMainPanelComponents(DISPLAY_TEXT_AREA_VIEW);
	}

	/**
	 * Hides the display text area and shows the map panel.
	 */

	public void showMapPanel() {
		addMainPanelComponents(MAP_VIEW);
	}

	/**
	 * Hides the map panel and puts back the display text area.
	 */
	public void hideMapPanel() {
		// DO NOT DO: getMapPanel().setVisible(false)
		// displayTextArea() handles removal of map from the view.
		displayTextArea();
	}

	/**
	 * Creates and returns the south panel.
	 * 
	 * @return
	 */
	private JPanel getSouthPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));

		inputPanel = new JPanel();
		inputPanel.setBounds(100, 650, 800, 800);
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
	private JPanel getWestPanel() {
		Gui gui = getGui();

		// Create location details panel
		JPanel locationP = new JPanel();
		locationP.setLayout(new BoxLayout(locationP, BoxLayout.PAGE_AXIS));
		locationP.add(gui.getCurrentLocationLabel());
		locationP.add(gui.getDisplayCurrentLocation());

		// Create items collected details panel
		JPanel itemsP = new JPanel();
		itemsP.setLayout(new BoxLayout(itemsP, BoxLayout.PAGE_AXIS));
		itemsP.add(gui.getItemsCollectedLabel());
		itemsP.add(gui.getCurrentItemsCollected());

		JPanel westP = new JPanel();
		westP.setLayout(new BoxLayout(westP, BoxLayout.PAGE_AXIS));
//		westP.setLayout(new BorderLayout());

		westP.add(GuiUtil.getBorderedPanel(locationP), BorderLayout.NORTH);
		westP.add(GuiUtil.getBorderedPanel(itemsP), BorderLayout.SOUTH);
		return westP;
	}

	/*
	 * Creates and returns the map panel.
	 */
	private JPanel getMapPanel() {
		if (mapPanel == null) {
			mapPanel = new JPanel();
			mapPanel.setSize(Gui.MAP_IMAGE_WIDTH, Gui.MAP_IMAGE_HEIGHT);

			// map label design
			JLabel mapLabel = new JLabel();

			// setting map image
			ImageIcon mapImage = new ImageIcon(new ImageIcon(Gui.MAP_IMAGE_FILE_PATH).getImage()
					.getScaledInstance(Gui.MAP_IMAGE_WIDTH, Gui.MAP_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
			mapLabel.setIcon(mapImage);
			mapPanel.add(mapLabel);
		}
		return mapPanel;
	}

}
