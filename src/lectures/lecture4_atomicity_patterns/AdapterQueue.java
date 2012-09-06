package lectures.lecture4_atomicity_patterns;

//@ThreadSafe
class AdapterQueue<E> implements SimpleQueue<E> {
	//@GurdedBy(this)
	private ListQueue<E> delegate;
	
	AdapterQueue() {
		delegate = new ListQueue<E>();
	}
	
	@Override
	public synchronized void put(E elem) {
		// TODO Auto-generated method stub
		delegate.put(elem);
	}

	@Override
	public synchronized E get() {
		// TODO Auto-generated method stub
		return delegate.get();
	}

}
