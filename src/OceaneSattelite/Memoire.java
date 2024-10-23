package OceaneSattelite;

import java.util.ArrayList;
import java.util.List;

public class Memoire {
    private final int capacite;
    private final List<Donnees> donnees;

    public Memoire(int capacite) {
        this.capacite = capacite;
        this.donnees = new ArrayList<>();
    }

    public boolean estPleine() {
        return donnees.size() >= capacite;
    }

    public void stocker(Donnees donnee) {
        if (!estPleine()) {
            donnees.add(donnee);
        }
    }

    public List<Donnees> getDonnees() {
        return new ArrayList<>(donnees);
    }

    public void vider() {
        donnees.clear();
    }
}