//package com.we.xml;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Collection;
//
//// Based of Team 6's solution as per Rennie's instruction
//
//public class NewXMLParser {
//    public Collection<Location> locations = new ArrayList<>();
//
//    public Location getLocation(String locationInput){
//        for (Location location : locations) {
//            if (location.getName().equals(locationInput)){
//                return location;
//            }
//        }
//        return null;
//    }
//
//    public void initLocations() {
//        try{
//            File locationsFile = new File("locations.xml");
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(locationsFile);
//            doc.getDocumentElement().normalize();
//            NodeList locationList = doc.getElementsByTagName("location");
//
//            for (int temp = 0; temp < locationList.getLength(); temp++) {
//                Node location = locationList.item(temp);
//
//                Element locationEl = (Element) location;
//                // This can be written in a method that takes in a string and reused for each.
//                String name = locationEl.getElementsByTagName("name").item(0).getTextContent();
//
//                String prompt = locationEl.getElementsByTagName("prompt").item(0).getTextContent();
//                String description = locationEl.getElementsByTagName("description").item(0).getTextContent();
//                String north = locationEl.getElementsByTagName("north").item(0).getTextContent();
//                String east = locationEl.getElementsByTagName("east").item(0).getTextContent();
//                String south = locationEl.getElementsByTagName("south").item(0).getTextContent();
//                String west = locationEl.getElementsByTagName("east").item(0).getTextContent();
//
//                locations.add(new Location(name, prompt, description, north, east, south, west));
//            }
//        } catch (Exception e) {
//            System.out.println("there was an error");
//        }
//    }
//}
