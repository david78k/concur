package lectures.lecture4_atomicity_patterns;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

class CollectionClass {

	// static factory method
	public static <T> Collection<T> synchronizedCollection (Collection<T> c) {
		return new SynchronizedCollection<T>(c);
	}
	
	// inner class
	static class SynchronizedCollection<E> implements Collection<E>, Serializable {

		private static final long serialVersionUID = -5684932469337024845L;

		SynchronizedCollection(Collection<E> c) {
		}

		@Override
		public boolean add(E e) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return false;
		}

		@Override
		public void clear() {
			
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			return null;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return false;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Object[] toArray() {
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}
	}
}
