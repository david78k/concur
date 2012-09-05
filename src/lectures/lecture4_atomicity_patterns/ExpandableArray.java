package lectures.lecture4_atomicity_patterns;

import java.util.NoSuchElementException;

//@ThreadSafe
public class ExpandableArray {

//	@GuardedBy(this)
	protected Object[] data;	// the elements
	
//	@GuardedBy(this)
	protected int size = 0;		// the number of array slots used
	
	// INV: 0 <= size <= data.length
	
	public ExpandableArray(int cap) {
		data = new Object[cap];
	}
	
	public synchronized int size() {
		return size; 
	}
	
	public synchronized Object get (int i) throws NoSuchElementException {	// subscripted access
		if (i < 0 || i >= size) 
			throw new NoSuchElementException();
		// else
		return data[i];
//		return copyOf(data[i]);	// alternative get
	}
	
	public synchronized void add(Object o) {	// add at the end, expand array if needed
		if (size == data.length) {	// if full, need a bigger array
			Object[] olddata = data;
			data = new Object[3 * (size + 1) / 2];
			System.arraycopy(olddata, 0, data, 0, size);
		}
		data[size ++] = o;
//		size ++;
	}
	
	public synchronized void removeLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
//		size --;
		data[-- size] = null;
	}
}
