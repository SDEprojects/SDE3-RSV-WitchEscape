package com.util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Validation {
    static String regex = "^[a-zA-Z]+$";
    static List<String> housePlacesList = new ArrayList<>(Arrays.asList("front door","back door", "window","closet"));
    public static boolean isInputValid(String input) {
    /*
        for open commands open front door,
        open back door
        open window
        open closet
     */
        List<String> inputList = Arrays.asList(input.split(" ")); //creates an array and stores in List when string input is passed
       for(String item:inputList){
           if(item.matches(regex)){
               System.out.println("true");
               return true;
           }else {
               System.out.println("false");
           }
       }
       return false;
    }

    public static void main(String[] args) {
        isInputValid("string 12121");
    }
}
