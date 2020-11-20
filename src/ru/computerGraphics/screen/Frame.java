package ru.computerGraphics.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
  private boolean go = true;
  private final Timer updateTimer;
  private final Button button;

  public Frame() {
    setTitle("Task 5");
    setPreferredSize(new Dimension(1366, 768));
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    BonfirePanel bonfirePanel = new BonfirePanel();
    JPanel actionPanel = new JPanel();

    updateTimer = new Timer(20, bonfirePanel);
    updateTimer.start();

    add(bonfirePanel);
    add(actionPanel, BorderLayout.PAGE_START);

    actionPanel.setSize(new Dimension(1366, 1200));

    button = new Button();
    actionPanel.add(button);
    button.addActionListener(this);
    button.setLabel("Остановить анимацию");

    JSlider slider = new JSlider();
    slider.setInverted(true);
    slider.setMinimum(8);
    slider.setMaximum(32);
    slider.setValue(20);
    slider.addChangeListener(e -> updateTimer.setDelay(((JSlider) e.getSource()).getValue()));
    actionPanel.add(slider);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    go = !go;

    if (go) {
      updateTimer.start();
      button.setLabel("Остановить анимацию");
    } else {
      updateTimer.stop();
      button.setLabel("Возобновить анимацию");
    }
  }
}
