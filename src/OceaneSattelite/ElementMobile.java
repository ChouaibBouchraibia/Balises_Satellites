package OceaneSattelite;

import nicellipse.component.NiRectangle;

import javax.swing.*;
import java.awt.*;

public abstract class ElementMobile extends NiRectangle {
    protected int vitesse;
    protected boolean enDeplacement;

    public ElementMobile(Color couleur, Dimension taille) {
        this.setBackground(couleur);
        this.setSize(taille);
        this.vitesse = 1;
        this.enDeplacement = true;
    }

    protected void deplacer(int x, int y) {
        final Runnable deplacement = new Runnable() {
            public void run() {
                setLocation(new Point(x, y));
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            SwingUtilities.invokeAndWait(deplacement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void arreter() {
        this.enDeplacement = false;
    }

    public void reprendre() {
        this.enDeplacement = true;
    }
}