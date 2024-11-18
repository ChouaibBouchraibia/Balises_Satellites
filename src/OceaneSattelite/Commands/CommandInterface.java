package OceaneSattelite.Commands;

import java.util.Map;

import OceaneSattelite.SimulationOcean;

public interface CommandInterface {

	public String getName();
	public String getDescription();
	public Map<String, Boolean> getArgs();
	public String execute(SimulationOcean simulation, String[] args);
	
}
