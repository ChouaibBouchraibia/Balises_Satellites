package OceaneSattelite;

import nicellipse.component.NiRectangle;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class ElementMobile extends NiRectangle {
    protected int vitesse;
    protected boolean enDeplacement;
    private Image image; // Image de l'élément mobile

    public ElementMobile(String cheminImage, Dimension taille) {
        this.setSize(taille);         // Définit la taille du composant
        this.setBorder(null);
        this.vitesse = 1;
        this.enDeplacement = true;

        // Charge l'image depuis le fichier
        try {
            this.image = ImageIO.read(new File(cheminImage));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur : Impossible de charger l'image.");
        }
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Dessine l'image pour remplir le composant
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    protected void deplacer(int x, int y) {
        final Runnable deplacement = new Runnable() {
            public void run() {
                setLocation(new Point(x, y)); // Déplace l'image aux nouvelles coordonnées
                try {
                    Thread.sleep(2); // Pause pour lisser le déplacement
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
