package com.util;

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
            System.out.println(get);
            //Store open synonyms
            String [] openArray = document.getDocumentElement().getElementsByTagName("open").item(0).getTextContent().split(", ");
            open = Arrays.asList(openArray);
            //Store trade synonyms
            String [] tradeArray = document.getDocumentElement().getElementsByTagName("trade").item(0).getTextContent().split(", ");
            trade = Arrays.asList(tradeArray);
            //********---*******
            //Get all locations in the document
            NodeList allLocations = document.getElementsByTagName("location");

            for(int i= 0; i< allLocations.getLength();i++){
                Node node = allLocations.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    insideLocation.put("name", element.getElementsByTagName("name").item(0).getTextContent());
                    insideLocation.put("description", element.getElementsByTagName("description").item(0).getTextContent().replaceAll("NL", "\n"));
                    insideLocation.put("question", element.getElementsByTagName("question").item(0).getTextContent().replaceAll("NL", "\n"));
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
    public static String getTextContentByTagNameAndType(String attType, String tagName){
        String result="";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse((new File("./Files/locations.xml")));
            document.getDocumentElement().normalize();
            Node node=document.getElementsByTagName(tagName).item(0);
            Element element= (Element) node;
            if (element.getAttribute("type").equals(attType)){
                result = element.getTextContent();
                System.out.println(result);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}
