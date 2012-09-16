package lectures.lecture4_atomicity_patterns;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class UnmodifiableCollectionWrapper {
	
	public static <T> Collection<T> unmodifiableClass (Collection<? extends T> c) {
		return new UnmodifiableClass<T>(c);
	}
	
	// No synchronization
	static class UnmodifiableClass<E> implements Collection<E>, Serializable {

		private static final long serialVersionUID = 1L;

		Collection<? extends E> c;
		
		public UnmodifiableClass(Collection<? extends E> c) {
			if (c == null)
				throw new NullPointerException();
			this.c = c;
		}

		@Override
		public int size() {
			return c.size();
		}

		@Override
		public boolean contains(Object arg0) {
			return c.contains(arg0);
		}
		
		@Override
		public boolean isEmpty() {
			return c.isEmpty();
		}
		
		@Override
		public boolean add(Object arg0) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean remove(Object arg0) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public Iterator<E> iterator() {
			return new Iterator<E>() {
				Iterator<? extends E> i = c.iterator();

				@Override
				public boolean hasNext() {
					return i.hasNext();
				}

				@Override
				public E next() {
					return i.next();
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}

		@Override
		public boolean addAll(Collection<? extends E> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			// TODO Auto-generated method stub
			return c.toArray(arg0);
		}
		
	}
}
