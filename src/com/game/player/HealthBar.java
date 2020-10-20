//package com.game.player;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class HealthBar {
//
//        JFrame window;
//        JPanel healthBarPanel, buttonPanel;
//        Container con;
//        JProgressBar healthBar;
//        JButton button;
//        int hp;
//
//        DamageHandler damageHandler = new DamageHandler();
//
//
//        public void healthBar() {
//
//            window = new JFrame();
//            window.setSize(800, 600);
//            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.getContentPane().setBackground(Color.black);
//            window.setLayout(null);
//            con = window.getContentPane();
//
//            healthBarPanel = new JPanel();
//            healthBarPanel.setBounds(250, 250, 300, 30);
//            healthBarPanel.setBackground(Color.black);
//            con.add(healthBarPanel);
//
//
//            healthBar = new JProgressBar(0, 5);
//            healthBar.setPreferredSize(new Dimension(300, 30));
//            healthBar.setValue(100);
//            healthBarPanel.add(healthBar);
//
//            buttonPanel = new JPanel();
//            buttonPanel.setBounds(250, 300, 300, 40);
//            buttonPanel.setBackground(Color.black);
//            con.add(buttonPanel);
//
//            button = new JButton("Receive Damage");
//            button.setBackground(Color.black);
//            button.setForeground(Color.white);
//            button.setFocusPainted(false);
//            button.addActionListener(damageHandler);
//            buttonPanel.add(button);
//
//            hp = 5;
//
//            window.setVisible(true);
//
//        }
//
//        public void damageReceived() {
//            hp = hp - 1;
//            healthBar.setValue(hp);
//        }
//
//        public class DamageHandler implements ActionListener {
//            //        @Override
//            public void actionPerformed(ActionEvent event) {
//                damageReceived();
//
//            }
//        }
//    }
