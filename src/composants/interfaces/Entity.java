package composants.interfaces;

import java.awt.Point;

public interface Entity {

	/**
	 * Retire un écouteur de synchronisation.
	 *
	 * @param listener
	 * @return Vrai si le rappel à été retiré, faux sinon.
	 */
	public boolean removeSyncedListener(SyncedListener listener);

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
	 * Effectue une petite animation.
	 */
	public void animate();

	/**
	 * Définis le rappel qui sera utilisé pour déplacer l'entité.
	 *
	 * @param callback
	 * @return Vrai si le rappel est nouveau, faux sinon.
	 */
	public boolean setMoveCallback(MoveCallback callback);

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
	 * Définis le rappel qui sera utilisé pour synchroniser l'entité.
	 * 
	 * @param callback
	 * @return Vrai si le rappel est nouveau, faux sinon.
	 */
	public boolean setSyncCallback(SyncCallback callback);

	/**
	 * Ajoute un écouteur de synchronisation qui sera appelé lorsque l'entité à été déplacée.
	 *
	 * @param listener
	 * @return Vrai si le listener à été ajouté, faux sinon.
	 */
	public boolean addSyncedListener(SyncedListener listener);
}
