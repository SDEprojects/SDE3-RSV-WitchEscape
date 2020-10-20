package com.game.xml;

public class Location {
    // All XML tags have to be added as variables here
    private String name;
    private String prompt;
    private String description;
    private String north;
    private String north_action;
    private String east;
    private String east_action;
    private String south;
    private String south_action;
    private String west;
    private String west_action;
    //Add more here

    public Location(){
        //no-op
    }

    // GETTERS + SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getNorth_action() {
        return north_action;
    }

    public void setNorth_action(String north_action) {
        this.north_action = north_action;
    }

    public String getEast_action() {
        return east_action;
    }

    public void setEast_action(String east_action) {
        this.east_action = east_action;
    }

    public String getSouth_action() {
        return south_action;
    }

    public void setSouth_action(String south_action) {
        this.south_action = south_action;
    }

    public String getWest_action() {
        return west_action;
    }

    public void setWest_action(String west_action) {
        this.west_action = west_action;
    }

    // TO STRING METHOD

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", prompt='" + prompt + '\'' +
                ", description='" + description + '\'' +
                ", north='" + north + '\'' +
                ", north_action='" + north_action + '\'' +
                ", east='" + east + '\'' +
                ", east_action='" + east_action + '\'' +
                ", south='" + south + '\'' +
                ", south_action='" + south_action + '\'' +
                ", west='" + west + '\'' +
                ", west_action='" + west_action + '\'' +
                '}';
    }
}