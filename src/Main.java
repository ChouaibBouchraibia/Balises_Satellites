import OceaneSattelite.Componenet.Balise;
import OceaneSattelite.Componenet.Satellite;
import OceaneSattelite.SimulationOcean;
import OceaneSattelite.deplacement.DeplacementHorizontal;
import OceaneSattelite.deplacement.DeplacementSinusoidal;
import OceaneSattelite.deplacement.DeplacementVertical;

import java.io.IOException;

// Main.java
public class Main {
	public static void main(String[] args) throws IOException {
		SimulationOcean simulation = new SimulationOcean();

		// Création des balises avec différentes stratégies
		Balise baliseHorizontale = new Balise(new DeplacementHorizontal(),600);
		baliseHorizontale.setLocation(0, 200);

		Balise baliseVerticale = new Balise(new DeplacementVertical(),300);
		baliseVerticale.setLocation(200, 300);

		Balise baliseSinusoidale = new Balise(new DeplacementSinusoidal(),500);
		baliseSinusoidale.setLocation(200, 350);

		// Création des satellites
		Satellite satellite1 = new Satellite();
		satellite1.setLocation(0, 100);
		Satellite satellite2 = new Satellite();
		satellite2.setLocation(300, 200);

		// Ajout des éléments à la simulation
		simulation.ajouterBalise(baliseHorizontale);
		simulation.ajouterBalise(baliseVerticale);
		simulation.ajouterBalise(baliseSinusoidale);
		simulation.ajouterSatellite(satellite1);
		simulation.ajouterSatellite(satellite2);

		// Enregistrement des observateurs
		satellite1.ajouterObservateur(baliseHorizontale);
		satellite1.ajouterObservateur(baliseVerticale);
		satellite1.ajouterObservateur(baliseSinusoidale);
		satellite2.ajouterObservateur(baliseHorizontale);
		satellite2.ajouterObservateur(baliseVerticale);
		satellite2.ajouterObservateur(baliseSinusoidale);

		// Démarrage des mouvements
		baliseHorizontale.demarrerDeplacement(simulation.getOcean());
		baliseVerticale.demarrerDeplacement(simulation.getOcean());
		baliseSinusoidale.demarrerDeplacement(simulation.getOcean());
		satellite1.demarrerDeplacement(simulation.getSky());
		satellite2.demarrerDeplacement(simulation.getSky());
	}
}
