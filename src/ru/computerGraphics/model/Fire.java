package ru.computerGraphics.model;

import ru.computerGraphics.utils.Defaults;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fire {
  private final Integer width = 128;
  private final Integer height = 72;
  private final Integer[] backBuffer;
  private final Integer[] tempBuffer;
  private final Integer[] firePalette;

  public Fire() {
    this.backBuffer = makeBuf();
    this.tempBuffer = makeBuf();

    this.firePalette = new Integer[256 * 3];
    int i = 0;
    for (; i < Defaults.palette.length; ++i) {
      firePalette[i] = Defaults.palette[i] * 255 / 63;
    }

    for (; i < firePalette.length; ++i) {
      firePalette[i] = 255;
    }
  }

  private Integer[] makeBuf() {
    int s = this.height * this.width;

    Integer[] g = new Integer[s];
    for (int j = 0; j < s; ++j) {
      g[j] = 0;
    }
    return g;
  }

  public Image draw() {
    Image fireFrame = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_ARGB);

    int s = width * (height - 2);
    int w = width;
    Integer[] g = this.tempBuffer;
    int w1 = 0, h1 = 0;
    Graphics2D g2 = (Graphics2D) fireFrame.getGraphics();
    for (int j = 0, i = 0; j < s; ++j, i += 4) {
      g2.setColor(new Color(firePalette[g[j + w] * 3],
              firePalette[g[j + w] * 3 + 1],
              firePalette[g[j + w] * 3 + 2]));

      if (w1 == w) {
        w1 = 0;
        h1 += 1;
      }

      g2.fillRect(w1++, h1, 1, 1);
    }

    calculateNextFrame();

    return fireFrame.getScaledInstance(1600, 900, Image.SCALE_AREA_AVERAGING);
  }

  private void calculateNextFrame() {
    Integer[] src = this.backBuffer;
    Integer[] temp = this.tempBuffer;

    int max = width * (height - 1) - 1;
    int coolMax = width * (height - 4);
    for (int i = width + 1; i < max; ++i) {
      int v = src[i - 1 - width] +
              src[i - width] +
              src[i + 1 - width] +
              src[i - 1] +
              src[i + 1] +
              src[i - 1 + width] +
              src[i + width] +
              src[i + 1 + width];

      int finalV = v / 8;

      int cool = v & 3;

      if (cool == 0 && (i >= coolMax || finalV > 0)) {
        finalV = (255 + finalV) % 256;
      }
      temp[i] = finalV;
    }

    max = width * (height - 2);
    if (max >= 0) {
      System.arraycopy(temp, width, src, 0, max);
    }

    max = width * (height - 1);
    for (int x = width * (height - 7); x < max; ++x) {
      if (temp[x] < 15)
        temp[x] = (256 - temp[x] + 22) % 256;
    }
  }
}
