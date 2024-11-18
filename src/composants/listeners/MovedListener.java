package composants.listeners;

import composants.entities.Entity;

public class MovedListener {
	
	private Entity e;
	
	public MovedListener(Entity e) {
		this.e = e;
	}
	
	public void call(Object source) {
		if (source == null) {
			return;
		}
		this.e.trySync((Entity) source);
	}
}
