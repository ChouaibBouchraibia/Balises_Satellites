package OceaneSattelite.deplacement;

import OceaneSattelite.StrategieDeplacement;

import java.awt.*;

public class DeplacementSinusoidal implements StrategieDeplacement {
    private double angle = 0;
    private int amplitude = 50;
    private double frequence = 0.05;

    @Override
    public Point deplacer(Point position, Dimension limites) {
        int x = position.x;
        int baseY = limites.height / 2;

        if (x < limites.width - 20) {
            x += 1;
        } else {
            x = 0;
        }

        int y = baseY + (int)(amplitude * Math.sin(angle));
        angle += frequence;

        return new Point(x, y);
    }
}