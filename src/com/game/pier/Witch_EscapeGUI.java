package com.game.pier;

import javax.swing.*;

public class Witch_EscapeGUI {

    private JTextField console_print_out;
    private JTextField user_input;
    private JButton button1;
    private JLabel game_label;
    private JPanel GameGUI;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Witch_EscapeGUI");
        frame.setContentPane(new Witch_EscapeGUI().GameGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
