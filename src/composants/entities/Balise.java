package composants.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import nicellipse.component.NiRectangle;

public class Balise extends Entity {

	NiRectangle test;

	public Balise(NiRectangle parent, Point startLocation) {
		super(parent, startLocation);

		// Initialiser correctement le rectangle de test
		test = new NiRectangle();
		test.setDimension(new Dimension(10, 10));
		test.setBackground(Color.RED);

		// Ajouter le test au parent
		parent.add(test);

		// Lancer le mouvement dans un thread séparé
		new Thread(() -> movetest(test, parent)).start();
	}

	// Méthode pour déplacer le test
	void move(int x, int y) {
		final Runnable doit = () -> {
			test.setLocation(new Point(x, y));
			try {
				Thread.sleep(2);  // Petit délai pour rendre visible le mouvement
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		try {
			SwingUtilities.invokeAndWait(doit);
		} catch (InvocationTargetException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	// Méthode pour gérer le mouvement dans un cycle
	public void movetest(NiRectangle object, NiRectangle space) {
		int x = 0, y = 0;

		// Boucle de mouvement infini (vers la droite, bas, gauche, haut)
		while (true) {
			// Mouvement horizontal (de gauche à droite)
			while (x < space.getWidth() - object.getWidth()) {
				x = x + 1;
				this.move(x, y);
			}

			// Mouvement vertical (de haut en bas)
			while (y < space.getHeight() - object.getHeight()) {
				y = y + 1;
				this.move(x, y);
			}

			// Mouvement horizontal (de droite à gauche)
			while (x > 0) {
				x = x - 1;
				this.move(x, y);
			}

			// Mouvement vertical (de bas en haut)
			while (y > 0) {
				y = y - 1;
				this.move(x, y);
			}
		}
	}
}
