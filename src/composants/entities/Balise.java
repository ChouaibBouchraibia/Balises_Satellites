package composants.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import nicellipse.component.NiRectangle;

public class Balise extends Entity {

	
	
	public Balise(NiRectangle parent, Point startLocation) {
		super(parent, startLocation);
		
		NiRectangle parent2 = this.getParent();
		
		NiRectangle test = new NiRectangle();
		test.setDimension(new Dimension(10, 10));
		test.setBackground(Color.RED);
		
		parent2.add(test);
	}
	
	
}
