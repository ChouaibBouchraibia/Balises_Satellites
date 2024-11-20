package OceaneSattelite.Commands.Commands;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;

import OceaneSattelite.ElementMobile;
import OceaneSattelite.SimulationOcean;
import OceaneSattelite.Commands.CommandInterface;
import nicellipse.component.NiRectangle;

public class EditCommand implements CommandInterface {

	@Override
	public String getName() {
		return "edit";
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
		
		boolean isOcean = type.equals("ocean");
		if (isOcean || type.equals("sky")) {
			if (args.length < 2) {
				return "Missing action";
			}
			
			NiRectangle elem = isOcean ? simulation.getOcean() : simulation.getSky();
			String action = args[1].toLowerCase();

			if (action.equals("color")) {
				if (args.length < 3) {
					return "Missing color";
				}
				
				Color color = Color.getColor(args[2]);
				if (color == null) {					
					try {
						color = (Color) Color.class.getField(args[2].toUpperCase()).get(Color.class);
					} catch (Exception e) {}
				}
				if (color == null) {
					return "Unknown color '" + args[2] + "'";
				}
				
				elem.setBackground(color);
				return "Color changed";
			}
			
			return "Unknown action'" + args[1] + "'";
		}
		
		boolean isBalise = type.equals("balise");
		if (isBalise || type.equals("satellite")) {
			if (args.length < 2) {
				return "Missing index";
			}
			if (args.length < 3) {
				return "Missing action";
			}
			
			int index = Integer.parseInt(args[1]);
			ElementMobile elem;
			if (isBalise) {
				try {
					elem = simulation.lesBalises().get(index);
				} catch (Exception e) {					
					return "Balise " + index + "does not exists";
				}
			} else {
				try {
					elem = simulation.lesSatellites().get(index);
				} catch (Exception e) {					
					return "Satellites " + index + "does not exists";
				}
			}
			
			String action = args[2].toLowerCase();
			if (action.equals("pos") || action.equals("position")) {
				if (args.length < 4) {
					return "Missing position";
				}
				
				Point location = elem.getLocation();
				Dimension size = elem.getSize();
				Dimension bound = elem.getParent().getSize();
				
				location.x += Integer.parseInt(args[3]);
				if (args.length > 4) {
					location.y += Integer.parseInt(args[4]);
				}
				
				if (location.x < 0) {
					location.x = 0;
				} else if (location.x + size.width > bound.width) {
					location.x = bound.width - size.width;
				}
				
				if (location.y < 0) {
					location.y = 0;
				} else if (location.y + size.height > bound.height) {
					location.y = bound.height - size.height;
				}
				
				elem.setLocation(location);
				return "Position changed";
			}
			
			return "Unknown action'" + args[2] + "'";
		}
		
		return "Unknown type '" + args[0] + "'";
	}

}
