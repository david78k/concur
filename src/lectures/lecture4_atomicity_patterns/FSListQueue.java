package lectures.lecture4_atomicity_patterns;

//@ThreadSafe
class FSListQueue<E> extends ListQueue<E> {
	// @GuardedBy(this)
	public synchronized void put (E elem) {
		super.put(elem);
	}
	
	// @GuardedBy(this)
	public synchronized E get() {
		return super.get();
	}
}
