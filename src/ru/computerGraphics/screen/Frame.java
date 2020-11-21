package ru.computerGraphics.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
  private final BonfirePanel bonfirePanel;
  private boolean go = true;
  private final Timer updateTimer;
  private final Button pauseButton;
  private final Button restartButton;

  public Frame() {
    setTitle("Task 5");
    setPreferredSize(new Dimension(1366, 768));
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    bonfirePanel = new BonfirePanel();
    JPanel actionPanel = new JPanel();

    updateTimer = new Timer(20, bonfirePanel);
    updateTimer.start();

    add(bonfirePanel);
    add(actionPanel, BorderLayout.PAGE_START);

    actionPanel.setSize(new Dimension(1366, 1200));

    pauseButton = new Button();
    actionPanel.add(pauseButton);
    pauseButton.addActionListener(this);
    pauseButton.setLabel("Остановить анимацию");

    restartButton = new Button();
    restartButton.addActionListener(this);
    restartButton.setLabel("Заново");

    JSlider slider = new JSlider();
    slider.setInverted(true);
    slider.setMinimum(8);
    slider.setMaximum(32);
    slider.setValue(20);
    slider.addChangeListener(e -> updateTimer.setDelay(((JSlider) e.getSource()).getValue()));
    actionPanel.add(slider);
    actionPanel.add(restartButton);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == pauseButton) {
      go = !go;

      if (go) {
        updateTimer.start();
        pauseButton.setLabel("Остановить анимацию");
      } else {
        updateTimer.stop();
        pauseButton.setLabel("Возобновить анимацию");
      }
    }

    if (e.getSource() == restartButton) {
      bonfirePanel.reloadBonfire();
    }
  }
}
