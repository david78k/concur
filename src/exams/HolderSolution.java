package exams;

/**
 * Fa11 Mid
 * Fa11 Final
 * 
 * two problems: deadlock and order, atomicity?
 * 
 * @author david78k
 * @param <T>
 *
 */
public class HolderSolution<T extends Comparable<T>> implements Comparable<HolderSolution<T>>{

	public static void main(String[] args) {

	}

	private T value;
	
	@Override
	public synchronized int compareTo(HolderSolution<T> other) {
		return value.compareTo(other.value);
	}
	
	private synchronized T get() {
		return value;
	}
	
	private synchronized void set(T value) {
		this.value = value;
	}
	
	public synchronized void exchangeInternal(HolderSolution<T> other) {
		T t = get();
		T v = other.get();
		set(v);
		other.set(t);
	}
	
	public void exchange(HolderSolution<T> other) {
		if(other == null) 
			return;
		else if (other.hashCode() > hashCode()) 
			exchangeInternal(other);
		else
			other.exchangeInternal(this);
	}
}
