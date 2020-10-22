package com.gamewindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.util.XMLParser;
import org.w3c.dom.Document;

import com.util.Typewriter;
import com.game.TheWorldInteraction;

public class Gui {

	TheWorldInteraction world = new TheWorldInteraction();
	XMLParser xmlParser = new XMLParser();
	Set<String> itemsCollectedSet = new HashSet<>();

	public static String userInput;

	private static final Boolean USE_NEW_LAYOUT = true;
	
	public static final Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);
	public static final Border redBorder = BorderFactory.createLineBorder(Color.RED);

	public static final String BACKGROUND_IMAGE_FILE_PATH = "./Files/backgroundImage.jpg";
	
	//Map Image details
	public static final String MAP_IMAGE_FILE_PATH = "./Files/map.jpg";
	public static final double MAP_SCALE_DOWN_PERCENT = 0.8;
	public static final int MAP_IMAGE_WIDTH = (int) (800 * MAP_SCALE_DOWN_PERCENT);
	public static final int MAP_IMAGE_HEIGHT = (int) (600 * MAP_SCALE_DOWN_PERCENT);

	//Flag to indicate if the current view is map view.
	private boolean isMapBeingShown = false;


	public JFrame gameWindow; // for main game window
	JFrame mapFrame; // for displaying map
	JPanel startPanel, displayOutputPanel, inputPanel, sidePanel, titlePanel, backgroundPanel, helpPanel,
			mainMiddlePanel;
	JButton startButton, mapButton, helpButton, enterButton;
	JLabel currentLocationLabel, itemsCollectedLabel, inputCommandLabel, titleLabel, backgroundLabel;
	ImageIcon backgroundImage;
	JTextArea helpTextArea;

	/**
	 * Creates and returns displayCurrentLocation label.
	 * 
	 * @return
	 */
	public JLabel getDisplayCurrentLocation() {
		displayCurrentLocation = new JLabel(" House");
		displayCurrentLocation.setFont(displayNumberFont);
		displayCurrentLocation.setBackground(Color.gray);
		displayCurrentLocation.setForeground(Color.BLACK);
//		displayCurrentLocation.setBorder(blueBorder);
		return displayCurrentLocation;
	}

	/**
	 * Creates and returns currentItemsCollected label.
	 * 
	 * @return
	 */
	public JLabel getCurrentItemsCollected() {
		currentItemsCollected = new JLabel("CurrentItems...");
		currentItemsCollected.setFont(displayNumberFont);
		currentItemsCollected.setBackground(Color.gray);
		currentItemsCollected.setForeground(Color.BLACK);
//		currentItemsCollected.setBorder(blueBorder);
		return currentItemsCollected;
	}

	public JTextArea getDisplayTextArea() {
		if (displayTextArea == null) {
			displayTextArea = new JTextArea(10, 30);
			displayTextArea.setEditable(false);
//	        Typewriter typewriter1 = new Typewriter();

			if (!USE_NEW_LAYOUT) {
				displayTextArea.setBounds(100, 345, 800, 300);
			}
			displayTextArea.setFont(displayAreaFont);
			displayTextArea.setBackground(Color.CYAN);
			displayTextArea.setForeground(Color.BLACK);
			displayTextArea.setLineWrap(true);
			xmlParser.parser();
			setMessage(xmlParser.gameIntro);
		}
		return displayTextArea;
	}

	public static JLabel displayCurrentLocation, currentItemsCollected;
	public static JTextField inputText;
	public static JTextArea displayTextArea;
	Container container;

	// Font and styling
	public static final Font titleFont = new Font("Rockwell", Font.BOLD, 50); // ORIGINAL
	public static final Font btnFont = new Font("Rockwell", Font.BOLD, 18); // ORIGINAL
	public static final Font displayAreaFont = new Font("Rockwell", Font.ITALIC, 18); // ORIGINAL
	public static final Font displayNumberFont = new Font("Rockwell", Font.BOLD, 18); // ORIGINAL

	// instantiate the classes
	private GameScreen startScreen = new GameScreen();
	private DisplayMap displayMap = new DisplayMap();
	private HelpMessageDisplay helpMessageDisplay = new HelpMessageDisplay();
	private ExecuteMove executeMove = new ExecuteMove();
	private InputTextFieldKeyAdapter enterKeyHandler = new InputTextFieldKeyAdapter();

	// Class to execute to display hidden helpPanel with instructions/commands with
	// click of help button
	public class HelpMessageDisplay implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isMapBeingShown) {
				guiPlayPanel.hideMapPanel();
				isMapBeingShown = false;
			}
