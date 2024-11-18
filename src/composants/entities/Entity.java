package composants.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import composants.events.MovedEvent;
import composants.listeners.MovedListener;
import eventHandler.EventHandler;
import nicellipse.component.NiRectangle;

public abstract class Entity {
	
	private static int SPEED = 50 / 25;
	
	private NiRectangle component;
	private Color componentBaseColor;
	private EventHandler movedEventHandler;
	
	private Directions direction;
	private boolean readyToSync;
	
	public Entity(NiRectangle parent) {
		this.component = (NiRectangle) parent.add(this.createComponent());
		this.componentBaseColor = this.component.getBackground();
		this.movedEventHandler = new EventHandler();
		
		this.direction = Directions.LEFT;
		this.readyToSync = false;
	}
	
	protected abstract NiRectangle createComponent();
	
	public Point getLocation() {
		return this.component.getLocation();
	}
	
	public void setLocation(Point location) {
		this.component.setLocation(location);
	}
	
	public Dimension getDimension() {
		return this.component.getSize();
	}
	
	public Dimension getBounds() {
		return this.component.getParent().getSize();
	}
	
	public void animate() {
		
	}

	public boolean move() {
		boolean boundReached = false;
		Point loc = this.getLocation();
		Dimension dim = this.getDimension();
		Dimension parentDim = this.component.getParent().getSize();
		
		int xPos = loc.x;
		int yPos = loc.y;

		switch (this.direction) {
			case LEFT:
				if (xPos + dim.width + SPEED > parentDim.width) {
					xPos = parentDim.width - dim.width;
					this.setDirection(Directions.RIGHT);
					boundReached = true;
				} else {
					xPos += SPEED;
				}
				break;
			case RIGHT:
				if (xPos - SPEED < 0) {
					xPos = 0;
					this.setDirection(Directions.LEFT);
					boundReached = true;
				} else {
					xPos -= SPEED;
				}
				break;
			case DOWN:
				if (yPos + dim.height + SPEED > parentDim.height) {
					yPos = parentDim.height - dim.height;
					this.setDirection(Directions.UP);
					boundReached = true;
				} else {
					yPos += SPEED;
				}
				break;
			case UP:
				if (yPos - SPEED < 0) {
					yPos = 0;
					this.setDirection(Directions.DOWN);
					boundReached = true;
				} else {
					yPos -= SPEED;
				}
				break;
			default:
				break;
		}

		this.component.setLocation(xPos, yPos);
		this.movedEventHandler.send(new MovedEvent(this));
		return boundReached;
	}

	public void addMovedListener(MovedListener listener) {
		this.movedEventHandler.registerListener(MovedEvent.class, listener);
	}

	public void removeMovedListener(MovedListener listener) {
		this.movedEventHandler.unregisterListener(MovedEvent.class, listener);
	}
	
	public Directions getDirection() {
		return this.direction;
	}
	
	public void setDirection(Directions direction) {
		this.direction = direction;
	}
	
	public boolean isReadyToSync() {
		return this.readyToSync;
	}
	
	private Color tmp;
	public void setReadyToSync(boolean isReady) {
		if (isReady) {
			tmp = this.component.getBackground();
			this.component.setBackground(tmp.darker());
		} else {
			this.component.setBackground(this.componentBaseColor);
		}
		this.readyToSync = isReady;
	}
	
	public boolean canSyncWith(Entity entity) {
		return entity.isReadyToSync();
	}
	
	public boolean trySync(Entity entity) {
		if (this.isReadyToSync() && this.canSyncWith(entity)) {
			this.setReadyToSync(false);
			entity.setReadyToSync(false);
			this.animate();
			entity.animate();
			return true;
		}
		return false;
	}
	
	public enum Directions {
		UP, DOWN, LEFT, RIGHT
	}
}
