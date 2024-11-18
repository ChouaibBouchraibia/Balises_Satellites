package OceaneSattelite.Commands.Commands;

import java.util.Map;

import OceaneSattelite.SimulationOcean;
import OceaneSattelite.Commands.CommandInterface;
import OceaneSattelite.Componenet.Balise;
import OceaneSattelite.Componenet.Satellite;
import OceaneSattelite.deplacement.DeplacementVertical;

public class AddCommand implements CommandInterface {

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public Map<String, Boolean> getArgs() {
		return null;
	}

	@Override
	public String execute(SimulationOcean simulation, String[] args) {
		if (args == null || args.length < 1) {
			return "Missing arguments";
		}
		
		String type = args[0].toLowerCase();
		int posX = 0;
		int posY = 0;

		if (args.length > 1) {
			posX = Integer.parseInt(args[1]);
		}
		if (args.length > 2) {
			posY = Integer.parseInt(args[2]);
		}
		
		
		if (type.equals("balise")) {
			Balise balise = new Balise(new DeplacementVertical(), 300);
			simulation.ajouterBalise(balise);
			simulation.lesSatellites().forEach(s -> s.ajouterObservateur(balise));
			balise.setLocation(posX, posY);
			balise.demarrerDeplacement(simulation.getOcean());
			return "Balise added";
		} else if (type.equals("satellite")) {
			Satellite satellite = new Satellite();
			simulation.ajouterSatellite(satellite);
			satellite.setLocation(posX, posY);
			satellite.demarrerDeplacement(simulation.getSky());
			return "Satellite added";
		}

		return "Invalide type '" + args[0] + "'";
	}

}
