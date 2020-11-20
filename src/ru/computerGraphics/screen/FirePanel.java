package ru.computerGraphics.screen;

import ru.computerGraphics.model.Fire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirePanel extends JPanel implements ActionListener {
  private final Fire fire;

  public FirePanel() {
    fire = new Fire();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawImage(fire.getFrame(), 0, 0, getWidth(), getHeight() * 19 / 18, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }
}
