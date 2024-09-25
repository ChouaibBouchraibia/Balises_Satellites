package composants.interfaces;

import java.awt.Point;

import nicellipse.component.NiRectangle;

public interface EntityInterface {

	/**
	 * Retourne le parent.
	 *
	 * @return
	 */
	public NiRectangle getParent();
	
	/**
	 * Définis le parent.
	 *
	 * @param limits
	 * @return
	 */
	public boolean setParent(NiRectangle parent);
	
	/**
	 * Retourne la position de l'entité.
	 *
	 * @return
	 */
	public Point getLocation();
	
	/**
	 * Définis la position de l'entité.
	 *
	 * @param location
	 * @return
	 */
	public boolean setLocation(Point location);

	/**
	 * Déplace l'entité.
	 */
	public void move();
	
	/**
	 * Synchronise l'entité.
	 */
	public void sync();
	
	/**
	 * Effectue une petite animation.
	 */
	public void animate();

	/**
	 * Ajoute un écouteur de mouvement qui sera appelé lorsque l'entité à été déplacée.
	 *
	 * @param listener
	 * @return Vrai si le listener à été ajouté, faux sinon.
	 */
	public boolean addMovedListener(MovedListener listener);

	/**
	 * Retire un écouteur de mouvement.
	 *
	 * @param listener
	 * @return Vrai si le rappel à été retiré, faux sinon.
	 */
	public boolean removeMovedListener(MovedListener listener);

	/**
	 * Ajoute un écouteur de synchronisation qui sera appelé lorsque l'entité à été déplacée.
	 *
	 * @param listener
	 * @return Vrai si le listener à été ajouté, faux sinon.
	 */
	public boolean addSyncedListener(SyncedListener listener);
	
	/**
	 * Retire un écouteur de synchronisation.
	 *
	 * @param listener
	 * @return Vrai si le rappel à été retiré, faux sinon.
	 */
	public boolean removeSyncedListener(SyncedListener listener);
}
