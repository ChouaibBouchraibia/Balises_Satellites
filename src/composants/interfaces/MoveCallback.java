package composants.interfaces;

import java.awt.Point;

public interface MoveCallback {

	/**
	 * Fonction qui sera appelée pour déplacer une entité.
	 *
	 * @param location La position actuelle de l'entité.
	 * @return La position à appliquer à l'entité.
	 */
	public Point move(Point location);
}
