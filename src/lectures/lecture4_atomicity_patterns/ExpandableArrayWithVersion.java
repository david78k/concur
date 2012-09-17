package lectures.lecture4_atomicity_patterns;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

// version number, removeLast, EAIterator
class ExpandableArrayWithVersion extends ExpandableArray {

	protected int version = 0;
	private EAIterator iterator;
	
	public ExpandableArrayWithVersion(int cap) {
		super(cap);
	}

	public static void main(String[] args) {
		ExpandableArrayWithVersion v = new ExpandableArrayWithVersion(0);
		
		for (Iterator it = v.iterator(); it.hasNext();) {
			try {
				System.out.println(it.next());
			} catch (ConcurrentModificationException e) {
				// TODO: handle exception
			}
		}
	}

	private Iterator iterator() {
		return iterator;
	}

	public synchronized void removeLast() {
		super.removeLast();
		version ++;
	}
	
	protected class EAIterator implements Iterator {
		
		protected final int currentVersion;
		private Object v;

		EAIterator() {
			currentVersion = version;
		}
		
		@Override
		public Object next() {
			synchronized (ExpandableArrayWithVersion.this) {
				if (version != currentVersion) {
					throw new ConcurrentModificationException();
				} else {
					
				}
			}
			return null;
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public void remove() {
		}
		
	}
}
