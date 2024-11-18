package composants.entities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import nicellipse.component.NiRectangle;

public class Satellite extends Entity {
	
	public Satellite(NiRectangle parent) {
		super(parent);
	}

	@Override
	public NiRectangle createComponent() {
		NiRectangle comp = new NiRectangle();
		comp.setDimension(new Dimension(25, 25));
		comp.setBackground(Color.GREEN);
		return comp;
	}
	
	@Override
	public boolean move() {
		if (super.move()) {
			this.setReadyToSync(true);
			return true;
		}
		return false;
	}
}
