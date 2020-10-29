package com.util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Validation {
    static String regex = "^[a-zA-Z]+$";

    //
    public static boolean isInputValidString(String input) {
        input=input.replaceAll("\\s","");
        if(input.matches(regex)){
            return true;
        }
        return false;
    }


}
