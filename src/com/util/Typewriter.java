package com.util;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.stream.Stream;

import static com.gamewindow.Gui.displayTextArea;
public class Typewriter {

    private int charIndex = 0;

    // function to display string one character at a time, as a type writer effect
    public  Typewriter(JTextArea jTextArea, String text) {
        Timer timer = new Timer(20,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String jtextAreaString = jTextArea.getText();
                jtextAreaString +=text.charAt(charIndex);
                jTextArea.setText(jtextAreaString);
                charIndex++;
                if(charIndex >= text.length()){
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }


}

