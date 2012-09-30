package exams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * SP08 Final
 * 
 * @author david78k
 * @param <E>
 *
 */
public class AtomicReferenceMap<E> {

	interface Op<E> {
		E op(E e);
	}
	
	private AtomicReference<List<E>> l;
	
	AtomicReferenceMap () {
		l = new AtomicReference<List<E>>(new ArrayList<E>());
	}
	
	AtomicReferenceMap (List<E> initial) {
		l = new AtomicReference<List<E>>(initial);
	}
	
	List<E> get() {
		return l.get();
	}
	
	private List<E> apply(List<E> list, Op<E> f) {
		List<E> newList = new ArrayList<E>();
		
		for (E item : list) {
			newList.add(f.op(item));
		}
		return newList;
	}
	
	public void mapUpdate(Op<E> f) {
		List<E> oldList;
		List<E> newList;
		do {
			oldList = get();
			newList = apply(oldList, f);
		} while (!l.compareAndSet(oldList, newList));
	}
	
}
