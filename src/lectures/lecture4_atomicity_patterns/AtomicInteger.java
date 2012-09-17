package lectures.lecture4_atomicity_patterns;

// value/get/set/getAndSet/getAndIncrement
public class AtomicInteger extends Number {

	private static final long serialVersionUID = 1L;

	private volatile int value;
	
	public AtomicInteger(int initialValue) {
		value = initialValue;
	}
	
	// no synchronized needed due to volatile
	final int get() {return value;}
	
	final void set(int value) {
		this.value = value;
	}
	
	// hardware supported atomic instruction
	private boolean compareAndSet(int old, int newValue) {
		if (old == value) {
			value = newValue;
			return true;
		}
		return false;
	}
	
	public final int getAndSet(int newValue) {
		
		for(;;) {
			int old = get();
			if(compareAndSet(old, newValue)) {
				return old;
			}
		}
	}
	
	public final int getAndIncrement() {
		
		for(;;) {
			int old = get();
			if(compareAndSet(old, old + 1)) {
				return old;
			}
		}
	}

	@Override
	public double doubleValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

}