//			helpPanel.setVisible(true);
//			setMessage("This is the test if the type writer works and displays in jTextArea", helpTextArea);
			setMessage("This is the test if the type writer works and displays in jTextArea");
		}
	}

	// Class to execute when start button is clicked
	public class GameScreen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// create startScreen
			createGameScreen();
			world.start();
			world.roomPrompt();
		}
	}

	// class to display map with the click of mapButton
	public class DisplayMap implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(USE_NEW_LAYOUT) {
				isMapBeingShown = true;
				guiPlayPanel.showMapPanel();
			}
			else {
				showMapSeparateWindow();
			}
		}
	}
	

	// Class to execute with the click of EnterButton or pressing enter after typing
	// in intputText
	public class ExecuteMove implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isMapBeingShown) {
				guiPlayPanel.hideMapPanel();
				isMapBeingShown = false;
			}
			startTheMove();
			inputText.setText("");
		}
	}

	// Class to handle 'Enter key' in the input text field.
	class InputTextFieldKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			// when you enter after inputting commands in input text field
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(isMapBeingShown) {
					guiPlayPanel.hideMapPanel();
					isMapBeingShown = false;
				}
				// like an clicking enter button
				 startTheMove();
				inputText.setText("");
			}
		}
	}

	private void showMapSeparateWindow() {

		// Design a map window
		mapFrame = new JFrame();
		mapFrame.setSize(600, 600);
		mapFrame.setResizable(false);
		mapFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mapFrame.getContentPane().setBackground(Color.BLACK);
		mapFrame.setLayout(null);

		Container mapContainer = mapFrame.getContentPane();

		// background panel design
		JPanel mapPanel = new JPanel();
		mapPanel.setBounds(0, 0, mapFrame.getWidth(), mapFrame.getHeight());

		// getting map from files
		JLabel mapLabel = new JLabel();
		mapLabel.setSize(mapFrame.getWidth(), mapFrame.getHeight());
		ImageIcon mapImage = new ImageIcon(new ImageIcon("./Files/map.jpg").getImage()
				.getScaledInstance(mapFrame.getWidth() - 4, mapFrame.getHeight() - 4, Image.SCALE_SMOOTH));
		mapLabel.setIcon(mapImage);
		mapPanel.add(mapLabel);
		mapContainer.add(mapPanel);
		mapFrame.setLocation(mapButton.getLocation().x - 400, mapButton.getLocation().y - 400);
		mapFrame.setVisible(true);
	}

	public Gui() {

		// game window design
		gameWindow = new JFrame("Witch Escape: Try to escape!");
//        gameWindow.setSize(1050,1000);//ORIGINAL
//		gameWindow.setSize(1050, 600);
//		gameWindow.setSize(Gui.MAP_IMAGE_WIDTH + 200, Gui.MAP_IMAGE_HEIGHT + 200);
		gameWindow.setSize(1200, 600);
		gameWindow.setResizable(false);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.BLACK);

		// ---NEW CODE BEGIN
		if (USE_NEW_LAYOUT) {
			GuiStartPanel guiStartPanel = new GuiStartPanel(this);
			setMainPanel(guiStartPanel);
			gameWindow.setVisible(true);
			return;
		}
		// ---NEW CODE END

		gameWindow.setLayout(null);

		// container
		container = gameWindow.getContentPane();

		// title label
		titleLabel = new JLabel("Witch Escape");
		titleLabel.setForeground(Color.lightGray);
		titleLabel.setFont(titleFont);

		// titlePanel design
		titlePanel = new JPanel();
		titlePanel.setBounds(100, 100, 800, 125);
		titlePanel.setOpaque(false);

		// add label to panel
		titlePanel.add(titleLabel);

		// add panel to container
		container.add(titlePanel);

		// background panel design
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, gameWindow.getWidth(), gameWindow.getHeight());

		// background label design
		backgroundLabel = new JLabel();
		backgroundLabel.setSize(backgroundPanel.getWidth(), backgroundPanel.getHeight());

		// setting background image
		backgroundImage = new ImageIcon(new ImageIcon("./Files/backgroundImage.jpg").getImage()
				.getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_SMOOTH));
		backgroundLabel.setIcon(backgroundImage);
		backgroundPanel.add(backgroundLabel);
		// add to the container
		container.add(backgroundPanel);

		// start panel design
		startPanel = new JPanel();
		startPanel.setBounds(400, 400, 200, 300);
		startPanel.setOpaque(false);
		backgroundLabel.add(startPanel);

		// buttons on start page
		Dimension dimension = new Dimension(200, 300);
		startButton = new JButton(" START ");

		startButton.setSize(dimension);
		startButton.setFont(btnFont);
		startButton.setOpaque(false);
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.GRAY);

		// adding event listener
		startButton.addActionListener(startScreen);
		startPanel.add(startButton);
		startButton.setFocusPainted(false);

		gameWindow.setVisible(true);
	}

	private void setMainPanel(JPanel panel) {
		Container contentPane = gameWindow.getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		gameWindow.revalidate();
	}

	public void createGameScreen() {

		// ---NEW CODE BEGIN
		if (USE_NEW_LAYOUT) {
			guiPlayPanel = new GuiPlayPanel(this);
			setMainPanel(guiPlayPanel);
			return;
		}
		// ---NEW CODE END

		// making panels invisible
		titlePanel.setBounds(100, 10, 800, 125);
		titlePanel.setVisible(true);
		startButton.setVisible(false);
		backgroundPanel.setVisible(false);

		Border line = BorderFactory.createLineBorder(Color.RED);
		// main display for outputs
		displayOutputPanel = new JPanel();
		displayOutputPanel.setBorder(line);
		displayOutputPanel.setBounds(100, 345, 800, 300);
		container.add(displayOutputPanel); // adding to the container
		// add to display panel
		displayOutputPanel.add(displayTextArea);

		sidePanel = new JPanel();
		sidePanel.setBounds(100, 130, 800, 200);
		sidePanel.setBackground(Color.GRAY);
		sidePanel.setLayout(new GridLayout(2, 2));

		container.add(sidePanel);

		sidePanel.add(getCurrentLocationLabel());
		sidePanel.add(getDisplayCurrentLocation());
		sidePanel.add(getItemsCollectedLabel());
		sidePanel.add(getCurrentItemsCollected());

		inputPanel = new JPanel();
		inputPanel.setBounds(100, 650, 800, 50);
		inputPanel.setLayout(new GridLayout(1, 3));

		inputCommandLabel = new JLabel("    Go Where");
		inputCommandLabel.setBounds(100, 650, 50, 50);
		inputCommandLabel.setFont(displayNumberFont);
		inputCommandLabel.setBackground(Color.GRAY);
		inputCommandLabel.setForeground(Color.BLACK);
		inputCommandLabel.setBorder(line);
		inputPanel.add(inputCommandLabel);
		container.add(inputPanel);

		inputPanel.add(getInputText());
		inputPanel.add(getEnterButton());
		inputPanel.add(getMapButton());
		inputPanel.add(getHelpButton());

		helpPanel = new JPanel();
		helpPanel.setBounds(100, 710, 800, 200);
		helpPanel.setVisible(false);
		container.add(helpPanel);

		helpTextArea = new JTextArea();
		helpTextArea.setBounds(100, 710, 800, 200);
		helpTextArea.setFont(displayAreaFont);
		helpTextArea.setBackground(Color.CYAN);
		helpTextArea.setForeground(Color.BLACK);
		helpTextArea.setLineWrap(true);

		helpTextArea.setOpaque(false);

		// add to display panel
		helpPanel.add(helpTextArea);


	}

	public JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton("Help");
			helpButton.setBorder(blueBorder);
			helpButton.setFont(displayNumberFont);
			helpButton.setBackground(Color.GRAY);
			helpButton.setForeground(Color.BLACK);
			helpButton.addActionListener(helpMessageDisplay);
			helpButton.setFocusPainted(false);
		}
		return helpButton;
	}

	public JButton getMapButton() {
		if (mapButton == null) {
			mapButton = new JButton("Show Map");
			mapButton.setBorder(blueBorder);
			mapButton.setFont(displayNumberFont);
			mapButton.setBackground(Color.GRAY);
			mapButton.setForeground(Color.BLACK);
			mapButton.addActionListener(displayMap);
			mapButton.setFocusPainted(false);
		}
		return mapButton;
	}

	public JButton getEnterButton() {
		if (enterButton == null) {
			enterButton = new JButton("Enter");
			enterButton.setBorder(blueBorder);
			enterButton.setFont(displayNumberFont);
			enterButton.setBackground(Color.GRAY);
			enterButton.setForeground(Color.BLACK);
			enterButton.addActionListener(executeMove);
			enterButton.setFocusPainted(false);
		}

		return enterButton;
	}

	public JTextField getInputText() {
		if (inputText == null) {
			inputText = new JTextField();
			inputText.setFont(displayNumberFont);
			inputText.setBackground(Color.GRAY);
			inputText.setForeground(Color.BLACK);
			inputText.setBorder(redBorder);
			inputText.addKeyListener(enterKeyHandler);
		}
		return inputText;
	}

	public JLabel getItemsCollectedLabel() {
		itemsCollectedLabel = new JLabel("     Items collected     ");
		itemsCollectedLabel.setFont(displayNumberFont);
		itemsCollectedLabel.setBackground(Color.gray);
		itemsCollectedLabel.setForeground(Color.BLACK);
		itemsCollectedLabel.setBorder(blueBorder);
		return itemsCollectedLabel;
	}

	public JLabel getCurrentLocationLabel() {
		currentLocationLabel = new JLabel("     Current com.game.Location     ");
		currentLocationLabel.setFont(displayNumberFont);
		currentLocationLabel.setBackground(Color.gray);
		currentLocationLabel.setForeground(Color.BLACK);
		currentLocationLabel.setBorder(blueBorder);
		return currentLocationLabel;
	}

	static File file = new File("./Files/locations.xml");
	static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	static DocumentBuilder documentBuilder;
	static Document document;

	private GuiPlayPanel guiPlayPanel;


//	// function using user input
//	public void startTheMove() {
//		startTheMove(inputText, displayTextArea);
//	}

	// function using user input
	public  String startTheMove() {

		String message = "";

		userInput = inputText.getText();

		String regex = "^[a-zA-Z]+$";
		if (!userInput.matches(regex)) {
			setMessage("Doesn't make any sense! What are you trying to do?");
		}else{
			userInput = userInput.toLowerCase();
			String[] userInputArray = userInput.split(" ");
			if(userInputArray[0].equals("open")){
				world.open(userInputArray);
				currentItemsCollected.setText(itemsCollectedSet.toString());
			}
		}
		return userInput;
	}

	public static void setMessage(String message) {
		Typewriter typewriter = new Typewriter(displayTextArea, message);
		typewriter.startDisplay();

	}

	public JFrame getMainWindow() {
		return this.gameWindow;
	}

	public void revalidate() {
		this.gameWindow.revalidate();
	}

}
