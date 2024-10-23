package OceaneSattelite.Componenet;

import OceaneSattelite.*;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balise extends ElementMobile implements Observateur {
    private StrategieDeplacement strategieDeplacement;
    private Memoire memoire;
    private boolean enCollecte;
    private boolean enSynchronisation;
    private Thread threadDeplacement;
    private NiRectangle espace;
    private Point positionInitiale;


    public Balise(StrategieDeplacement strategie, int capaciteMemoire) {
        super(Color.red, new Dimension(20, 20));
        this.strategieDeplacement = strategie;
        this.memoire = new Memoire(capaciteMemoire);
        this.enCollecte = true;
        this.enSynchronisation = false;
    }

    public void demarrerDeplacement(NiRectangle espace) {
    this.espace = espace;
        threadDeplacement = new Thread(() -> {
            while (enDeplacement) {
                if (enCollecte) {
                    Point newPos = strategieDeplacement.deplacer(
                            this.getLocation(),
                            espace.getSize() );

                    deplacer(newPos.x, newPos.y);
                    collecter();
                }

            }
        });
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
            arreter();
        }
    }

    private void descendre() {

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
        memoire.vider();
        enSynchronisation = false;
        reprendre();
        demarrerDeplacement(espace);
        enCollecte = true;
    }
}