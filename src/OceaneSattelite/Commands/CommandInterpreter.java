package OceaneSattelite.Commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import OceaneSattelite.SimulationOcean;
import OceaneSattelite.Commands.Commands.AddCommand;
import OceaneSattelite.Commands.Commands.EditCommand;
import OceaneSattelite.Commands.Commands.RemoveCommand;
import OceaneSattelite.Commands.Commands.TestCommand;

public class CommandInterpreter {

	private static Map<String, CommandInterface> commands = new HashMap<String, CommandInterface>();;
	
	static {
		registerCommands();
	}
	
	private static void registerCommands() {
		TestCommand testCommand = new TestCommand();
		commands.put(testCommand.getName().toLowerCase(), testCommand);
		
		AddCommand addCommand = new AddCommand();
		commands.put(addCommand.getName().toLowerCase(), addCommand);
		
		EditCommand editCommand = new EditCommand();
		commands.put(editCommand.getName().toLowerCase(), editCommand);
		
		RemoveCommand removeCommand = new RemoveCommand();
		commands.put(removeCommand.getName().toLowerCase(), removeCommand);
	}
	
	public static void start(SimulationOcean simulation) {
		new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			boolean running = true;
			
			while (running) {
	            String line = scanner.nextLine();
	            List<String> parts = Arrays.stream(line.split("\\s+"))
	            		.map(s -> s.trim())
	            		.filter(s -> s != "")
	            		.toList();
	            
	            if (parts.isEmpty()) {
	            	continue;
	            }
	            
	            String commandName = parts.get(0);
	            CommandInterface command = commands.get(commandName.toLowerCase());
	            if (command == null) {
	            	System.out.println("Unknown command '" + commandName + "'");
	            	continue;
	            }

                String out = null;
                try {
                    out = command.execute(simulation, parts.subList(1, parts.size()).toArray(String[]::new));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (out != null) {
	            	System.out.println(out);
	            }
	        }
			
			scanner.close();
	    }).start();
	}
}
