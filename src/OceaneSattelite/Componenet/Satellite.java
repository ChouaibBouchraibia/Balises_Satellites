package OceaneSattelite.Componenet;

import OceaneSattelite.Donnees;
import OceaneSattelite.ElementMobile;
import OceaneSattelite.Observable;
import OceaneSattelite.Observateur;
import nicellipse.component.NiRectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Satellite extends ElementMobile implements Observable {
    private List<Observateur> observateurs;
    private boolean disponible;
    private Thread threadDeplacement;

    public Satellite() {
        super(Color.gray, new Dimension(30, 30));
        this.observateurs = new ArrayList<>();
        this.disponible = true;
    }

    public void demarrerDeplacement(NiRectangle espace) {
        threadDeplacement = new Thread(() -> {
            while (enDeplacement) {
                if (getX() < espace.getWidth() - getWidth()) {
                    deplacer(getX() + 1, getY());
                } else {
                    deplacer(0, getY());
                }
                notifierObservateurs();
            }
        });
        threadDeplacement.start();
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
        // Simulation du temps de transfert
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                disponible = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}