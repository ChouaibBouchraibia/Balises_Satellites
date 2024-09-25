import nicellipse.component.NiLabel;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

import javax.swing.*;

import composants.entities.Balise;
import composants.entities.Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static List<Balise> balises;
	
	private static NiRectangle sky;
	private static NiRectangle ocean;
	
	public static void main(String[] args) {
		balises = new ArrayList<Balise>();

		NiSpace space = new NiSpace("Space", new Dimension(1000, 700));

		NiRectangle container = new NiRectangle();
		container.setBackground(Color.white);
		container.setSize(new Dimension(900, 600));
		container.setLocation(50, 50);

		// Ciel.
		sky = new NiRectangle();
		sky.setBackground(Color.white);
		sky.setLocation(new Point(0, 0));
		sky.setSize(new Dimension(900, 300));
		container.add(sky);

		// Mer.
		ocean = new NiRectangle();
		ocean.setBackground(Color.blue);
		ocean.setLocation(new Point(0, 300));
		ocean.setSize(new Dimension(900, 300));
		container.add(ocean);

		// Balises.
		addBalises();

		space.add(container);
		space.openInWindow();
		space.repaint();
		
		// Boucle infinie.
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Main.balises.forEach((balise) -> balise.move());
				System.out.println("test");
			}
		}, 0l, 250l);
	}
	
	private static void addBalises() {
		for (int i = 0; i < 5; i++) {
			Balise b = new Balise(ocean, new Point(10, 10));
			balises.add(b);
		}
	}
}
