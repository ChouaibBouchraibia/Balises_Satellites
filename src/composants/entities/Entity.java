package composants.entities;
import java.awt.Point;

import composants.interfaces.EntityInterface;
import composants.interfaces.MoveCallback;
import composants.interfaces.MovedListener;
import composants.interfaces.SyncCallback;
import composants.interfaces.SyncedListener;

public abstract class Entity implements EntityInterface {

	
	private MoveCallback moveCallback;
	
	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setLocation(Point location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move() {
		this.moveCallback.move(this.getLocation());
	}

	@Override
	public void sync() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void animate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setMoveCallback(MoveCallback callback) {
		this.moveCallback = callback;
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addMovedListener(MovedListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMovedListener(MovedListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSyncCallback(SyncCallback callback) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSyncedListener(SyncedListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSyncedListener(SyncedListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

}
