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

    public abstract void demarrerDeplacement(NiRectangle espace);
    
    protected void deplacer(int x, int y) {
    	setLocation(new Point(x, y));
    }

    public void arreter() {
        this.enDeplacement = false;
    }

    public void reprendre() {
        this.enDeplacement = true;
    }
}