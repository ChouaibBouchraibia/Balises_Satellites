package OceaneSattelite;

public interface Observable {
    void ajouterObservateur(Observateur obs);
    void supprimerObservateur(Observateur obs);
    void notifierObservateurs();
}