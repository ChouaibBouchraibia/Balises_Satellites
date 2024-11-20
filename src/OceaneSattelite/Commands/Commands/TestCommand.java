package OceaneSattelite.Commands.Commands;

import java.util.Map;

import OceaneSattelite.SimulationOcean;
import OceaneSattelite.Commands.CommandInterface;

public class TestCommand implements CommandInterface {

	@Override
	public String getName() {
		return "test";
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
		return "This is a test";
	}
}
