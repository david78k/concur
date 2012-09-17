package lectures.lecture4_atomicity_patterns;

import java.io.Serializable;
import java.util.EventListener;
import java.util.NoSuchElementException;

class EventListenerList implements Serializable {

	private static final long serialVersionUID = 1L;

	// A null array to be shared by all empty listener lists
	private final static Object[] NULL_ARRAY = new Object[0];
	
	// The list of ListenerType - Listener pairs
	protected transient Object[] listenerList = NULL_ARRAY;
	
	public Object[] getListenerList() {
		return listenerList;
	}
	
	public int getListenerCount() {
		return listenerList.length/2;
	}
	
	public synchronized <T extends EventListener> void add(Class<T> t, T l) {
		if (t == null || l == null) {
			throw new NullPointerException();
		} else {
			// copy the array and add the listener
			int size = listenerList.length;
			Object[] list = new Object[size + 2]; 
			
			System.arraycopy(listenerList, 0, list, 0, size);
			list[size] = t;
			list[size + 1] = l;
			
			listenerList = list;
		}
	}
	
	public synchronized <T extends EventListener> void remove(Class<T> t, T l) {
		if (t == null || l == null) {
			throw new NullPointerException();
		} else {
			int size = listenerList.length;
			int index = -1;
			
			for (int i = 0; i < size; i++) {
				Object o = listenerList[i];
				if (l.equals(o)) {
					index = i;
					break;
				}
			}
			
			// if the given listener is in list: how to check?
			if (index == -1) throw new NoSuchElementException();
			
			Object[] list = new Object[size - 2]; 
			
			System.arraycopy(listenerList, 0, list, 0, index);
			
			if (index < list.length)
				System.arraycopy(listenerList, index + 2, list, index, size - index - 2);
			
			// set the listener array to the new array or null if it is now empty
			listenerList = (list.length == 0) ? NULL_ARRAY:list;
		}		
	}
	
	// not synchronized
	public String toString() {
		String s = "EventListenerList: ";
		
		// stores reference to list in a local variable rather than copying the list
		Object[] list = listenerList;
		s += list.length/2 + " listeners ";
		
		for (int i = 0; i < list.length/2; i += 2) {
			s += " type " + ((Class) list[i]).getName();
			s += " listener " + list[i + 1];
		}
		
		return s;
	}
}
