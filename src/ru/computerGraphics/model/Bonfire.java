package ru.computerGraphics.model;

import ru.computerGraphics.utils.Defaults;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bonfire {
  private final Integer width = 128;
  private final Integer height = 72;
  private final Integer[] backBuffer;
  private final Integer[] tempBuffer;
  private final Integer[] firePalette;

  public Bonfire() {
    backBuffer = makeBuf();
    tempBuffer = makeBuf();

    firePalette = new Integer[256 * 3];
    int i = 0;
    for (; i < Defaults.palette.length; ++i) {
      firePalette[i] = Defaults.palette[i] * 255 / 63;
    }

    for (; i < firePalette.length; ++i) {
      firePalette[i] = 255;
    }
  }

  private Integer[] makeBuf() {
    int s = height * width;

    Integer[] buffer = new Integer[s];
    for (int j = 0; j < s; ++j) {
      buffer[j] = 0;
    }
    return buffer;
  }

  public Image getFrame() {
    calculateNextFrame();
    return fillFrame();
  }

  private Image fillFrame(){
    Image fireFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    int s = width * (height - 2);
    int w = width;

    int widthTemp = 0, heightTemp = 0;
    Graphics2D g2 = (Graphics2D) fireFrame.getGraphics();
    for (int j = 0, i = 0; j < s; ++j, i += 4) {
      g2.setColor(new Color(firePalette[tempBuffer[j + w] * 3],
              firePalette[tempBuffer[j + w] * 3 + 1],
              firePalette[tempBuffer[j + w] * 3 + 2]));

      if (widthTemp == w) {
        widthTemp = 0;
        heightTemp += 1;
      }

      g2.fillRect(widthTemp++, heightTemp, 1, 1);
    }

    return fireFrame;
  }

  private void calculateNextFrame() {
    int max = width * (height - 1) - 1;
    int coolMax = width * (height - 4);
    for (int i = width + 1; i < max; ++i) {
      int v = backBuffer[i - 1 - width] +
              backBuffer[i - width] +
              backBuffer[i + 1 - width] +
              backBuffer[i - 1] +
              backBuffer[i + 1] +
              backBuffer[i - 1 + width] +
              backBuffer[i + width] +
              backBuffer[i + 1 + width];

      int finalV = v / 8;

      int cool = v & 3;

      if (cool == 0 && (i >= coolMax || finalV > 0)) {
        finalV = (255 + finalV) % 256;
      }
      tempBuffer[i] = finalV;
    }

    max = width * (height - 2);
    if (max >= 0) {
      System.arraycopy(tempBuffer, width, backBuffer, 0, max);
    }

    max = width * (height - 1);
    for (int x = width * (height - 7); x < max; ++x) {
      if (tempBuffer[x] < 15)
        tempBuffer[x] = (256 - tempBuffer[x] + 22) % 256;
    }
  }
}
