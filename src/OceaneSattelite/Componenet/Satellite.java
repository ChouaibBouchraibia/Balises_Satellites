package OceaneSattelite.Componenet;

import OceaneSattelite.Donnees;
import OceaneSattelite.ElementMobile;
import OceaneSattelite.Observable;
import OceaneSattelite.Observateur;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Satellite extends ElementMobile implements Observable {
    
	private static final long serialVersionUID = 2561586763749861833L;
	
	private List<Observateur> observateurs;
    private boolean disponible;

    public Satellite() throws IOException {
        super("imagesatellite.png", new Dimension(40, 40));
        this.observateurs = new ArrayList<>();
        this.disponible = true;
    }


    public void demarrerDeplacement(NiRectangle espace) {
    	new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (!enDeplacement) {
					return;
				}

				if (getX() < espace.getWidth() - getWidth()) {
                    deplacer(getX() + 1, getY());
                } else {
                    disponible = true;
                    deplacer(0, getY());
                }
                notifierObservateurs();
			}
		}, 0l, 20l);
    }

    @Override
    public void ajouterObservateur(Observateur obs) {
        observateurs.add(obs);
    }

    @Override
    public void supprimerObservateur(Observateur obs) {
        observateurs.remove(obs);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur obs : observateurs) {
            obs.miseAJour(this);
        }
    }

    public boolean estDisponible() {
        return disponible;
    }

    public void recevoirDonnees(List<Donnees> donnees) {
        disponible = false;
        // Activer la synchronisation et afficher le cercle
        this.setSynchronisation(true);  // Montrer le cercle autour du satellite

        new Thread(() -> {
            try {
                Thread.sleep(500);
                disponible = true;

                // Désactiver la synchronisation après 2 secondes
                setSynchronisation(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}