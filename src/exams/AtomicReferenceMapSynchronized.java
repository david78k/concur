package exams;

import java.util.ArrayList;
import java.util.List;

public class AtomicReferenceMapSynchronized<E> {
	interface Op<E> {
		E op(E e);
	}

	private volatile List<E> l;
	
	public AtomicReferenceMapSynchronized() {
		l = new ArrayList<E>();
	}
	
	public AtomicReferenceMapSynchronized(List<E> initial) {
		l = new ArrayList<E>(initial);
	}
	
	public synchronized List<E> get() {
		return l;
	}
	
	private List<E> apply(List<E> list, Op<E> f) {
		List<E> newList = new ArrayList<E>();
		
		for (E item : list) {
			newList.add(f.op(item));
		}
		return newList;
	}
	
	public void mapUpdate(Op<E> f) {
		List<E> oldList = l;
		List<E> newList = new ArrayList<E>();
		
		do {
			oldList = get();
			newList = apply(oldList, f);
		} while (!compareAndSet(oldList, newList));
	}

	private synchronized boolean compareAndSet(List<E> expected, List<E> newList) {
		if(expected == l) {
			l = newList;
			return true;
		}
		return false;
	}
}
