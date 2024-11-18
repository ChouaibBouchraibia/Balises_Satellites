package OceaneSattelite.Componenet;

import OceaneSattelite.*;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balise extends ElementMobile implements Observateur {
    private StrategieDeplacement strategieDeplacement;
    private Memoire memoire;
    private boolean enCollecte;
    private boolean enSynchronisation;
    private Thread threadDeplacement;



    public Balise(StrategieDeplacement strategie, int capaciteMemoire) throws IOException {
        super("imagebalise.png", new Dimension(50, 50));
        this.strategieDeplacement = strategie;
        this.memoire = new Memoire(capaciteMemoire);
        this.enCollecte = true;
        this.enSynchronisation = false;
    }

    public void demarrerDeplacement(NiRectangle espace) {
        threadDeplacement = new Thread(() -> {
            while (enDeplacement) {
                if (enCollecte) {
                    Point newPos = strategieDeplacement.deplacer(
                            this.getLocation(),
                            espace.getSize() );
                    deplacer(newPos.x, newPos.y);
                    collecter();
                }
                System.out.println("arret");
            }
        });
        System.out.println("Arret du deplacement");
        threadDeplacement.start();
    }

    private void collecter() {
        if (enCollecte && !memoire.estPleine()) {
            memoire.stocker(new Donnees());
        }
        if (memoire.estPleine()) {
            System.out.println("Memoire pleine");
            enCollecte = false;
            enSynchronisation = true;
            remonterEnSurface();
        }
    }

    private void descendre() {
        while (getY()<150) {
            deplacer(getX(), getY() + 1);
        }
    }

    private void remonterEnSurface() {
        while (getY()>0) {
            deplacer(getX(), getY() - 1);
        }
    }

    @Override
    public void miseAJour(Observable observable) {
        if (observable instanceof Satellite && enSynchronisation) {
            Satellite satellite = (Satellite) observable;
            if (satellite.estDisponible() && estAPortee(satellite)) {
                transfererDonnees(satellite);
            }
        }
    }

    private boolean estAPortee(Satellite satellite) {
        return Math.abs(satellite.getX() - this.getX()) < 50;
    }

    private void transfererDonnees(Satellite satellite) {
        System.out.println("Transfert de données");
        satellite.recevoirDonnees(memoire.getDonnees());
        new Thread(() -> {
                descendre();
        }).start();
        memoire.vider();
        enSynchronisation = false;
        enCollecte = true;
    }
}