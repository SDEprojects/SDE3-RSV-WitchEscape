package com.game.client;
import com.gamewindow.Gui;

import javax.swing.*;

public class StartGame {
    //This is the main method where the game starts
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalArgumentException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Gui();
    }
}
