import nicellipse.component.NiLabel;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiSpace;

import javax.swing.*;

import composants.entities.Balise;
import composants.entities.Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		NiLabel label;
		NiSpace space = new NiSpace("Space", new Dimension(1000, 700));

		NiRectangle container = new NiRectangle();
		container.setBackground(Color.white);
		container.setSize(new Dimension(900, 600));
		container.setLocation(50, 50);

		//Ciel
		NiRectangle sky = new NiRectangle();
		sky.setBackground(Color.white);
		sky.setLocation(new Point(0, 0));
		sky.setSize(new Dimension(900, 300));
		container.add(sky);

		//Mer
		NiRectangle ocean = new NiRectangle();
		ocean.setBackground(Color.blue);
		ocean.setLocation(new Point(0, 300));
		ocean.setSize(new Dimension(900, 300));
		container.add(ocean);


		space.add(container);
		space.openInWindow();


		new Balise(ocean, null);
		

		space.repaint();

	}
}
