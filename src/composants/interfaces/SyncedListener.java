package composants.interfaces;

public interface SyncedListener {

	/**
	 * Fonction qui sera appelée lorsque l'entité à été synchronisée.
	 *
	 * @param e L'entité qui à été synchronisée.
	 */
	public void OnSynced(EntityInterface e);
}
