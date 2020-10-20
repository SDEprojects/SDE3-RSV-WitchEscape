package com.game.xml;
// Based off tutorial here: https://www.vogella.com/tutorials/JavaXML/article.html

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaXParser {
    // Add additional location tags here.
    static final String LOCATION = "location"; // This is one layer deep (similar to item in the example)
    static final String NAME = "name";
    static final String PROMPT = "prompt"; // This is two layers deep (similar to mode/unit/current/interactive in the example)
    static final String DESCRIPTION = "description";
    static final String NORTH = "north";
    static final String NACTION = "north_action";
    static final String EAST = "east";
    static final String EACTION = "east_action";
    static final String SOUTH = "south";
    static final String SACTION = "south_action";
    static final String WEST = "west";
    static final String WACTION = "west_action";

    // This is not currently getting the elements we need,
    // but I'll continue working on it to get the information we need extracted.

    @SuppressWarnings({ "unchecked", "null" })
    public List<Location> readConfig(String configFile) {
        List<Location> locations = new ArrayList<Location>();
        // Add another array here for additional XML files
        try {
            // Create a new instance of XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // read the XML document
            Location location = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement(); // This gets all tags
//                    System.out.println(startElement.getName());

                    // If we have an item element, we create a new item
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case LOCATION:
                            location = new Location();

                            // Gets the name attribute from the Location tag
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()){
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(NAME)){
                                    location.setName(attribute.getValue());
                                }
                            }
                            break;

                        case PROMPT:
                            event = eventReader.nextEvent();
                            location.setPrompt(event.asCharacters().getData());
                            break;

                        case DESCRIPTION:
                            event = eventReader.nextEvent();
                            location.setDescription(event.asCharacters().getData());
                            break;

                        case NORTH:
                            event = eventReader.nextEvent();
                            location.setNorth(event.asCharacters().getData());
                            break;

                        case NACTION:
                            event = eventReader.nextEvent();
                            location.setNorth_action(event.asCharacters().getData());
                            break;

                        case EAST:
                            event = eventReader.nextEvent();
                            location.setEast(event.asCharacters().getData());
                            break;

                        case EACTION:
                            event = eventReader.nextEvent();
                            location.setEast_action(event.asCharacters().getData());
                            break;

                        case SOUTH:
                            event = eventReader.nextEvent();
                            location.setSouth(event.asCharacters().getData());
                            break;

                        case SACTION:
                            event = eventReader.nextEvent();
                            location.setSouth_action(event.asCharacters().getData());
                            break;

                        case WEST:
                            event = eventReader.nextEvent();
                            location.setWest(event.asCharacters().getData());
                            break;

                        case WACTION:
                            event = eventReader.nextEvent();
                            location.setWest_action(event.asCharacters().getData());
                            break;

                        default:
                            System.out.println("Nothing matched your case statement. elementName was="+ elementName);
                            break;
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(LOCATION)) {
                        locations.add(location);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return locations;
    }
}