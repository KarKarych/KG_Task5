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
    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHints(rh);
    g.drawImage(fire.draw(getWidth(), getHeight()), 0, 0,getWidth(), getHeight() * 19/18, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }
}
