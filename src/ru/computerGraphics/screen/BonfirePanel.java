package ru.computerGraphics.screen;

import ru.computerGraphics.model.Bonfire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BonfirePanel extends JPanel implements ActionListener {
  private Bonfire bonfire;

  public BonfirePanel() {
    bonfire = new Bonfire();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawImage(bonfire.getFrame(), 0, 0, getWidth(), getHeight() * 19 / 18, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  public void setBonfire(Bonfire bonfire) {
    this.bonfire = bonfire;
  }
}
