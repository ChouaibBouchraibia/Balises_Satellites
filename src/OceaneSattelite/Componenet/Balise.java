package OceaneSattelite.Componenet;
import OceaneSattelite.*;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Balise extends ElementMobile implements Observateur {
    private static final long serialVersionUID = -3349914014710954200L;

    // Enum to represent different states of the Balise
    public enum EtatBalise {
        COLLECTE,
        SYNCHRONISATION,
        DESCENTE,
        ATTENTE,
        REMONTEE
    }

    private float originY;
    private StrategieDeplacement strategieDeplacement;
    private Memoire memoire;
    private EtatBalise etatCourant;
    private Timer timerDeplacement;
    private int vitesseDeplacement = 2; // Vitesse de déplacement par étape

    public Balise(StrategieDeplacement strategie, int capaciteMemoire) throws IOException {
        super("imagebalise.png", new Dimension(50, 50));
        this.strategieDeplacement = strategie;
        this.memoire = new Memoire(capaciteMemoire);
        this.etatCourant = EtatBalise.COLLECTE;
    }

    public void demarrerDeplacement(NiRectangle espace) {
        if (timerDeplacement != null) {
            timerDeplacement.cancel();
        }

        timerDeplacement = new Timer();
        timerDeplacement.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Point newPos = new Point();
                switch (etatCourant) {
                    case COLLECTE:
                        newPos = strategieDeplacement.deplacer(
                                getLocation(),
                                espace.getSize()
                        );
                        collecter();
                        break;
                    case REMONTEE:
                        newPos = new Point(getX(), getY() - vitesseDeplacement);
                        if (newPos.y <= 0) {
                            newPos.y = 0;
                            etatCourant = EtatBalise.ATTENTE;
                        }
                        break;
                    case ATTENTE:
                        newPos = new Point(getX(), getY());
                        break;
                    case DESCENTE:
                        newPos = new Point(getX(), getY() + vitesseDeplacement);
                        if (newPos.y >= originY) {
                            etatCourant = EtatBalise.COLLECTE;
                        }
                        break;
                    case SYNCHRONISATION:
                        newPos = new Point(getX(), getY());
                        break;
                }
                deplacer(newPos.x, newPos.y);

            }
        }, 0, 10);
    }

    private void collecter() {
        if (etatCourant == EtatBalise.COLLECTE && !memoire.estPleine()) {
            memoire.stocker(new Donnees());
        }
        if (memoire.estPleine()) {

            remonterEnSurface();
        }
    }

    private void descendre() {
        etatCourant = EtatBalise.DESCENTE;


    }

    private void remonterEnSurface() {
        this.originY = getY();
        etatCourant = EtatBalise.REMONTEE;


    }

    @Override
    public void miseAJour(Observable observable) {
        if (observable instanceof Satellite && etatCourant == EtatBalise.ATTENTE) {
            Satellite satellite = (Satellite) observable;

            if (satellite.estDisponible() && estAPortee(satellite)) {
                etatCourant = EtatBalise.SYNCHRONISATION;
                transfererDonnees(satellite);
            }
        }
    }

    private boolean estAPortee(Satellite satellite) {
        // Pour améliorer: Faire en fonction du centre des deux avec par exemple une marge de 10 pixels ou 25% de la taille de chaque.
        int xPos1 = getX();
        int xPos2 = satellite.getX();
        return xPos1 >= xPos2 && xPos1 <= xPos2 + satellite.getWidth() && getY() <= 1;
    }

    private void transfererDonnees(Satellite satellite) {
        satellite.recevoirDonnees(memoire.getDonnees());
        memoire.vider();

        descendre();
    }
}