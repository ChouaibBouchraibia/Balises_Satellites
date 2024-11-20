package OceaneSattelite.Commands.Commands;

import java.util.Map;

import OceaneSattelite.SimulationOcean;
import OceaneSattelite.Commands.CommandInterface;

public class RemoveCommand implements CommandInterface {

	@Override
	public String getName() {
		return "remove";
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
		if (args == null || args.length < 2) {
			return "Missing arguments";
		}
		
		String type = args[0].toLowerCase();
		int index = Integer.parseInt(args[1]);
		
		if (type.equals("balise")) {
			if (simulation.retirerBalise(index) == null) {
				return "Balise " + index + " does not exists";
			}
			return "Balise " + index + " removed";
		} else if (type.equals("satellite")) {
			if (simulation.retirerSatellite(index) == null) {
				return "Satellite" + index + " does not exists";
			}
			return "Satellite " + index + " removed";
		}
		
		return null;
	}

}
