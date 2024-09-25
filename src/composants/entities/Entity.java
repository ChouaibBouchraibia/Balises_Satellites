package composants.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import composants.interfaces.EntityInterface;
import composants.interfaces.MovedListener;
import composants.interfaces.SyncedListener;

public abstract class Entity implements EntityInterface {

	private Rectangle limits;
	private Point location;
	private List<MovedListener> movedListeners;
	private List<SyncedListener> syncedListeners;
	
	public Entity(Rectangle limits, Point startLocation) {
		this.limits = limits;
		this.location = startLocation;
		this.movedListeners = new ArrayList<MovedListener>();
		this.syncedListeners = new ArrayList<SyncedListener>();
	}
	
	public Rectangle getLimits() {
		return this.limits;
	}
	
	public boolean setLimits(Rectangle limits) {
		this.limits = limits;
		return true;
	}
	
	@Override
	public Point getLocation() {
		return this.location;
	}

	@Override
	public boolean setLocation(Point location) {
		this.location = location;
		return true;
	}

	@Override
	public void move() {
		this.movedListeners.forEach((e) -> e.OnMoved(this));
	}

	@Override
	public void sync() {
		this.syncedListeners.forEach((e) -> e.OnSynced(this));
	}

	@Override
	public void animate() {}

	@Override
	public boolean addMovedListener(MovedListener listener) {
		if (listener == null || this.movedListeners.contains(listener)) {
			return false;
		}
		
		this.movedListeners.add(listener);
		return true;
	}

	@Override
	public boolean removeMovedListener(MovedListener listener) {
		if (listener == null) {
			return false;
		}

		int index = this.movedListeners.indexOf(listener);
		if (index == -1) {
			return false;
		}
		
		this.movedListeners.remove(index);
		return true;
	}

	@Override
	public boolean addSyncedListener(SyncedListener listener) {
		if (listener == null || this.syncedListeners.contains(listener)) {
			return false;
		}
		
		this.syncedListeners.add(listener);
		return true;
	}

	@Override
	public boolean removeSyncedListener(SyncedListener listener) {
		if (listener == null) {
			return false;
		}
		
		int index = this.syncedListeners.indexOf(listener);
		if (index == -1) {
			return false;
		}
		
		this.syncedListeners.remove(index);
		return true;
	}

	
}
