package com.gamewindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.game.TheWorldInteraction;
import com.util.CharacterDisplay;
import com.util.CombatEngine;
import com.util.XMLParser;

public class Gui {
	static {

	}
	XMLParser xmlParser = new XMLParser();
	TheWorldInteraction theWorldInteraction = new TheWorldInteraction();
	CombatEngine combatEngine = new CombatEngine();
	public static Set<String> itemsCollectedSet = new HashSet<>();

	public static String userInput;

	public static final Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);
	public static final Border redBorder = BorderFactory.createLineBorder(Color.RED);

	public static final String BACKGROUND_IMAGE_FILE_PATH = "./Files/backgroundImage.jpg";
	
	//Flag to indicate if the current view is map view.
	private boolean isMapBeingShown = false;
	private boolean isHelpBeingShown = false;
	
	//Mapp Button Labels
	private static final String HIDE_MAP_LABEL = "Hide Map";
	private static final String SHOW_MAP_LABEL = "Show Map";
	//Help Button Labels
	private static final String HIDE_HELP_LABEL = "Hide Help";
	private static final String SHOW_HELP_LABEL = "Show Help";



	public static JFrame gameWindow; // for main game window
	private JFrame mapFrame; // for displaying map
	private JPanel startPanel, displayOutputPanel, inputPanel, sidePanel, titlePanel, backgroundPanel, helpPanel,
			mainMiddlePanel;
	private JButton startButton, mapButton, helpButton, enterButton;
	private JLabel currentLocationLabel, itemsCollectedLabel, inputCommandLabel, titleLabel, backgroundLabel;
	private ImageIcon backgroundImage;
	private static JTextArea helpTextArea;

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
		currentItemsCollected = new JLabel();
		currentItemsCollected.setFont(displayNumberFont);
		currentItemsCollected.setBackground(Color.gray);
		currentItemsCollected.setForeground(Color.BLACK);
//		currentItemsCollected.setBorder(blueBorder);
		return currentItemsCollected;
	}

	public JScrollPane getDisplayTextArea() {
		JScrollPane displayTextAreaScroll = null;
		if (displayTextArea == null) {
			displayTextArea = new JTextArea(20, 30);
			setTextAreaDisplayProperties(displayTextArea);

			//Add scrollbar
			displayTextAreaScroll = createScrollPane(displayTextArea);

			xmlParser.parser();
			theWorldInteraction.start();
			setCurrentLocation();
			//theWorldInteraction.evaluateChallenge();
//
		}
		return displayTextAreaScroll;
	}

	private void setTextAreaDisplayProperties(JTextArea textArea) {
		textArea.setEditable(false);
		textArea.setFont(displayAreaFont);
		textArea.setBackground(Color.CYAN);
		textArea.setForeground(Color.BLACK);
		textArea.setLineWrap(true);
	}
	
	public JScrollPane getHelpTextArea() {
		JScrollPane helpTextAreaScroll = null;
		if (helpTextArea == null) {
			helpTextArea = new JTextArea(20, 30);
			setTextAreaDisplayProperties(helpTextArea);
			
			//Add scrollbar
			helpTextAreaScroll = createScrollPane(helpTextArea);
		}
		return helpTextAreaScroll;
	}

	/**
	 * Returns Scrollpane with the given view component.
	 * 
	 * @param view
	 * @return
	 */
	private JScrollPane createScrollPane(Component view) {
		JScrollPane helpTextAreaScroll;
		helpTextAreaScroll = new JScrollPane(view, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		helpTextAreaScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		return helpTextAreaScroll;
	}

	public static JLabel displayCurrentLocation;
	public static JLabel currentItemsCollected;
	public static JTextField inputText;
	private static JTextArea displayTextArea;
	Container container;

	// Font and styling
	public static final Font titleFont = new Font("Times New Roman", Font.BOLD, 50); // ORIGINAL
	public static final Font btnFont = new Font("Times New Roman", Font.BOLD, 10); // ORIGINAL
	public static final Font displayAreaFont = new Font("Times New Roman", Font.ITALIC, 18); // ORIGINAL
	public static final Font displayNumberFont = new Font("Times New Roman", Font.BOLD, 18); // ORIGINAL
	

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
			if(isHelpBeingShown) {
				displayTextArea();
			}
			else {
				isHelpBeingShown = true;
				showHelpPanel();
				setHelpMessage("This is the test if the type writer works and displays in jTextArea");
				helpButton.setText(HIDE_HELP_LABEL);
			}
			
//			showHelpPanel();
//			setHelpMessage("This is the test if the type writer works and displays in jTextArea");
		}
	}

	// Class to execute when start button is clicked
	public class GameScreen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// create startScreen
			createGameScreen();

		}
	}

	// class to display map with the click of mapButton
	public class DisplayMap implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(isMapBeingShown) {
				displayTextArea();
				setCurrentLocation();
			}
			else {
				isMapBeingShown = true;
				showMapPanel();
				mapButton.setText(HIDE_MAP_LABEL);
				setCurrentLocation();
			}
		}
	}

	
	/**
	 * Shows the map.
	 */
	public void showMapPanel() {
		isHelpBeingShown = false;
		helpButton.setText(SHOW_HELP_LABEL);

		guiPlayPanel.showMapPanel();
	}

	/**
	 * Hides the map.
	 */
	private void displayTextArea() {
		isMapBeingShown = false;
		mapButton.setText(SHOW_MAP_LABEL);

		isHelpBeingShown = false;
		helpButton.setText(SHOW_HELP_LABEL);
		
		guiPlayPanel.displayTextArea();

	}
	
	
	/**
	 * Shows the help text area.
	 */
	private void showHelpPanel() {
		isMapBeingShown = false;
		mapButton.setText(SHOW_MAP_LABEL);

		guiPlayPanel.showHelpPanel();
	}
	
	// Class to execute with the click of EnterButton or pressing enter after typing
	// in intputText
	public class ExecuteMove implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			displayTextArea();
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
				displayTextArea();
				// like an clicking enter button
				 startTheMove();
				inputText.setText("");
			}
		}
	}

	public  Gui() {

		// game window design
		gameWindow = new JFrame("Witch Escape: Try to escape!");
//        gameWindow.setSize(1050,1000);//ORIGINAL
//		gameWindow.setSize(1050, 600);
//		gameWindow.setSize(Gui.MAP_IMAGE_WIDTH + 200, Gui.MAP_IMAGE_HEIGHT + 200);
		gameWindow.setSize(1200, 800);
		gameWindow.setResizable(false);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.BLACK);

		GuiStartPanel guiStartPanel = new GuiStartPanel(this);
		setMainPanel(guiStartPanel);
		gameWindow.setVisible(true);
	}

	private void setMainPanel(JPanel panel) {
		Container contentPane = gameWindow.getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		gameWindow.revalidate();
	}

	public void createGameScreen() {

		guiPlayPanel = new GuiPlayPanel(this);
		setMainPanel(guiPlayPanel);
	}

	public JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton(SHOW_HELP_LABEL);
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
			mapButton = new JButton(SHOW_MAP_LABEL);
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
		itemsCollectedLabel = new JLabel("Items Collected");
		itemsCollectedLabel.setFont(displayNumberFont);
		itemsCollectedLabel.setBackground(Color.gray);
		itemsCollectedLabel.setForeground(Color.BLACK);
		itemsCollectedLabel.setBorder(blueBorder);
		return itemsCollectedLabel;
	}

	public JLabel getCurrentLocationLabel() {
		currentLocationLabel = new JLabel("Current Location");
		currentLocationLabel.setFont(displayNumberFont);
		currentLocationLabel.setBackground(Color.gray);
		currentLocationLabel.setForeground(Color.BLACK);
		currentLocationLabel.setBorder(blueBorder);
		return currentLocationLabel;
	}

