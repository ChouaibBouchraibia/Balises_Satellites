package composants.entities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import nicellipse.component.NiRectangle;

public class Balise extends Entity {
	
	public Balise(NiRectangle parent, Point startLocation) {
		super(parent, startLocation);
	}

	@Override
	public Component createComponent() {
		NiRectangle test = new NiRectangle();
		test.setDimension(new Dimension(10, 10));
		test.setLocation(this.getLocation());
		test.setBackground(Color.RED);

		return test;
	}
}
