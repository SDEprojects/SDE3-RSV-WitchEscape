package com.util;

import com.game.TheWorldInteraction;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLParser {
    public static String gameIntro;
    public static ArrayList<ArrayList> locations = new ArrayList<>();
    public static List<String> get;
    public static List<String> open;
    public static List<String> trade;
    public static List<String> fight;
    public static Map<String, String> itemsWithStatsMap = new HashMap<>();

    public void parser(){
        ArrayList<HashMap> location = new ArrayList<>();
        HashMap<String, String> insideLocation = new HashMap<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse((new File("./Files/locations.xml")));
            document.getDocumentElement().normalize();

            //Store game Intro text
            gameIntro=document.getElementsByTagName("gameintro").item(0).getTextContent();//replaceAll("NL", "\n");
            //********---*******
            //Store get synonyms
            String [] getArray = document.getDocumentElement().getElementsByTagName("get").item(0).getTextContent().split(", ");
            get = Arrays.asList(getArray);
            //System.out.println(get);
            //Store open synonyms
            String [] openArray = document.getElementsByTagName("open").item(0).getTextContent().split(", ");
            open = Arrays.asList(openArray);
            //Store trade synonyms
            String [] tradeArray = document.getDocumentElement().getElementsByTagName("trade").item(0).getTextContent().split(", ");
            trade = Arrays.asList(tradeArray);

            String[] fightArray = document.getElementsByTagName("fight").item(0).getTextContent().split(",");
            fight = Arrays.asList(fightArray);
            //********---*******
            //Get all locations in the document
            NodeList allLocations = document.getElementsByTagName("location");

            //store key value of weapons into map
            NodeList nodeList=document.getElementsByTagName("itemsWithStats");
            for(int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String elements = element.getTextContent();
                    itemsWithStatsMap.put(element.getAttribute("type"),elements);
                }
            }
                    for(int i= 0; i< allLocations.getLength();i++){
                        Node node = allLocations.item(i);
                        if(node.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) node;
                            insideLocation.put("name", element.getElementsByTagName("name").item(0).getTextContent());
                            insideLocation.put("description", element.getElementsByTagName("description").item(0).getTextContent().replaceAll("NL", "\n"));
                            insideLocation.put("question", element.getElementsByTagName("question").item(0).getTextContent().replaceAll("NL", "\n"));
                            insideLocation.put("challenge", element.getElementsByTagName("challenge").item(0).getTextContent());
                            insideLocation.put("items", element.getElementsByTagName("items").item(0).getTextContent());
                            insideLocation.put("exits", element.getElementsByTagName("exits").item(0).getTextContent());
                            List<String> exits  = Arrays.asList(insideLocation.get("exits").split(", "));
                            //store values based on available exits
                            for (String exit : exits){
                                insideLocation.put(exit.replaceAll("\\s",""), element.getElementsByTagName(exit.replaceAll("\\s", "")).item(0).getTextContent());
                                insideLocation.put(exit.replaceAll("\\s","")+"desc", element.getElementsByTagName(exit.replaceAll("\\s", "")+"desc").item(0).getTextContent().replaceAll("NL", "\n"));
                            }
                            location.add(insideLocation);
                            locations.add(location);
                            //reset data structures from inside the loop
                            insideLocation = new HashMap<>();
                            location = new ArrayList<>();
                        }
                    }
                } catch (Exception e) {
                    String ex = e.toString();
                }

    }

    public static void main(String[] args) {
       XMLParser xmlParser = new XMLParser();
       xmlParser.parser();
        System.out.println(itemsWithStatsMap);

            }

}
