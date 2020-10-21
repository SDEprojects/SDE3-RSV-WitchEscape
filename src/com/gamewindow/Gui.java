package com.gamewindow;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.swing.*;

import javax.swing.border.Border;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import com.util.Typewriter;

public class Gui {

    public static JFrame gameWindow; // for main game window
    JFrame mapFrame; // for displaying map
    JPanel startPanel, displayOutputPanel, inputPanel, sidePanel, titlePanel, backgroundPanel, helpPanel;
    JButton startButton, mapButton, helpButton, enterButton;
    JLabel currentLocationLabel, itemsCollectedLabel, inputCommandLabel, titleLabel, backgroundLabel;
    ImageIcon backgroundImage;
    JTextArea helpTextArea;
    public static JLabel getDisplayCurrentLocation() {
        return displayCurrentLocation;
    }

    public static JLabel getCurrentItemsCollected() {
        return currentItemsCollected;
    }

    public static JTextArea getDisplayTextArea() {
        return displayTextArea;
    }

    public static JLabel displayCurrentLocation, currentItemsCollected;
    JTextField inputText;
    public static JTextArea displayTextArea;
    Container container;

    //Font and styling
    Font titleFont = new Font( "Times New Roman",Font.BOLD,50);
    Font btnFont = new Font("Times New Roman",Font.BOLD,18);
    Font displayAreaFont = new Font("Times New Roman",Font.ITALIC,18);
    Font displayNumberFont = new Font("Times New Roman", Font.BOLD,18);

