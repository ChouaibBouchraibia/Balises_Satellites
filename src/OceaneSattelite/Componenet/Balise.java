package OceaneSattelite.Componenet;

import OceaneSattelite.*;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Balise extends ElementMobile implements Observateur {
    
	private static final long serialVersionUID = -3349914014710954200L;
	
	private StrategieDeplacement strategieDeplacement;
    private Memoire memoire;
    private boolean enCollecte;
    private boolean enSynchronisation;

    public Balise(StrategieDeplacement strategie, int capaciteMemoire) {
        super(Color.red, new Dimension(20, 20));
        this.strategieDeplacement = strategie;
        this.memoire = new Memoire(capaciteMemoire);
        this.enCollecte = true;
        this.enSynchronisation = false;
    }

    @Override
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
        satellite.recevoirDonnees(memoire.getDonnees());
        new Thread(() -> {
                descendre();
        }).start();
        memoire.vider();
        enSynchronisation = false;
        enCollecte = true;
    }
}