//	static File file = new File("./Files/locations.xml");
//	static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//	static DocumentBuilder documentBuilder;
//	static Document document;

	private GuiPlayPanel guiPlayPanel;


//	// function using user input
//	public void startTheMove() {
//		startTheMove(inputText, displayTextArea);
//	}

	// function using user input
	public  void startTheMove() {
		setCurrentLocation();
		String message = "";
		//theWorldInteraction.createCurrentRoom("house");
		userInput = inputText.getText(); //
		String[] inputArray= userInput.split(" ");
		String regex = "^[a-zA-Z]+$";
		ArrayList<String> inputList = new ArrayList<>(Arrays.asList(inputArray));

//		if (!userInput.matches(regex)) {
//			setMessage("Doesn't make any sense! What are you trying to do?");
		userInput = userInput.toLowerCase();

		if(XMLParser.open.contains(inputArray[0])){
			theWorldInteraction.open(inputArray);
			displayCurrentLocation.setText(theWorldInteraction.currentRoomObj.name);
			currentItemsCollected.setText(theWorldInteraction.inventory.toString());
			setCurrentLocation();
		}else if(XMLParser.get.contains(inputArray[0])) {
			theWorldInteraction.get(inputArray);
			//setMessage(TheWorldInteraction.currentRoomObj.question);
			currentItemsCollected.setText(theWorldInteraction.inventory.toString());
		}
		else if (inputArray[0].equalsIgnoreCase("challenge")){
			theWorldInteraction.evaluateChallenge();

		}else if(XMLParser.fight.contains(inputArray[0])){
			theWorldInteraction.evaluateChallenge();
			setCurrentLocation();
			displayCurrentLocation.setText(theWorldInteraction.currentRoomObj.name);
			currentItemsCollected.setText(theWorldInteraction.inventory.toString());

		}
		else if (XMLParser.trade.contains(inputArray[0])){
			theWorldInteraction.trade(inputArray);
			currentItemsCollected.setText(theWorldInteraction.inventory.toString());
		}
		else if(inputArray[0].equalsIgnoreCase("look")){
			setMessage(theWorldInteraction.currentRoomObj.roomDescription + "\n" +
					theWorldInteraction.currentRoomObj.question + "\n" + theWorldInteraction.itemsAvailableForPickUp());
					currentItemsCollected.setText(theWorldInteraction.inventory.toString());
					displayCurrentLocation.setText(theWorldInteraction.currentRoomObj.name);
		}
		else if(inputList.contains("ship")){
			theWorldInteraction.pickShip(inputList);

		}
	}



	public static void setMessage(String message) {
		CharacterDisplay typewriter = new CharacterDisplay(displayTextArea, message);
		typewriter.startDisplay();
	}
	
	public static void setHelpMessage(String message) {
		CharacterDisplay typewriter = new CharacterDisplay(helpTextArea, message);
		typewriter.startDisplay();
	}

	public JFrame getMainWindow() {
		return this.gameWindow;
	}

	public void revalidate() {
		this.gameWindow.revalidate();
	}

	public  void setCurrentLocation() {
		String name = theWorldInteraction.currentRoomObj.name;
		displayCurrentLocation.setText(name);
		GuiPlayPanel.setPinLocation(name);
	}

}
