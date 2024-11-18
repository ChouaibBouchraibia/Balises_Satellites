package OceaneSattelite.Commands.Commands;

import java.awt.Dimension;
import java.util.Map;

import OceaneSattelite.ElementMobile;
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
		ElementMobile elem = null;
		if (type.equals("balise")) {
			Balise balise = new Balise(new DeplacementVertical(), 300);
			elem = balise;
			
			simulation.ajouterBalise(balise);
			simulation.lesSatellites().forEach(s -> s.ajouterObservateur(balise));
			balise.demarrerDeplacement(simulation.getOcean());
		} else if (type.equals("satellite")) {
			Satellite satellite = new Satellite();
			elem = satellite;
			
			simulation.ajouterSatellite(satellite);
			satellite.demarrerDeplacement(simulation.getSky());
		}
		
		if (elem == null) {			
			return "Invalide type '" + args[0] + "'";
		}
		
		int posX = 0;
		int posY = 0;

		if (args.length > 1) {
			posX = Integer.parseInt(args[1]);
		}
		if (args.length > 2) {
			posY = Integer.parseInt(args[2]);
		}
		
		if (posX != 0 || posY != 0) {
			Dimension size = elem.getSize();
			Dimension bound = elem.getParent().getSize();
			
			if (posX < 0) {
				posX = 0;
			} else if (posX + size.width > bound.width) {
				posX = bound.width - size.width;
			}
			
			if (posY < 0) {
				posY = 0;
			} else if (posY + size.height > bound.height) {
				posY = bound.height - size.height;
			}
			
			elem.setLocation(posX, posY);
		}
		
		return "Added";
	}

}
