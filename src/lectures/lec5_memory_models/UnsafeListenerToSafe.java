package lectures.lec5_memory_models;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
public class UnsafeListenerToSafe {
	
	private final EventListener listener;
	
	private UnsafeListenerToSafe() {
		listener = new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}
		};
	}
	
	public static UnsafeListenerToSafe newInstance(EventSource source) {
		UnsafeListenerToSafe safeListener = new UnsafeListenerToSafe();
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}

