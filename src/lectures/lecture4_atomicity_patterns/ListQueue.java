package lectures.lecture4_atomicity_patterns;

import java.util.List;

//@NotThreadSafe
class ListQueue<E> implements SimpleQueue<E>{
	private List<E> l;
	public void put (E elem) {
		l.add(elem);
	}
	public E get() {
		return null;
	}
}