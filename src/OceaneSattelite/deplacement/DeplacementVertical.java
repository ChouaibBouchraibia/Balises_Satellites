package OceaneSattelite.deplacement;

import OceaneSattelite.StrategieDeplacement;

import java.awt.*;

public class DeplacementVertical implements StrategieDeplacement {
    private boolean versLeBas = true;

    @Override
    public Point deplacer(Point position, Dimension limites) {
        int x = position.x;
        int y = position.y;

        if (versLeBas) {
            if (y < limites.height - 20) {
                y += 1;
            } else {
                versLeBas = false;
                y -= 1;
            }
        } else {
            if (y > 0) {
                y -= 1;
            } else {
                versLeBas = true;
                y += 1;
            }
        }

        return new Point(x, y);
    }
}