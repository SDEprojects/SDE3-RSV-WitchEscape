import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLParser {
    public static String gameIntro;
    public static ArrayList<ArrayList> locations = new ArrayList<>();

    public void parser(){
        ArrayList<HashMap> location = new ArrayList<>();
        HashMap<String, String> insideLocation = new HashMap<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse((new File("src/locations.xml")));
            document.getDocumentElement().normalize();
            //Store game Intro text
            gameIntro=document.getDocumentElement().getElementsByTagName("gameintro").item(0).getTextContent().replaceAll("NL", "\n");

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
            System.out.println(e.toString());
        }

    }


}