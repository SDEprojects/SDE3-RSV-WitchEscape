package com.game.xml;

import java.util.List;

public class TestReader {
    public static void main(String[] args) {
        // For StaXParser
        StaXParser read = new StaXParser();
        List<Location> readConfig = read.readConfig("locations.xml");
        for (Location location : readConfig) {
            System.out.println(location.getName());
//            System.out.println(location.getPrompt());
//            System.out.println(location.getDescription());
            System.out.println(location.toString());
        }
    }
}
