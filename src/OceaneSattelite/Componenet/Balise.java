package OceaneSattelite.Componenet;

import OceaneSattelite.*;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Balise extends ElementMobile implements Observateur {

	private static final long serialVersionUID = -3349914014710954200L;

	private StrategieDeplacement strategieDeplacement;
    private Memoire memoire;
    private boolean enCollecte;
    private boolean enSynchronisation;


    public Balise(StrategieDeplacement strategie, int capaciteMemoire) throws IOException {
        super("imagebalise.png", new Dimension(50, 50));
        this.strategieDeplacement = strategie;
        this.memoire = new Memoire(capaciteMemoire);
        this.enCollecte = true;
        this.enSynchronisation = false;
    }


    public void demarrerDeplacement(NiRectangle espace) {
    	new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (enCollecte) {
                    Point newPos = strategieDeplacement.deplacer(
                        getLocation(),
                        espace.getSize()
                    );
                    deplacer(newPos.x, newPos.y);
                    collecter();
                }
			}
		}, 0l, 20l);
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

        // Activer la synchronisation et afficher le cercle
        this.setSynchronisation(true);  // Montrer le cercle autour de la balise
        satellite.setSynchronisation(true);  // Montrer le cercle autour du satellite

        new Thread(() -> {
            descendre();
        }).start();

        // Simuler le délai de la synchronisation (par exemple 2 secondes)
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Désactiver la synchronisation après 2 secondes
                setSynchronisation(false);
                satellite.setSynchronisation(false);
            }
        }, 500);  // 2000ms = 2 secondes

        memoire.vider();
        enSynchronisation = false;
        enCollecte = true;
    }
}