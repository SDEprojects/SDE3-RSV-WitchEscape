package com.gui;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Gui {
    JTextField displayTextField = new JTextField();
    JFrame jFrame = new JFrame();

    JTextArea textArea;
    public Gui(){

        jFrame.setSize(1000,800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(Color.gray);
        jFrame.setVisible(true);

        textArea.setSize(500,500);


        jFrame.add(textArea);
    }


    public JTextArea getTextArea() {
        return textArea;
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

    private static String instructionDisplay() {
//        NodeList nodeList = document.getElementsByTagName("locations");
//        NodeList childNodes = null;
//        if (document.getElementsByTagName("locations")!=null){
//            Node locationsNode = nodeList.item(0);
//            childNodes=locationsNode.getChildNodes();
//        }else{
//            System.out.println("null");
//        }
        String instructions = document.getElementsByTagName("introduction").item(0).getTextContent().replaceAll(";","\n");
        return instructions;

    }



    public static void main(String[] args) {
        System.out.println(instructionDisplay());
        new Gui();
    }
}
