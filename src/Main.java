import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

import composants.entities.Balise;
import composants.entities.Satellite;
import composants.listeners.MovedListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static List<Satellite> satellites = new ArrayList<Satellite>();
	private static List<Balise> balises = new ArrayList<Balise>();
	
	public static void main(String[] args) {

		NiSpace space = new NiSpace("Space", new Dimension(1000, 700));

		NiRectangle container = new NiRectangle();
		container.setBackground(Color.white);
		container.setSize(new Dimension(900, 600));
		container.setLocation(50, 50);

		// Ciel.
		NiRectangle sky = new NiRectangle();
		sky.setBackground(Color.white);
		sky.setLocation(new Point(0, 0));
		sky.setSize(new Dimension(900, 300));
		container.add(sky);

		// ... Add satellites.
		Dimension skySize = sky.getSize();
		for (int i = 0; i < 10; i++) {
			Satellite satellite = new Satellite(sky);
			Dimension dim = satellite.getDimension();
		    
			satellite.setLocation(new Point(
	    		(int) (Math.random() * (skySize.width - dim.width)),
	    		(int) (Math.random() * (skySize.height / 1.5 - dim.height))
	    	));
			satellites.add(satellite);
		}

		// Mer.
		NiRectangle ocean = new NiRectangle();
		ocean.setBackground(Color.blue);
		ocean.setLocation(new Point(0, 300));
		ocean.setSize(new Dimension(900, 300));
		container.add(ocean);

		// ... Add balises.
		Dimension oceanSize = ocean.getSize();
		for (int i = 0; i < 10; i++) {
			Balise balise = new Balise(ocean);
			Dimension dim = balise.getDimension();
			
			balise.setLocation(new Point(
	    		(int) (Math.random() * (oceanSize.width - dim.width)),
	    		(int) (Math.random() * (oceanSize.height - dim.height))
	    	));
		    satellites.forEach((satellite) -> {
		    	satellite.addMovedListener(new MovedListener(balise));
		    });
			balises.add(balise);
		}

		space.add(container);
		space.openInWindow();
		space.repaint();
		
		// Boucle infinie.
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Main.satellites.forEach((satellites) -> satellites.move());
				Main.balises.forEach((balise) -> balise.move());
			}
		}, 0l, 25l);
		
		
		
		
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println(in.nextLine());
		}
	}
}
