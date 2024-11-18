package composants.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import nicellipse.component.NiRectangle;

public class Balise extends Entity {
	
	private static int nb_instances = 0;
	private static int nb_synching_max = 0;
	private static int nb_synching = 0;
	
	private Random rand;
	private boolean canSwitchDirection;

	private SyncStatus syncStatus;
	private Directions oldDirection;
	private int oldYPos;
	
	public Balise(NiRectangle parent) {
		super(parent);
		
		nb_synching_max = ++nb_instances / 2; // Half can sync at the same time
		
		this.rand = new Random();
		this.canSwitchDirection = true;

		this.syncStatus = SyncStatus.NONE;
	}
	
	@Override
	public void finalize() {
		--nb_synching_max;
	}

	@Override
	public NiRectangle createComponent() {
		NiRectangle comp = new NiRectangle();
		comp.setDimension(new Dimension(50, 50));
		comp.setBackground(Color.RED);
		return comp;
	}

	@Override
	public boolean move() {
		// Do not move!
		if (this.syncStatus == SyncStatus.WAITING) {
			return true;
		}

		boolean boundReached = super.move();
		
		// Once done and back to the old position.
		if (this.syncStatus == SyncStatus.DONE && this.getLocation().y >= oldYPos) {
			this.syncStatus = SyncStatus.NONE;
			this.canSwitchDirection = true;
			this.setDirection(this.oldDirection);
			nb_synching--;
		}

		if (boundReached) {
			// Ready to sync!
			if (this.syncStatus == SyncStatus.GOING) {
				this.syncStatus = SyncStatus.WAITING;
				this.setReadyToSync(true);
			} else {
				this.canSwitchDirection = true;
			}
		} else if (this.syncStatus == SyncStatus.NONE) {
			if (rand.nextInt(100) > 98 && nb_synching < nb_synching_max) {
				// Wanna sync?
				nb_synching++;
				this.syncStatus = SyncStatus.GOING;
				this.oldDirection = this.getDirection();
				this.oldYPos = this.getLocation().y;
				this.setDirection(Entity.Directions.UP);
			} else if (this.canSwitchDirection && rand.nextInt(100) > 70) {
				// Random new direction cause.
				Directions[] directions = Entity.Directions.values();
				this.setDirection(directions[rand.nextInt(directions.length)]);
				this.canSwitchDirection = false;
			}
		}
		return boundReached;
	}
	
	@Override
	public boolean canSyncWith(Entity entity) {
		if (super.canSyncWith(entity)) {
			Point loc = this.getLocation();
			Dimension dim = this.getDimension();
			Point entityLoc = entity.getLocation();
			Dimension entityDim = entity.getDimension();
			
			int a = loc.x + dim.width;
			int b = entityLoc.x + entityDim.width;
			
			return (entityLoc.x >= loc.x && entityLoc.x <= a) || (b >= loc.x && b <= a);
		}
		return false;
	}
	
	@Override
	public boolean trySync(Entity entity) {
		if (super.trySync(entity)) {
			// Going back to the old position.
			this.setDirection(Entity.Directions.DOWN);
			this.setReadyToSync(false);
			this.syncStatus = SyncStatus.DONE;
			return true;
		}
		return false;
	}
	
	public enum SyncStatus {
		NONE, GOING, WAITING, DONE
	}
}
