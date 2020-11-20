package ru.computerGraphics.screen;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
  public Frame() {
    setTitle("Task 5");

    setBackground(new Color(255, 255, 255));
    FirePanel firePanel = new FirePanel();
    setPreferredSize(new Dimension(1600, 900));
    setResizable(true);
    add(firePanel);
    Timer updateTimer = new Timer(20, firePanel);
    updateTimer.start();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
