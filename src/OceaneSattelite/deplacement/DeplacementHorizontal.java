package OceaneSattelite.deplacement;

import OceaneSattelite.StrategieDeplacement;

import java.awt.*;

public class DeplacementHorizontal implements StrategieDeplacement {
    private boolean versLaDroite = true;

    @Override
    public Point deplacer(Point position, Dimension limites) {
        int x = position.x;
        int y = position.y;

        if (versLaDroite) {
            if (x < limites.width - 20) {
                x += 1;
            } else {
                versLaDroite = false;
                x -= 1;
            }
        } else {
            if (x > 0) {
                x -= 1;
            } else {
                versLaDroite = true;
                x += 1;
            }
        }

        return new Point(x, y);
    }
}