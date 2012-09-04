package lectures.lecture3_atomicity;

import java.util.concurrent.atomic.AtomicLong;

public class Sequencer {
	private final AtomicLong sequenceNumber = new AtomicLong(0);
	
	public long next() {
		return sequenceNumber.getAndIncrement();
	}
}
