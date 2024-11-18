package composants.events;

import composants.listeners.MovedListener;
import eventHandler.AbstractEvent;

public class MovedEvent extends AbstractEvent {

	private static final long serialVersionUID = -1266829939258698564L;

	public MovedEvent(Object source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		if (target == null) {
			return;
		}
		((MovedListener) target).call(this.source);
	}
}
