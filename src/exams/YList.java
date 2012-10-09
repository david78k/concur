package exams;

/**
 * SP05 Mid
 * SP05 Final
 * 
 * @author david78k
 *
 */
interface XList<E> {
	void add(E item);
	int contains(E item);
	int size();
}

class YListAdapter<E> implements XList<E> {

	private XList<E> list;

	public YListAdapter() {
		list = new YList<E>();
	}
	
	@Override
	public synchronized void add(E item) {
		list.add(item);
	}

	@Override
	public synchronized int contains(E item) {
		return list.contains(item);
	}

	@Override
	public synchronized int size() {
		return list.size();
	}
	
}

public class YList<E> implements XList<E>, Cloneable{

	YList () {
//		returns a new, empty YList
	}
	
	@Override
	public void add(E item) {
//		adds item to the list at the indicated index,
//		throwing an exception
//		if index<0 or index>number of items
//		in list
	}

	@Override
	/**
	 * iterate list to find indicated item
	 */
	public int contains(E item) {
//		traverses list to count the number of
//		occurrences of the given item
		return 0;
	}

	@Override
	public int size() {
		return 0;
	}
	
	public String toString() {
		return "";
	}

	protected YList<E> clone() {
		YList<E> cloned = this.clone();
		return cloned;
	}
}

