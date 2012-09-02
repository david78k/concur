package lectures.lecture2_atomicity;

import java.util.concurrent.atomic.AtomicBoolean;

public class SlowSpinLock {
	AtomicBoolean free = new AtomicBoolean(true);
	
	void lock () {
		while(!free.compareAndSet(true, false)) {
			/* spin */
		}
	}
	
	void unlock() {
		free.set(true);
	}
}
