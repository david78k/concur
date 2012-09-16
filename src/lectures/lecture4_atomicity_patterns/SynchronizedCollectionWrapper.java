package lectures.lecture4_atomicity_patterns;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

class SynchronizedCollectionWrapper {

	public static void main(String[] args) {
		Collection<Object> c = new ConcurrentLinkedQueue<Object>();
		Iterator<Object> i = c.iterator();
		
		// has a "check then act" atomicity problem in concurrent programs
		while (i.hasNext()) {
			Object object = (Object) i.next();
//			doSomethingWith(i.next());
			System.out.println(object);
		}
	}

	// static factory method
	public static <T> Collection<T> synchronizedCollection (Collection<T> c) {
		return new SynchronizedCollection<T>(c);
	}
	
	// inner class
	static class SynchronizedCollection<E> implements Collection<E>, Serializable {

		private static final long serialVersionUID = -5684932469337024845L;
		
		// @GuardedBy(mutex)
		Collection<E> c;	// Backing Collection
		
		// Object on which to synchronize
		Object mutex;

		SynchronizedCollection(Collection<E> c) {
			if (c == null)
				throw new NullPointerException();
			this.c = c;
			mutex = this;
		}
		
		SynchronizedCollection(Collection<E> c, Object mutex) {
			if (c == null)
				throw new NullPointerException();
			this.c = c;
			this.mutex = mutex;
		}

		@Override
		public int size() {
			synchronized (mutex) {
				return c.size();
			}
		}
		
		@Override
		public boolean isEmpty() {
			synchronized (mutex) {
				return c.isEmpty();
			}
		}
		
		@Override
		public boolean contains(Object o) {
			synchronized (mutex) {
				return c.contains(o);
			}
		}
		
		@Override
		public Object[] toArray() {
			synchronized (mutex) {
				return c.toArray();
			}
		}
		
		@Override
		public <T> T[] toArray(T[] a) {
			synchronized (mutex) {
				return c.toArray(a);
			}
		}

		@Override
		public boolean add(E e) {
			synchronized (mutex) {
				return c.add(e);
			}
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		// NOT synchronized!!!!
		@Override
		public Iterator<E> iterator() {
			return c.iterator();	// must be manually synched by user!
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			return false;
		}
		
		@Override
		public void clear() {
			
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

	}
}
