package net.jselby.escapists.editor.layers.impl;

import net.jselby.escapists.editor.layers.Layer;
import net.jselby.escapists.editor.mapping.Map;
import net.jselby.escapists.editor.mapping.MapRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PerimeterLayer extends Layer {
    @Override
    public void render(Map map, MapRenderer renderer, BufferedImage image, Graphics2D g,
                       java.util.Map<Integer, BufferedImage> tileCache,
                       BufferedImage tiles, BufferedImage ground) {
        int right = Integer.parseInt((String) map.get("Perim.Right"));
        int left = Integer.parseInt((String) map.get("Perim.Left"));
        int bottom = Integer.parseInt((String) map.get("Perim.Bottom"));
        int top = Integer.parseInt((String) map.get("Perim.Top"));

        Color col = new Color(0.24f, 0.39f, 0.74f, 0.3f);

        g.setColor(col);
        g.fillRect(right, 0, (map.getWidth() * 16) - right, map.getHeight() * 16);
        g.fillRect(0, 0, left, map.getHeight() * 16);
        g.fillRect(left, bottom, right - left, (map.getHeight() * 16) - bottom);
        g.fillRect(left, 0, right - left, top);

        g.setColor(Color.BLUE);
        g.drawRect(left, top, right - left, bottom - top);
    }

    @Override
    public String getLayerName() {
        return "Perimeter";
    }

    @Override
    public boolean isTransparent() {
        return true;
    }
}
