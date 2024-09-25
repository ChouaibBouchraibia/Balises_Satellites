package composants.entities;

import java.awt.Point;

public class Balise extends Entity {
	
	
	public Balise() {
		this.setMoveCallback((location) -> moveA(location));
	}

	private Point moveA(Point location) {
		return new Point(location.x + 2, location.y + 4);
	}
}