    //instantiate the classes
    TitleScreen startScreen = new TitleScreen();
    DisplayMap displayMap = new DisplayMap();
    HelpMessageDisplay helpMessageDisplay = new HelpMessageDisplay();
    ExecuteMove executeMove = new ExecuteMove();
    //Class to execute to display hidden helpPanel with instructions/commands with click of help button
    public class HelpMessageDisplay implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            helpPanel.setVisible(true);
        }
    }

    //Class to execute when start button is clicked
    public class TitleScreen implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //create startScreen
            createGameScreen();

        }
    }
    //class to display map with the click of mapButton
    public class DisplayMap implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            showMap();
        }
    }

    // Class to execute with the click of EnterButton or pressing enter after typing in intputText
    public class ExecuteMove implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            startTheMove();
            inputText.setText("");
        }
    }
    private void showMap(){

        //Design a map window
        mapFrame = new JFrame();
        mapFrame.setSize(500,500);
        mapFrame.setResizable(false);
        mapFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mapFrame.getContentPane().setBackground(Color.BLACK);
        mapFrame.setLayout(null);

        Container mapContainer = mapFrame.getContentPane();

        //background panel design
        JPanel mapPanel = new JPanel();
        mapPanel.setBounds(0,0,mapFrame.getWidth(),mapFrame.getHeight());

        //getting map from files
        JLabel mapLabel = new JLabel();
        mapLabel.setSize(mapFrame.getWidth(),mapFrame.getHeight());
        ImageIcon mapImage = new ImageIcon(new ImageIcon("./Files/map.jpg").getImage().getScaledInstance(mapFrame.getWidth()-4,mapFrame.getHeight()-4,Image.SCALE_SMOOTH));
        mapLabel.setIcon(mapImage);
        mapPanel.add(mapLabel);
        mapContainer.add(mapPanel);
        mapFrame.setLocation(mapButton.getLocation().x-400, mapButton.getLocation().y-400);
        mapFrame.setVisible(true);
    }

    public Gui(){

        // game window design
        gameWindow = new JFrame("Witch Escape: Try to escape!");
        gameWindow.setSize(1050,920);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.BLACK);
        gameWindow.setLayout(null);

        //container
        container = gameWindow.getContentPane();

        // title label
        titleLabel = new JLabel("Witch Escape");
        titleLabel.setForeground(Color.lightGray);
        titleLabel.setFont(titleFont);

        //titlePanel design
        titlePanel = new JPanel();
        titlePanel.setBounds(200,100,920,125);
        titlePanel.setOpaque(false);

        //add label to panel
        titlePanel.add(titleLabel);

        // add panel to container
        container.add(titlePanel);

        //background panel design
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,gameWindow.getWidth(),gameWindow.getHeight());

        //background label design
        backgroundLabel = new JLabel();
        backgroundLabel.setSize(backgroundPanel.getWidth(),backgroundPanel.getHeight());

        //setting background image
        backgroundImage =new ImageIcon (new ImageIcon("./Files/backgroundImage.jpg").
                getImage().getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(backgroundImage);
        backgroundPanel.add(backgroundLabel);
        // add to the container
        container.add(backgroundPanel);

        //start panel design
        startPanel = new JPanel();
        startPanel.setBounds(800,400,200,80);
        startPanel.setOpaque(false);
        backgroundLabel.add(startPanel);

        // buttons on start page
        startButton = new JButton("START");
        startButton.setFont(btnFont);
        startButton.setForeground(Color.GRAY);
        startButton.setBackground(Color.GRAY);

        //adding event listener
        startButton.addActionListener(startScreen);
        startPanel.add(startButton);
        startButton.setFocusPainted(false);

        gameWindow.setVisible(true);
    }
    public void createGameScreen(){

        //making panels invisible
        titlePanel.setVisible(false);
        startButton.setVisible(false);
        backgroundPanel.setVisible(false);


        //main display for outputs
        displayOutputPanel = new JPanel();

        displayOutputPanel.setBounds(100,145,800,300);

        //displayOutputPanel.setOpaque(false);
        container.add(displayOutputPanel); // adding to the container

        displayTextArea = new JTextArea();
//        Typewriter typewriter1 = new Typewriter();
        Border line = BorderFactory.createLineBorder(Color.WHITE);
        displayTextArea.setBounds(100,145,800,300);
        displayTextArea.setFont(displayAreaFont);
        displayTextArea.setBackground(Color.CYAN);
        displayTextArea.setForeground(Color.BLACK);
        displayTextArea.setLineWrap(true);
        displayTextArea.setBorder(line);
        displayTextArea.setOpaque(false);
        displayTextArea.setText("Welcome, this is a test");
        //add to display panel
        displayOutputPanel.add(displayTextArea);

        sidePanel = new JPanel();
        sidePanel.setBounds(100,15,800,50);
        sidePanel.setBackground(Color.CYAN);
        sidePanel.setLayout(new GridLayout(1,4));

        container.add(sidePanel);

        currentLocationLabel = new JLabel("Current Location");
        currentLocationLabel.setFont(displayNumberFont);
        currentLocationLabel.setForeground(Color.WHITE);
        sidePanel.add(currentLocationLabel);

        displayCurrentLocation = new JLabel("House");
        displayCurrentLocation.setFont(displayNumberFont);
        displayCurrentLocation.setForeground(Color.WHITE);
        sidePanel.add(displayCurrentLocation);

        itemsCollectedLabel = new JLabel("Items collected");
        itemsCollectedLabel.setFont(displayNumberFont);
        itemsCollectedLabel.setForeground(Color.WHITE);
        sidePanel.add(itemsCollectedLabel);

        currentItemsCollected = new JLabel("");
        currentItemsCollected.setFont(displayNumberFont);
        currentItemsCollected.setForeground(Color.WHITE);
        sidePanel.add(currentItemsCollected);

        inputPanel = new JPanel();
        inputPanel.setBounds(100,565,800,50);
        inputPanel.setLayout(new GridLayout(1,3));


        inputCommandLabel = new JLabel("Go where");
        inputCommandLabel.setBounds(100,565,200,50);
        inputCommandLabel.setFont(displayNumberFont);
        inputCommandLabel.setBackground(Color.CYAN);
        inputCommandLabel.setForeground(Color.black);
        inputPanel.add(inputCommandLabel);
        container.add(inputPanel);

        inputText = new JTextField();
        inputText.setBounds(400,565,500,50);
        inputText.setFont(displayNumberFont);
        inputText.setBackground(Color.CYAN);
        inputText.setForeground(Color.black);
        inputPanel.add(inputText);

        // when you enter after inputting commands in input text field
        inputText.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()== KeyEvent.VK_ENTER){
                    //like an clicking enter button
                    startTheMove();
                    inputText.setText("");
                }
            }
        });

        enterButton = new JButton("Enter");
        enterButton.setBounds(400,565,100,50);
        enterButton.setFont(displayNumberFont);
        enterButton.setBackground(Color.cyan);
        enterButton.setForeground(Color.cyan);
        enterButton.addActionListener(executeMove);
        inputPanel.add(enterButton);
        enterButton.setFocusPainted(false);

        mapButton = new JButton("Show Map");
        mapButton.setBounds(400,565,100,50);
        mapButton.setFont(displayNumberFont);
        mapButton.setBackground(Color.cyan);
        mapButton.setForeground(Color.cyan);
        mapButton.addActionListener(displayMap);
        inputPanel.add(mapButton);
        mapButton.setFocusPainted(false);

        helpButton = new JButton("Help/Instructions");
        helpButton.setBounds(400,565,100,50);
        helpButton.setFont(displayNumberFont);
        helpButton.setBackground(Color.cyan);
        helpButton.setForeground(Color.cyan);
        helpButton.addActionListener(helpMessageDisplay);
        inputPanel.add(helpButton);
        helpButton.setFocusPainted(false);


        helpPanel= new JPanel();
        helpPanel.setBounds(100,625,800,200);
        helpPanel.setVisible(false);
        container.add(helpPanel);

        helpTextArea = new JTextArea();
        helpTextArea.setBounds(100,625,800,200);
        helpTextArea.setFont(displayAreaFont);
        helpTextArea.setBackground(Color.CYAN);
        helpTextArea.setForeground(Color.BLACK);
        helpTextArea.setLineWrap(true);
        helpTextArea.setBorder(line);
        helpTextArea.setOpaque(false);
        helpTextArea.setText("This is only text");

        //add to display panel
        helpPanel.add(helpTextArea);

    }




    static File file = new File("./Files/locations.xml");
    static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder documentBuilder;
    static Document document;
    static{
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //function using user input
    public void startTheMove(){
        String message = "This is just the test if the type writer works";

        String userInput = inputText.getText();

        String regex = "^[a-zA-Z]+$";
        if (userInput.matches(regex)){
            setMessage("This is and Valid input.");
        }else{
            setMessage("Doesn't make any sense! What are you trying to do");
        }

    }

    public void setMessage(String message){

        Typewriter typewriter = new Typewriter(displayTextArea, message);

    }

}
