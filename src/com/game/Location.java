package com.game;

import java.util.HashMap;
import java.util.List;

public class Location {
    private String name;
    private String roomDescription;
    private String question;
    private String challenge;
    private List<String> availableExits;
    private List<String> roomItems;
    private HashMap<String, String> exitsTo;
    private HashMap<String, String> actionDescription;

    public Location(String name, String roomDescription, String question, String challenge, List<String> availableExits,
                    List<String> roomItems, HashMap<String, String> exitsTo, HashMap<String, String> actionDescription ){
        this.name = name;
        this.roomDescription = roomDescription;
        this.question = question;
        this.challenge = challenge;
        this.availableExits = availableExits;
        this.roomItems = roomItems;
        this.exitsTo = exitsTo;
        this.actionDescription = actionDescription;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public java.lang.String getQuestion() {
        return question;
    }

    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    public java.lang.String getChallenge() {
        return challenge;
    }

    public void setChallenge(java.lang.String challenge) {
        this.challenge = challenge;
    }

    public List<java.lang.String> getAvailableExits() {
        return availableExits;
    }

    public void setAvailableExits(List<java.lang.String> availableExits) {
        this.availableExits = availableExits;
    }

    public List<java.lang.String> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(List<java.lang.String> roomItems) {
        this.roomItems = roomItems;
    }

    public HashMap<java.lang.String, java.lang.String> getExitsTo() {
        return exitsTo;
    }

    public void setExitsTo(HashMap<java.lang.String, java.lang.String> exitsTo) {
        this.exitsTo = exitsTo;
    }

    public HashMap<java.lang.String, java.lang.String> getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(HashMap<java.lang.String, java.lang.String> actionDescription) {
        this.actionDescription = actionDescription;
    }
}