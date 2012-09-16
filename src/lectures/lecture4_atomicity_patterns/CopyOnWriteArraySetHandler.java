package lectures.lecture4_atomicity_patterns;

import java.util.concurrent.CopyOnWriteArraySet;

// uses an internal CopyOnWriteArraySet
public class CopyOnWriteArraySetHandler {
	
	class Handler {
		void handle() {}
	}
	
	CopyOnWriteArraySet<Handler> handlers = new CopyOnWriteArraySet<Handler>();
	
	private long internalState;
	
	public void addHandler(Handler h) {
		handlers.add(h);
	}
	
	private synchronized void changeState () {
		this.internalState = 0;
	}
	
	// whenever state is updated, handle methods for all handlers in the set are invoked
	public void update () {
		changeState();
		
		for (Handler h : handlers) {
			h.handle();
		}
	}
}
